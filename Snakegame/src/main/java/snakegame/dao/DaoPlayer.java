package snakegame.dao;

import java.sql.*;

import javafx.collections.ObservableList;
import snakegame.domain.Player;


/**
 * DAO-rajapinta, kutsuu PlayerSQl:n metodeja
 */
public interface DaoPlayer {

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia luomaan pelaajan
     *
     * @param player pelaaja
     */
    void create(Player player);

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia päivittämään pelaajan ennätyksen
     *
     * @param player pelaaja
     * @return päivitetty pelaaja
     */
    Player update(Player player);

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia tyhjentämään tietokantataulun
     */
    void clear();

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia tarkistamaan, onko kyseisellä käyttäjänimellä jo käyttäjää
     *
     * @param username käyttäjänimi
     * @return true, jos on, false, jos ei ole
     */
    boolean isThereAccountWithThisName(String username);

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia tarkistamaan, onnistuuko sisäänkirjautuminen kyseisellä nimellä ja salasanalla
     *
     * @param username käyttäjänimi
     * @param passw    salasana
     * @return null, jos käyttäjää ei ole olemassa, muuten palauttaa pelaajan, jonka kirjaantuminen onnistui
     */
    Player isLogInOK(String username, String passw);

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia tarkistamaan, onko kyseisellä nimellä jo luotu käyttäjä
     *
     * @param username käyttäjänimi
     * @return null, jos käyttäjää ei ole olemassa, muuten palauttaa pelaajan jolla on kyseinen käyttäjänimi
     */
    Player findUser(String username);

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia luomaan tietokantataulun
     */
    void createTable();

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia ottamaan yhteyden tietokantaan
     */
    void getConnection();

    /**
     * Rajapinnan metodi, kutsuu DaoPlayer-luokan metodia tallettamaan listalle top 10-pelaajat pisteineen
     *
     * @param list tyhjä lista
     * @return täytetty lista
     */
    ObservableList topList(ObservableList list);
}



