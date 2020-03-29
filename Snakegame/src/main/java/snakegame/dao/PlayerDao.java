package snakegame.dao;

import java.sql.SQLException;
import java.util.List;
//import snakegame.domain.Player;
import java.sql.*;
import javafx.scene.control.TextField;
import snakegame.dao.Player;


public class PlayerDao implements Dao<Player, Integer> {

    private static String URL = "jdbc:sqlite:testsql.db";
    private static Connection db = createConnection();

    public PlayerDao(){
        this.db=db;
        this.URL=URL;
    }

    public PlayerDao(String URL){
        this.URL=URL; //TESTEJÄ VARTEN, ETTEI OIKEA TIETOKANTA SEKOTU
        this.db=db;
    }


    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return db;
    }

    public static void stopConnection() throws SQLException {
        db.close();

    }

    public void createTable() throws SQLException {

            Statement s = db.createStatement();

            s.execute("CREATE TABLE IF NOT EXISTS Players (player_id INTEGER PRIMARY KEY, username TEXT unique, password TEXT, highscore INTEGER)");

            s.close();

    }

    @Override
    public void create(Player player) throws SQLException {

        createTable();

        Statement s = db.createStatement();

        try {
        PreparedStatement p = db.prepareStatement("INSERT OR ABORT INTO Players(username, password, highscore) VALUES (?,?,?)");
            p.setString(1, player.getName());
            p.setString(2, player.getPassword());
            p.setInt(3, player.getHighscore());

            p.executeUpdate();

        } catch (SQLException e) {
        }
        s.close();
    }

    @Override
    public Player read(Integer key) throws SQLException {

        return null;
    }

    public Player findUser(String username) throws SQLException {

        createTable();

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM Players WHERE username =?");
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        if(!rs.next()) {
            return null;
        }
        Player pp = new Player( rs.getString("username"),
                rs.getString("password"), rs.getInt("highscore"));

        stmt.close();
        rs.close();

        return pp;
    }


    @Override
    public Player update(Player player) throws SQLException {
        return null;
    }

    @Override
    public List<Player> list() throws SQLException {
        return null;
    }

    public Player isLogInOK(String username, String passw) throws SQLException {

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM Players WHERE username =? AND password =?");
        stmt.setString(1, username);
        stmt.setString(2,passw);
        ResultSet rs = stmt.executeQuery();

        if(!rs.next()) {
            return null;
        }
        Player okay = new Player( rs.getString("username"),
                rs.getString("password"), rs.getInt("highscore"));

        okay.increaseHighscore(1);
        stmt.close();
        rs.close();

        return okay;
    }
    }
