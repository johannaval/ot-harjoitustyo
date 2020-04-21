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
    public boolean enterPressed;
    public boolean gameOver;

    public Area(Integer length, Integer width, Pane pane) {

        this.length = length;
        this.width = width;
        this.pane = pane;
        this.points = 0;
        pane.setMinSize(width, length);
        pane.setBorder(new Border(new BorderStroke(Color.GREENYELLOW, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        enterPressed = false;
        gameOver = false;
    }

    public int getAreaWidth() {

        return this.width;

    }

    public int getSnakeSize() {

        return parts.size();
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

        if (parts.size() > 10) {
            sp.setStroke(food.color);
            pane.setBorder(new Border(new BorderStroke(food.color, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
        }
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
        if (enterPressed) {
            if (hitWall() || hitItself()) {
                gameOver = true;
                return;
            }
        }
    }

    public void addNewPart() {

        for (int i = 1; i < 15; i++) {

            SnakePart newPart = new SnakePart(head.body.x, head.body.y, head.body, this);
            head.body = newPart;
            head.body.setStroke(Color.AQUA);
            addParts(newPart);
        }
    }

    public void addFood() {

        Random random = new Random();
        int x = random.nextInt(width - 60);
        int y = random.nextInt(length - 60);

        Food newFood = new Food(30 + x, 30 + y);
        pane.getChildren().add(newFood);
        pane.getChildren().remove(food);
        food = newFood;

    }

    public boolean hitWall() {

        if ((head.head.getXposition() >= this.width - 15 || head.head.getXposition() <= 15 || head.head.getYposition() <= 15 || head.head.getYposition() >= this.length - 30)) {
            return true;
        }
        return false;
    }

    public boolean hitItself() {

        for (SnakePart part : parts) {
            if (head.head.newX == part.getXposition() && head.head.newY == part.getYposition()) {
                return true;
            }
        }
        return false;
    }

    public boolean ateFood(Food food) {

        if (food == null) {
            return false;
        }
        if (Math.abs(food.getXposition() - head.head.getXposition()) < 10 && Math.abs(food.getYposition() - head.head.getYposition()) < 10) {
            return true;
        }
        return false;
    }

    public int getPoints() {

        return this.points;
    }

    public boolean gameOver() {

        return gameOver;
    }
}
