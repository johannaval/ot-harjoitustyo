package snakegame.dao;

import java.sql.Statement;

public class Player {

    int id;
    String kayttajanimi;
    String salasana;
    int ennatys;

    public String getNimi() {
        return this.kayttajanimi;

    }

    public int getEnnatys() {
        return this.ennatys;
    }

    public int getId() {
        return this.id;
    }

    public String getSalasana() {
        return this.salasana;
    }
}
