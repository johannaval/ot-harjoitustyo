package snakegame.domain;

import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Madon päästä vastaava luokka
 */
public class SnakeHead {

    /**
     * Lista madon osista (vartalo)
     */
    public ArrayList<SnakePart> parts = new ArrayList<>();
    /**
     * Madon pää
     */
    public SnakePart head;
    /**
     * Madon vartalo
     */
    public SnakePart body;
    private int x;
    private int y;
    private Area area;
    private int size;
    /**
     * kertoo madon suunnan
     */
    public String direction;

    /**
     * Madon pään konstruktori, saa parametrinaan madon koon ja pelialueesta vastaavan luokan
     *
     * @param size madon koko
     * @param area pelialueesta vastaava luokka
     */
    public SnakeHead(int size, Area area) {

        this.size = size;
        this.x = 200;
        this.y = 200;
        this.area = area;
        addHead();
        addBody();
    }

    private void addHead() {

        head = new SnakePart(x, y, null, area);
        head.setStroke(Color.AQUA);
        head.setFill(Color.BLACK);
        parts.add(head);
        this.body = head;
    }

    private void addBody() {

        for (int i = 1; i < size; i++) {
            SnakePart part = new SnakePart(this.x, this.y, body, area);
            part.setStroke(Color.BLACK);
            parts.add(part);
            body = part;
        }
    }

    /**
     * Vaihtaa madon suunnaksi parametrina saadun suunnan
     *
     * @param direction saatu suunta
     */
    public void switchDirection(String direction) {

        for (SnakePart part : parts) {
            part.setDirection(direction);
        }
    }

    /**
     * Asettaa suunnaksi parametrina saadun suunnan
     *
     * @param direction asetettava suunta
     */
    public void setDirection(String direction) {

        this.direction = direction;
    }

    /**
     * Palauttaa suunnan
     *
     * @return palauttaa suunnan
     */
    public String getDirection() {

        return this.direction;
    }
}
