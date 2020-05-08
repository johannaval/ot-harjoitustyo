package snakegame.domain;

import javafx.collections.ObservableList;
import snakegame.dao.DaoPlayer;
import snakegame.ui.LogInViewController;

/**
 * Luokka, joka huolehtii pelaajan tiedoista
 */
public class PlayerService {

    private Player loggedIn;
    private LogInViewController controller;
    private DaoPlayer dao;


    /**
     * PlayerServicen kontruktori, saa parametrinaan playerSQL:n ja kirjautumisnäkymän controllerin
     *
     * @param playerSQL  playerSQL luokka, joka vastaa pelaajan tietojen pysyväistallennuksesta
     * @param controller controller, huolehtii sisäänkirjautumisesta
     */
    public PlayerService(DaoPlayer playerSQL, LogInViewController controller) {

        this.dao = playerSQL;
        this.controller = controller;
    }


    /**
     * Kutsuu Daota tarkistamaan, onnistuuko kirjautuminen
     *
     * @param username käyttäjänimi
     * @param password salasana
     * @return palauttaa true, jos kirjautuminen onnistuu, false jos ei ja samalla katsoo, onko nimellä jo käyttäjä, jos on, kutsuu se
     * controlleria asettamaan virheviestiin salasanan olevan väärin
     */
    public boolean login(String username, String password) {

        if (dao.isLogInOK(username, password) == null) {

            if (isThereAccountWithThisName(username)) {
                controller.wrongPassword();
            }
            return false;

        } else {
            this.loggedIn = dao.isLogInOK(username, password);
        }
        return true;
    }

    /**
     * Tarkistaa Daolta, onko kyseisellä nimellä tehty jo tunnus
     *
     * @param username käyttäjänimi jota tarkistetaan
     * @return palauttaa true, jos kyseisellä nimellä on jo tunnus, false muuten
     */
    public boolean isThereAccountWithThisName(String username) {

        if (dao.isThereAccountWithThisName(username)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Asettaa ennätyksen käyttäjälle, jos uusi tulos on parempi kuin vanha ennätys, kutsuu myös Dao-rajapinnan metodia päivittämään tuloksen
     *
     * @param score uusi tulos
     */
    public void setHighscore(int score) {

        if (loggedIn.getHighscore() < score) {
            loggedIn.putHighscore(score);
            dao.update(loggedIn);
        }
    }


    /**
     * Palauttaa kirjautuneen käyttäjän
     *
     * @return kirjautunut käyttäjä
     */
    public Player getLoggedUser() {

        return loggedIn;
    }

    /**
     * Uloskirjaa käyttäjän
     */
    public void logout() {

        loggedIn = null;
    }

    /**
     * Luo uuden käyttäjän parametreina saadun nimen ja salasanan mukaan
     *
     * @param username käyttäjänimi
     * @param password salasana
     * @return palauttaa true, jos luonti onnistui, false jos kyseisellä käyttäjänimellä oli jo tunnus
     */
    public boolean createUser(String username, String password) {

        if (dao.findUser(username) != null) {
            return false;

        } else {
            Player p = new Player(username.toLowerCase(), password.toLowerCase(), 0);
            dao.create(p);
            return true;
        }
    }

    /**
     * Kutsuu dao:ta palauttamaan toplistan
     *
     * @param list tyhjä lista
     * @return täytetty lista
     */
    public ObservableList topList(ObservableList list) {

        ObservableList topList = dao.topList(list);

        return topList;
    }
}


