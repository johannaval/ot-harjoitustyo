package snakegame.dao;

import java.sql.*;

import javafx.collections.ObservableList;
import snakegame.domain.Player;


public interface DaoPlayer {

    void create(Player player);

    Player update(Player player) ;

    void clear();

    boolean isThereAccountWithThisName(String username);

    Player isLogInOK(String username, String passw);

    Player findUser(String username);

    void createTable();

    void getConnection();

    void stopConnection();

    ObservableList topList(ObservableList list);
}



