package snakegame.domain;

import snakegame.dao.DaoPlayer;
import snakegame.ui.LogInViewController;
import java.sql.SQLException;


public class PlayerService {

    private Player loggedIn;
    private LogInViewController controller;
    private DaoPlayer dao;


    public PlayerService(DaoPlayer playerSQL, LogInViewController controller) throws SQLException {

        this.dao = playerSQL;
        this.controller = controller;
    }


    public boolean login(String username, String password) throws SQLException {

        System.out.println("9");

        if (dao.isLogInOK(username, password) == null) {

            System.out.println("3");

            if (isThereAccountWithThisName(username)) {
                controller.wrongPassword();
            }
            return false;

        } else {

            this.loggedIn = dao.isLogInOK(username, password);
            System.out.println(loggedIn.getHighscore());
            System.out.println("4");
        }
        return true;
    }

    public boolean isThereAccountWithThisName(String username) throws SQLException {
        if (dao.isThereAccountWithThisName(username)) {
            return true;
        } else {
            return false;
        }

    }

    public void setHighscore(int score) throws SQLException {
        if (loggedIn.getHighscore() < score) {
            loggedIn.putHighscore(score);
            dao.update(loggedIn);
        }
    }


    public Player getLoggedUser() {
        return loggedIn;
    }

    public void logout() throws SQLException {

      //  dao.stopConnection();
        loggedIn = null;
    }

    public boolean createUser(String username, String password) throws SQLException {

        if (dao.findUser(username) != null) {
            return false;

        } else {
            Player p = new Player(username, password.toLowerCase(), 0);
            dao.create(p);
            return true;
        }
    }
}


