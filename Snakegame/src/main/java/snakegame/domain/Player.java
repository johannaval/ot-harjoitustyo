
package snakegame.domain;


public class Player {
    int id;
    public String username;
    public String password;
    int highscore;

    public Player( String username, String password, int highscore) {
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

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        snakegame.domain.Player player = (snakegame.domain.Player) o;
        return username.equals(player.username) &&
                password.equals(player.password);
    }

    public void putHighscore(int score) {
        this.highscore=score;
    }
}
