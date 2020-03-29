package snakegame.dao;

import java.sql.SQLException;
import java.util.List;
//import snakegame.domain.Player;
import java.sql.*;
import javafx.scene.control.TextField;
import snakegame.dao.Player;


public class PlayerDao implements Dao<Player, Integer> {

    private static final String URL = "jdbc:sqlite:testsql.db";
    private static final Connection db = createConnection();

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
        {
             getConnection();

            Statement s = db.createStatement();

            s.execute("CREATE TABLE IF NOT EXISTS Players (player_id INTEGER PRIMARY KEY, username TEXT unique, password TEXT, highscore INTEGER)");

            s.close();
            // stopConnection();
        }
    }

    @Override
    public void create(Player player) throws SQLException {

        createTable();

        //    getConnection();

        Statement s = db.createStatement();

        PreparedStatement p = db.prepareStatement("INSERT INTO Players(username, password, highscore) VALUES (?,?,?)");
            p.setString(1, player.getName());
            p.setString(2, player.getPassword());
            p.setInt(3, player.getHighscore());

            p.executeUpdate();


        s.close();

        //    stopConnection();
    }

    @Override
    public Player read(Integer key) throws SQLException {

        //  getConnection();


        //    stopConnection();

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
        //  connection.close();

        return okay;
    }
    }
