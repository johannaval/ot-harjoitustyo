package snakegame.domain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Food extends Rectangle {

    private int x;
    private int y;
    private ArrayList<Paint> colors;
    public Paint color;

    public Food(int x, int y) {
        super(15, 15);
        this.x = x;
        this.y = y;
        setTranslateX(x);
        setTranslateY(y);
        ArrayList<String> colors = new ArrayList<>();
        setFill(colorArray());
    }

    public int getXposition() {

        return this.x;
    }

    public int getYposition() {

        return this.y;
    }

    public Paint getColour() {
        return this.color;

    }

    public Paint colorArray() {

        ArrayList<Paint> colors = new ArrayList<>();

        colors.add(Color.PURPLE);
        colors.add(Color.AQUAMARINE);
        colors.add(Color.DARKORANGE);
        colors.add(Color.HOTPINK);
        colors.add(Color.GREENYELLOW);
        colors.add(Color.LIGHTSKYBLUE);
        colors.add(Color.TOMATO);
        colors.add(Color.LIME);
        colors.add(Color.MISTYROSE);
        colors.add(Color.SALMON);
        colors.add(Color.PEACHPUFF);
        colors.add(Color.OLIVE);
        colors.add(Color.HONEYDEW);
        colors.add(Color.AQUAMARINE);
        colors.add(Color.SPRINGGREEN);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);

        Random r = new Random();

        this.color = colors.get(r.nextInt((colors.size() - 0)));

        return this.color;
    }
}
