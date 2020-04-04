package snakegame.domain;

import java.util.ArrayList;

public class SnakeHead {

    public ArrayList<SnakePart> parts = new ArrayList<>();
    private SnakePart head;
    private int x;
    private int y;
    public Area area;
    private int size;

    public SnakeHead(int size, Area area) {

        this.size = size;
        this.x = 200;
        this.y = 200;
        this.area = area;
        addParts();

    }

    public void addParts() {

        head = new SnakePart(x, y, null, area);
        parts.add(head);

        SnakePart previous = head;

        for (int i = 1; i < size; i++) {
            SnakePart part = new SnakePart(x + i, y, previous, area);
            parts.add(part);
            previous = part;
        }
    }

    public void switchDirection(String direction) {

    }
}
