package snakegame.dao;

import java.util.Objects;

public class Player {

    int id;
    public String username;
    String password;
    int highscore;
    //ArrayList<Player>t;

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

    public int increaseHighscore(int amount) {

        this.highscore = this.highscore + amount;
        return this.highscore;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return username.equals(player.username) &&
                password.equals(player.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password);
    }
}
