
package snakegame.domain;

import java.util.Objects;

/**
 * Pelaaja-luokka, pitää kirjaa pelaajan nimestä, salasanasta ja ennätyksestä
 */
public class Player {

    /**
     * Pelaajan käyttäjänimi
     */
    public String username;
    /**
     * Pelaajan salasana
     */
    public String password;
    /**
     * Pelaajan ennätys
     */
    public int highscore;

    /**
     * Pelaaja-olio, jokaisella pelaajalla on käyttäjänimi, salasana ja ennätys
     *
     * @param username  käyttäjänimi
     * @param password  salasana
     * @param highscore ennätys
     */
    public Player(String username, String password, int highscore) {

        this.username = username;
        this.password = password;
        this.highscore = highscore;
    }

    /**
     * Palauttaa käyttäjän käyttäjänimen
     *
     * @return käyttäjänimi
     */
    public String getUsername() {

        return this.username;
    }

    /**
     * Palauttaa käyttäjän ennätyksen
     *
     * @return ennätys
     */
    public int getHighscore() {

        return this.highscore;
    }


    /**
     * palauttaa käyttäjän salasanan
     *
     * @return salasana
     */
    public String getPassword() {

        return this.password;
    }

    /**
     * Tarkistaa, onko kyseinen pelaaja sama kuin parametrina saatu pelaaja
     *
     * @param o verrattava pelaaja
     * @return palauttaa true, jos pelaajat ovat samat, false muuten
     */
    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return getHighscore() == player.getHighscore() &&
                Objects.equals(getUsername(), player.getUsername()) &&
                Objects.equals(getPassword(), player.getPassword());
    }

    /**
     * Asettaa ennätyksen
     *
     * @param score ennätys
     */
    public void putHighscore(int score) {

        this.highscore = score;
    }
}
