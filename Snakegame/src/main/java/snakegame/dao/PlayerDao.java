package snakegame.dao;

//import java.sql.SQLException;
import java.util.List;
import snakegame.domain.Player;
import java.util.*;
import java.sql.*;


public class PlayerDao implements Dao<Player, Integer> {

    @Override
    public void create(Player player) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:moi");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Pelaajat"
                + " (nimi, salasana, ennatys)"
                + " VALUES (?, ?, ?)");
        stmt.setString(1, Player.getUserName());
        stmt.setString(2, Player.getPassword());
        //stmt.setInt(4, Player.getEnnatys());

        System.out.println("tääl");

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public Player read(Integer key) throws SQLException {
        return null;

    }

    @Override
    public Player update(Player player) throws SQLException {
        // ei toteutettu
        return null;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

    @Override
    public List<Player> list() throws SQLException {
        // ei toteutettu
        return null;
    }
}
