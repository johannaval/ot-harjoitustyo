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

        if (dao.isLogInOK(username, password) == null) {

            if (isThereAccountWithThisName(username)) {
                controller.wrongPassword();
            }
            return false;
        }
        if (dao.isLogInOK(username, password) != null) {

            this.loggedIn = dao.isLogInOK(username, password);
            System.out.println(loggedIn.getHighscore());

            return true;

        }
        return false;
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

    public void logout() {
        loggedIn = null;
    }

    public boolean createUser(String username, String password) throws SQLException {

        if (dao.findUser(username) != null) {
            return false;

        } else {

            Player p = new Player(username, password.toLowerCase(), 0);

            try {
                dao.create(p);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }
}


