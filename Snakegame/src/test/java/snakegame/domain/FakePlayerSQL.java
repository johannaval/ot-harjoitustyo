package snakegame.domain;

import java.util.ArrayList;
import java.util.List;
import snakegame.dao.DaoPlayer;


public class FakePlayerSQL implements DaoPlayer {

    List<Player> players = new ArrayList<>();

    public FakePlayerSQL() {
        players.add(new Player("lento", "kone", 0));
    }

    @Override
    public void create(Player player) {
        players.add(player);

    }

    @Override
    public Player update(Player player) {

        player.putHighscore(player.highscore);
        return player;
    }

    @Override
    public void clear() {
        players.clear();

    }

    @Override
    public boolean isThereAccountWithThisName(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Player isLogInOK(String username, String passw)  {
        for (Player player : players) {
            if (player.getUsername().equals(username) && player.getPassword().equals(passw)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public Player findUser(String username)  {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    @Override
    public void createTable() {

    }

    @Override
    public void getConnection()  {

    }

    @Override
    public void stopConnection() {


    }
}

