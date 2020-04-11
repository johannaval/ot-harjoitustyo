package snakegame.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.sql.DriverManager;


public class PlayerSQL implements DaoPlayer<Player, Integer> {

    private String url = "jdbc:sqlite:testsql.db";
    private Connection db;
    private PreparedStatement ps;
    private Statement statement;
    public ArrayList<Player> players = new ArrayList<>();

    public PlayerSQL() throws SQLException {
        this.db = db;
        this.url = url;
        getConnection();
    }

    public PlayerSQL(String url) throws SQLException {
        this.url = url; //TESTEJÄ VARTEN, ETTEI OIKEA TIETOKANTA SEKOTU
        this.db = db;
        getConnection();
    }


    /*private static Connection createConnection() {


        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    } */

    public void getConnection() throws SQLException {

        db = DriverManager.getConnection(url);
        statement = db.createStatement();
    }

    public void stopConnection() throws SQLException {

        statement.close();
        db.close();
    }

    public void createTable() throws SQLException {

  //      getConnection();

        Statement s = db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Players (player_id INTEGER PRIMARY KEY, username TEXT unique, password TEXT, highscore INTEGER)");
        s.close();

   //     stopConnection();
    }

    @Override
    public void create(Player player) throws SQLException {

     //   getConnection();
        createTable();

        ps = db.prepareStatement("INSERT OR ABORT INTO Players(username, password, highscore) VALUES (?,?,?)");
        ps.setString(1, player.getUsername());
        ps.setString(2, player.getPassword());
        ps.setInt(3, player.getHighscore());

        ps.executeUpdate();
        ps.close();

   //     stopConnection();
    }

    @Override
    public Player read(Integer key) throws SQLException {
        return null;
    }

    public Player findUser(String username) throws SQLException {

  //      getConnection();

        createTable();

        ps = db.prepareStatement("SELECT * FROM Players WHERE username =?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {

   //         stopConnection();
            return null;
        }
        Player pp = new Player(rs.getString("username"),
                rs.getString("password"), rs.getInt("highscore"));

        ps.close();
        rs.close();

  //      stopConnection();
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

   //     getConnection();

        ps = db.prepareStatement("SELECT * FROM Players WHERE username =? AND password =?");
        ps.setString(1, username);
        ps.setString(2, passw);
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {

   //         stopConnection();
            return null;
        }
        Player okay = new Player(rs.getString("username"),
                rs.getString("password"), rs.getInt("highscore"));

        okay.increaseHighscore(1);
        ps.close();
        rs.close();

     //   stopConnection();
        return okay;
    }

    public void lopetettua(){
       // players.add(player);
    }

    public ArrayList getEntiia() throws SQLException {

        ps = db.prepareStatement("SELECT * FROM Players ");
        ResultSet rs = ps.executeQuery();

        if(!rs.next()){
            return null;
        } else {
        //    Player p = rs.

       //     players.add(rs.next());


        }
        return players;
    }
}
