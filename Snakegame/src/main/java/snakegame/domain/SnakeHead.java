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
        addBody();
    }

    public void addHead() {

        head = new SnakePart(x, y, null, area);
        head.setStroke(Color.AQUA);
        head.setFill(Color.BLACK);
        parts.add(head);

        this.body = head;
    }

    public void addBody() {

        for (int i = 1; i < size; i++) {
            SnakePart part = new SnakePart(this.x, this.y, body, area);
            part.setStroke(Color.BLACK);
            parts.add(part);
            body = part;
        }
    }

    public void switchDirection(String direction) {

        for (SnakePart part : parts) {
            part.setDirection(direction);
        }
    }

    public void setDirection(String direction) {

        this.direction = direction;
    }

    public String getDirection() {

        return this.direction;
    }
}
