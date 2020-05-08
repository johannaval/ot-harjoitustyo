package snakegame.domain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Pelialueen ruoista vastaava luokka
 */
public class Food extends Rectangle {

    private int x;
    private int y;
    /**
     * Ruoan väri
     */
    public Paint color;
    /**
     * Pelaajan valitsema teema
     */
    public String theme;

    /**
     * Ruoka-olion konstruktori, saa parametreina x ja y koordinaatit, ja teeman jonka pelaaja on valinnut
     *
     * @param x     x-koordinaatti
     * @param y     y-koordinaatti
     * @param theme pelaajan teema
     */
    public Food(int x, int y, String theme) {
        super(15, 15);
        this.x = x;
        this.y = y;
        setTranslateX(x);
        setTranslateY(y);
        this.theme = theme;
        setColor();
    }

    /**
     * Asettaa ruoan värin sen mukaan, minkä teeman pelaaja on valinnut
     */
    public void setColor() {
        if (this.theme.equals("1")) {
            setFill(colorArray());
        } else if (this.theme.equals("2")) {
            setFill(Color.WHITE);
        } else if (this.theme.equals("3")) {
            setFill(Color.DARKOLIVEGREEN);
            setStroke(Color.WHITE);
        }
    }

    /**
     * Palauttaa ruoan X:n koordinaatin
     *
     * @return koordinaatti
     */
    public int getXposition() {

        return this.x;
    }

    /**
     * Palattaa ruoan Y:n koordinaatin
     *
     * @return koordinaatti
     */
    public int getYposition() {

        return this.y;
    }

    /**
     * Luo väreistä ArrayListan
     *
     * @return palauttaa sattumanvaraisen värin ruoan väriksi
     */
    public Paint colorArray() {

        List<Color> colors = Arrays.asList(Color.PURPLE, Color.AQUAMARINE, Color.DARKORANGE, Color.HOTPINK, Color.GREENYELLOW, Color.LIGHTSKYBLUE,
                Color.TOMATO, Color.LIME, Color.MISTYROSE, Color.SALMON, Color.PEACHPUFF, Color.HONEYDEW, Color.AQUAMARINE, Color.SPRINGGREEN,
                Color.RED, Color.BLUE, Color.YELLOW);

        Random r = new Random();
        this.color = colors.get(r.nextInt((colors.size() - 0)));
        return this.color;
    }
}
