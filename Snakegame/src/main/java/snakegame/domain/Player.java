
package snakegame.domain;


public class Player {

    public String username;
    public String password;
    int highscore;

    public Player(String username, String password, int highscore) {

        this.username = username;
        this.password = password;
        this.highscore = highscore;
    }

    public String getUsername() {

        return this.username;
    }

    public int getHighscore() {

        return this.highscore;
    }


    public String getPassword() {

        return this.password;
    }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Player)) {
            return false;
        }

        Player other = (Player) o;
        return username.equals(other.username);
    }

    public void putHighscore(int score) {
        this.highscore = score;
    }
}
