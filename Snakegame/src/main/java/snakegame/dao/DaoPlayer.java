package snakegame.dao;

import javafx.collections.ObservableList;
import snakegame.domain.Player;


/**
 * DAO-rajapinta, kutsuu PlayerSQl:n metodeja
 */
public interface DaoPlayer {

    /**
     * Luo pelaajan ja lisää tietokantaan
     *
     * @param player pelaaja
     */
    void create(Player player);

    /**
     * Päivittää pelaajan ennätyksen
     *
     * @param player pelaaja
     * @return päivitetty pelaaja
     */
    Player update(Player player);

    /**
     * Tyhjentää tietokantataulun
     */
    void clear();

    /**
     * Tarkistaa, onko kyseisellä käyttäjänimellä jo käyttäjää
     *
     * @param username käyttäjänimi
     * @return true, jos on, false, jos ei ole
     */
    boolean isThereAccountWithThisName(String username);

    /**
     * Tarkistaa, onnistuuko sisäänkirjautuminen kyseisellä nimellä ja salasanalla
     *
     * @param username käyttäjänimi
     * @param passw    salasana
     * @return null, jos käyttäjää ei ole olemassa, muuten palauttaa pelaajan, jonka kirjaantuminen onnistui
     */
    Player isLogInOK(String username, String passw);

    /**
     * Tarkistaa, onko kyseisellä nimellä jo luotu käyttäjä
     *
     * @param username käyttäjänimi
     * @return null, jos käyttäjää ei ole olemassa, muuten palauttaa pelaajan jolla on kyseinen käyttäjänimi
     */
    Player findUser(String username);

    /**
     * Luo tietokantataulun
     */
    void createTable();

    /**
     * Ottaa yhteyden tietokantaan
     */
    void getConnection();

    /**
     * Tallentaa listalle top 10-pelaajat pisteineen
     *
     * @param list tyhjä lista
     * @return täytetty lista
     */
    ObservableList topList(ObservableList list);
}



