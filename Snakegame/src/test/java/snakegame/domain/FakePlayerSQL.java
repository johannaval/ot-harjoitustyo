package snakegame.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import snakegame.dao.DaoPlayer;


public class FakePlayerSQL implements DaoPlayer {
    List<Player> players = new ArrayList<>();

    public FakePlayerSQL() {
        players.add(new Player("lento", "kone", 0));
    }

    @Override
    public void create(Player player) throws SQLException {
        players.add(player);

    }

    @Override
    public Player update(Player player) throws SQLException {

        player.putHighscore(player.highscore);
        return player;
    }

    @Override
    public void clear() throws SQLException {
        players.clear();

    }

    @Override
    public boolean isThereAccountWithThisName(String username) throws SQLException {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Player isLogInOK(String username, String passw) throws SQLException {
        for (Player player : players) {
            if (player.getUsername().equals(username) && player.getPassword().equals(passw)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public Player findUser(String username) throws SQLException {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void getConnection() throws SQLException {

    }
}

