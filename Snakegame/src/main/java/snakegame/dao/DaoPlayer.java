package snakegame.dao;

import java.sql.*;

import snakegame.domain.Player;


public interface DaoPlayer {

    void create(Player player) throws SQLException;

    Player update(Player player) throws SQLException;

    void clear() throws SQLException;

    boolean isThereAccountWithThisName(String username) throws SQLException;

    Player isLogInOK(String username, String passw) throws SQLException;

    Player findUser(String username) throws SQLException;

    void createTable() throws SQLException;

    void getConnection() throws SQLException;

    void stopConnection() throws SQLException;
}



