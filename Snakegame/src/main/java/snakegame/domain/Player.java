
package snakegame.domain;

/**
 * Pelaaja-luokka, pelaajan ominaisuudet
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

    public Player(String username, String password, int highscore) {

        this.username = username;
        this.password = password;
        this.highscore = highscore;
    }

    /** Palauttaa käyttäjän käyttäjänimen
     * @return käyttäjänimi
     */
    public String getUsername() {

        return this.username;
    }

    /** Palauttaa käyttäjän ennätyksen
     * @return  ennätys
     */
    public int getHighscore() {

        return this.highscore;
    }


    /**
     * palauttaa käyttäjän salasanan
     * @return salasana
     */
    public String getPassword() {

        return this.password;
    }

    /**
     * Tarkistaa, onko kyseinen pelaaja sama kuin parametrina saatu pelaaja
     * @param o verrattava pelaaja
     * @return palauttaa true, jos pelaajat ovat samat, false muuten
     */
    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Player)) {
            return false;
        }

        Player other = (Player) o;
        return username.equals(other.username);
    }

    /**
     * Asettaa ennätyksen
     * @param score ennätys
     */
    public void putHighscore(int score) {

        this.highscore = score;
    }
}
