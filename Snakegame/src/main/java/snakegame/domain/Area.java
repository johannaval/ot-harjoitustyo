package snakegame.domain;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;


public class Area {

    private int length;
    private int width;
    public ArrayList<SnakePart> parts = new ArrayList<>();
    public SnakeHead head;
    public Pane pane;
    public int points;
    public Food food;

    public Area(Integer length, Integer width, Pane pane) {

        this.length = length;
        this.width = width;
        this.pane = pane;
        this.points = 0;
        addFood();
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
        if (ateFood(food) == true) {
            points = points + 50;
            addFood();
            addNewPart();
        }
    }

    public void addNewPart() {

        SnakePart newPart = new SnakePart(head.body.x+200, head.body.y+200, head.body, this);
        System.out.println(" :(( mix ei toimi");
        head.body = newPart;
        addParts(newPart);
        System.out.println(parts.size());


    }

    public void addFood() {

        Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(length);

        Food newFood = new Food(x, y);
        pane.getChildren().add(newFood);
        pane.getChildren().remove(food);
        food = newFood;

    }

    public boolean ateFood(Food food) {

        if (food == null) {
            return false;
        }

        if (((food.getXposition() == head.head.getXposition() || food.getXposition() == head.head.getXposition() + 1 || food.getXposition() == head.head.getXposition() - 1 || food.getXposition() == head.head.getXposition() - 2 || food.getXposition() == head.head.getXposition() + 2)) &&
                (food.getYposition() == head.head.getYposition() || food.getYposition() == head.head.getYposition() + 1 || food.getYposition() == head.head.getYposition() - 1 || food.getYposition() == head.head.getYposition() - 2 || food.getYposition() == head.head.getYposition() + 2)) {
            System.out.println(head.head.getXposition() + " ja " + food.getXposition());
            System.out.println(head.head.getYposition() + " ja " + food.getYposition());


            return true;
        }
        return false;
    }

    public int getPoints() {
        return this.points;
    }
}
