package snakegame.domain;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;


public class Area {

    private int length;
    private int width;
    public ArrayList<SnakePart> parts = new ArrayList<>();
    public SnakeHead head;
    public Pane pane;

    public Area(Integer length, Integer width, Pane pane) {

        this.length = length;
        this.width = width;
        this.pane = pane;

        pane.setMinSize(width, length);
        pane.setBorder(new Border(new BorderStroke(Color.DARKOLIVEGREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(10))));
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    public int getAreaWidth() {
        return this.width;

    }

    public int getAreaLength() {
        return this.length;
    }

    public void addNewSnake(SnakeHead headNew) {

        this.head = headNew;

        for (SnakePart sp : headNew.parts) {
            addParts(sp);
        }
    }

    public void addParts(SnakePart sp) {

        pane.getChildren().add(sp);
        parts.add(sp);
    }

    public void update() {
        for (SnakePart part : parts) {
            part.switchDirection();
        }
    }
}
