package snakegame.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SnakePart extends Rectangle {

    public int newX;
    public int newY;
    public int x;
    public int y;
    private String up = "UP";
    private String down = "DOWN";
    private String right = "RIGHT";
    private String left = "LEFT";
    private String direction = "RIGHT";
    SnakePart previous;
    int maxSizeX;
    int maxSizeY;


    public SnakePart(int x, int y, SnakePart part, Area area) {
        super(15, 15);
        this.newX = x;
        this.newY = y;
        setTranslateX(newX);
        setTranslateY(newY);
        previous = part;
        maxSizeX = area.getAreaWidth();
        maxSizeY = area.getAreaLength();

        //  setStroke(Color.BLACK);
        //   setFill(Color.GREENYELLOW);
        setArcWidth(10);
        setArcHeight(10);


    }

    public void setDirection(String direction) {
        this.direction = direction;
        switchDirection();
    }

    public String getDirection() {

        return this.direction;
    }

    public void switchDirection() {

        this.x = newX;
        this.y = newY;

        if (previous != null) {

            this.newX = previous.x;
            this.newY = previous.y;

        } else {

            if (direction == "UP") {
                goUp();
            }
            if (direction == "LEFT") {
                goLeft();
            }
            if (direction == "RIGHT") {
                goRight();
            }
            if (direction == "DOWN") {
                goDown();
            }
        }
        newPosition();
    }

    public void newPosition() {
        setTranslateX(newX);
        setTranslateY(newY);
    }

    public void goUp() {

        newY = newY - 1;
        if (newY <= 15) {
            newY = maxSizeY - 30;
        }
    }

    public void goDown() {

        newY = newY + 1;
        if (newY >= maxSizeY-30) {
            newY = 15;
        }
    }

    public void goLeft() {
        newX = newX - 1;
        if (newX <= 15) {
            newX = maxSizeX - 30;
        }
    }

    public void goRight() {
        newX = newX + 1;
        if (newX >= maxSizeX - 30) {
            newX = 15;
        }
    }

    public int getXposition() {

        return this.x;
    }

    public int getYposition() {

        return this.y;
    }

    //testei varten
    public void setXposition(Integer x) {
        this.x = x;
    }

    //tstei varten
    public void setYposition(Integer y) {
        this.y = y;
    }

    //testei varten
    public void setNewXposition(Integer x) {
        this.newX = x;
    }

    //tstei varten
    public void setNewYposition(Integer y) {
        this.newY = y;
    }

}
