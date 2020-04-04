package snakegame.domain;

import javafx.scene.shape.Rectangle;

public class SnakePart extends Rectangle {

    int newX;
    int newY;
    int x;
    int y;
    private String up = "UP";
    private String down = "DOWN";
    private String right = "RIGHT";
    private String left = "LEFT";
    private String direction = "RIGHT";
    SnakePart previous;
    int maxSizeX;
    int maxSizeY;


    public SnakePart(int x, int y, SnakePart part, Area area) {
        super(10, 10);
        this.newX = x;
        this.newY = y;
        setTranslateX(newX);
        setTranslateY(newY);
        previous = part;
        maxSizeX = area.getAreaWidth();
        maxSizeY = area.getAreaLength();
    }

    public void setDirection(String direction) {
        this.direction = direction;
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
        if (newY < 0) {
            newY = maxSizeY - 1;
        }
    }

    public void goDown() {
        newY = newY + 1;
        if (newY >= maxSizeY) {
            newY = 0;
        }
    }

    public void goLeft() {
        newX = newX - 1;
        if (newX < 0) {
            newX = maxSizeX - 1;
        }
    }

    public void goRight() {
        newX = newX + 1;
        if (newX >= maxSizeX) {
            newX = 0;
        }
    }

}