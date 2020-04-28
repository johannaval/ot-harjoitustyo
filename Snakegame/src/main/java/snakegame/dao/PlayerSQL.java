package snakegame.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.sql.DriverManager;

import javafx.collections.ObservableList;
import snakegame.domain.Player;

/**
 * Tietojen pysyväistallennuksesta vastaava luokka
 */
public class PlayerSQL implements DaoPlayer {

    private String url;
    private Connection db;
    private PreparedStatement ps;
    private Statement statement;

    /**
     * Saa parametrinaan tietokannan urlin, kutsuu metodia getConnection() käynnistämään yhteyden
     * ja metodia createTable() luomaan tietokanta taulun, ellei sitä jo ole
     *
     * @param url tietokannan url
     */
    public PlayerSQL(String url) {
        this.url = url;
        this.db = db;
        getConnection();
        createTable();
    }

    /**
     * Avaa tietokanta-yhteyden
     */
    public void getConnection() {

        try {
            db = DriverManager.getConnection(url);
            statement = db.createStatement();
        } catch (SQLException throwables) {
            System.out.println("Virhe yhteyden luomisessa!");
        }
    }

    /**
     * Luo tietokantataulun, elleisitä jo ole
     */
    @Override
    public void createTable() {

        Statement s = null;
        try {
            s = db.createStatement();

            s.execute("CREATE TABLE IF NOT EXISTS Players (player_id INTEGER PRIMARY KEY, username TEXT unique, password TEXT, highscore INTEGER)");
            s.close();

        } catch (SQLException throwables) {
            System.out.println("Tietokantataulun luomisessa tapahtui virhe!");
        }
    }

    /**
     * Lisää pelaajan tietokantatauluun
     *
     * @param player pelaaja
     */
    @Override
    public void create(Player player) {

        try {

            ps = db.prepareStatement("INSERT OR ABORT INTO Players(username, password, highscore) VALUES (?,?,?)");
            ps.setString(1, player.getUsername());
            ps.setString(2, player.getPassword());
            ps.setInt(3, player.getHighscore());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException throwables) {
            System.out.println("Käyttäjän luomisessa tapahtui virhe!");
        }
    }

    /**
     * Etsii tietokantataulusta parametrinaan saadulla käyttäjänimellä olevan pelaajan
     *
     * @param username käyttäjänimi
     * @return null, jos pelaajaa ei ole, muuten palauttaa pelaajan
     */
    @Override
    public Player findUser(String username) {

        try {

            ps = db.prepareStatement("SELECT * FROM Players WHERE username =?");
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;
            }
            Player pp = new Player(rs.getString("username"),
                    rs.getString("password"), rs.getInt("highscore"));

            ps.close();
            rs.close();

            return pp;

        } catch (SQLException throwables) {
            System.out.println("Käyttäjän etsimisessa tapahtui virhe!");

        }
        return null;
    }


    /**
     * Päivittää parametrina saadun pelaajan ennätyksen tietokantatauluun
     *
     * @param player pelaaja
     * @return palauttaa pelaajan, jonka ennätys päivitettiin
     */
    @Override
    public Player update(Player player) {

        int score = player.getHighscore();
        String username = player.getUsername();

        String update = ("UPDATE PLAYERS SET highscore =? WHERE username =? ");

        try {
            ps = db.prepareStatement(update);

            ps.setInt(1, score);
            ps.setString(2, username);
            ps.executeUpdate();

            return player;

        } catch (SQLException throwables) {
            System.out.println("Käyttäjän tietojen päivittämisessä tapahtui virhe!");
        }
        return null;
    }

    /**
     * Tarkistaa, onnistuuko kyseisellä käyttäjänimellä ja salasanalla kirjaantuminen
     *
     * @param username käyttäjänimi
     * @param passw    salasana
     * @return pelauttaa null, jos käyttäjää ei ole olemassa, muuten palauttaa pelaajan, jonka kirjaantuminen onnistui
     */
    @Override
    public Player isLogInOK(String username, String passw) {

        try {
            ps = db.prepareStatement("SELECT * FROM Players WHERE username =? AND password =?");

            ps.setString(1, username);
            ps.setString(2, passw);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return null;

            } else {
                Player okay = new Player(rs.getString("username"),
                        rs.getString("password"), rs.getInt("highscore"));

                ps.close();
                rs.close();

                return okay;
            }
        } catch (SQLException throwables) {
            System.out.println("Kirjautumisessa tapahtui virhe!");
        }
        return null;
    }

    /**
     * Tarkistaa, onko kyseisellä nimellä lisätty jo käyttäjä
     *
     * @param username käyttäjänimi
     * @return false, jos ei ole, true muuten
     */
    @Override
    public boolean isThereAccountWithThisName(String username) {

        try {
            ps = db.prepareStatement("SELECT * FROM Players WHERE username =?");

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                ps.close();
                rs.close();
                return false;
            }
            ps.close();
            rs.close();

            return true;


        } catch (SQLException throwables) {
            System.out.println("Tietokannan tietojen hakemisessa tapahtui virhe!");
        }
        return true;
    }

    /**
     * Tyhjentää Players taulun tiedot
     */
    @Override
    public void clear() {

        try {
            statement.execute("DROP TABLE IF EXISTS PLAYERS");
        } catch (SQLException throwables) {
            System.out.println("Tietokannan tyhjentämisessä tapahtui virhe!");
        }
    }

    @Override
    public ObservableList topList(ObservableList list) {

        try {
            ps = db.prepareStatement("select * from Players order by highscore desc limit 10");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                if (rs.getInt("highscore") > 0) {
                    list.add(new Player(rs.getString("username"), rs.getString("password"), rs.getInt("highscore")));
                }
            }
            ps.close();
            rs.close();

            return list;

        } catch (SQLException throwables) {
            System.out.println("Top-listan hakemisessa tapahtui virhe!");
        }
        return null;
    }
}
