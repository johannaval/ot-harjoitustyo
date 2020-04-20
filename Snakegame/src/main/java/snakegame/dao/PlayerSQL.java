package snakegame.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import java.sql.DriverManager;

import snakegame.domain.Player;

public class PlayerSQL implements DaoPlayer {

    private String url;
    private Connection db;
    private PreparedStatement ps;
    private Statement statement;
    public ArrayList<Player> players = new ArrayList<>();

    public PlayerSQL() throws SQLException {
        this.db = db;
        this.url = "jdbc:sqlite:testsql.db";
        getConnection();
        createTable();
    }

    public PlayerSQL(String url) throws SQLException {
        this.url = url;
        this.db = db;
        getConnection();
        createTable();
    }

    public void getConnection() throws SQLException {

        db = DriverManager.getConnection(url);
        statement = db.createStatement();
    }

    public void stopConnection() throws SQLException {

        //   statement.close();
        //  db.close();
    }

    @Override
    public void createTable() throws SQLException {

        Statement s = db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Players (player_id INTEGER PRIMARY KEY, username TEXT unique, password TEXT, highscore INTEGER)");
        s.close();

    }

    @Override
    public void create(Player player) throws SQLException {

        createTable();

        ps = db.prepareStatement("INSERT OR ABORT INTO Players(username, password, highscore) VALUES (?,?,?)");
        ps.setString(1, player.getUsername());
        ps.setString(2, player.getPassword());
        ps.setInt(3, player.getHighscore());

        ps.executeUpdate();
        ps.close();
    }

    @Override
    public Player findUser(String username) throws SQLException {

        createTable();
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
    }


    @Override
    public Player update(Player player) throws SQLException {

        int score = player.getHighscore();
        String username = player.getUsername();

        String update = ("UPDATE PLAYERS SET highscore =? WHERE username =? ");

        ps = db.prepareStatement(update);

        ps.setInt(1, score);
        ps.setString(2, username);
        ps.executeUpdate();

        return player;

    }

    @Override
    public Player isLogInOK(String username, String passw) throws SQLException {

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
    }

    @Override
    public boolean isThereAccountWithThisName(String username) throws SQLException {
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
    }

    @Override
    public void clear() throws SQLException {
        statement.execute("DROP TABLE IF EXISTS PLAYERS");
    }
}
