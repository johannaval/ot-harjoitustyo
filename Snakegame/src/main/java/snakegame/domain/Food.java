package snakegame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle {

    private int x;
    private int y;

    public Food(int x, int y) {
        super(15,15);
        this.x = x;
        this.y = y;
        setTranslateX(x);
        setTranslateY(y);
        setFill(Color.AQUA);
    }

    public int getXposition() {
        return this.x;
    }

    public int getYposition() {
        return this.y;
    }
}
