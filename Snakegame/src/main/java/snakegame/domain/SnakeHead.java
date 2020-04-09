package snakegame.domain;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class SnakeHead {

    public ArrayList<SnakePart> parts = new ArrayList<>();
    public SnakePart head;
    public SnakePart body;
    public int x;
    public int y;
    public Area area;
    public int size;
    public String direction;

    public SnakeHead(int size, Area area) {

        this.size = size;
        this.x = 200;
        this.y = 200;
        this.area = area;
        addHead();
        addParts();
    }

    public void addHead() {
        head = new SnakePart(x, y, null, area);
        parts.add(head);
        body = head;
    }

    public void addParts() {

        for (int i = 1; i < size; i++) {
            SnakePart part = new SnakePart(x + 10, y, body, area);
            parts.add(part);
            body = part;
        }
    }

    public void switchDirection(String direction) {

        for (SnakePart part : parts) {
            part.setDirection(direction);
            //     part.switchDirection();

        }
    }


    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
