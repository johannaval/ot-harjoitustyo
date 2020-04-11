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
    public boolean firstEaten;
    public boolean gameOver;

    public Area(Integer length, Integer width, Pane pane) {

        this.length = length;
        this.width = width;
        this.pane = pane;
        this.points = 0;
        addFood();
        pane.setMinSize(width, length);
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        firstEaten = false;
        gameOver=false;
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
        if (firstEaten) {
            if (hitWall() == true) {
                gameOver=true;
                pane.getChildren().remove(head);
                System.out.println(":D");
                pane.getChildren().removeAll();
                pane.getChildren().remove(parts);
                return;
            }
        }
        if (ateFood(food) == true) {
            points = points + 50;
            addFood();
            addNewPart();
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

        System.out.println("!! :D omppu");

        Random random = new Random();
        int x = random.nextInt(width - 60);
        int y = random.nextInt(length - 60);

        Food newFood = new Food(30 + x, 30 + y);
        pane.getChildren().add(newFood);
        pane.getChildren().remove(food);
        food = newFood;

    }

    public boolean hitWall() {

        if ((head.head.getXposition() >= this.width  || head.head.getXposition() <=0 || head.head.getYposition()<=0 || head.head.getYposition()>this.length)) {
            //    System.out.println("osui!");
            //   System.out.println(head.head.getXposition() + "," + head.head.getYposition());
            System.out.println("osu");
            return true;
        }
        return false;
    }

    public boolean ateFood(Food food) {

        if (food == null) {
            return false;
        }

        if (((food.getXposition() == head.head.getXposition() || food.getXposition() == head.head.getXposition() + 1 || food.getXposition() == head.head.getXposition() - 1 || food.getXposition() == head.head.getXposition() - 2 || food.getXposition() == head.head.getXposition() + 2 || food.getXposition() == head.head.getXposition() + 3 || food.getXposition() == head.head.getXposition() - 3) &&
                (food.getYposition() == head.head.getYposition() || food.getYposition() == head.head.getYposition() + 1 || food.getYposition() == head.head.getYposition() - 1 || food.getYposition() == head.head.getYposition() - 2 || food.getYposition() == head.head.getYposition() + 2 || (food.getYposition() == head.head.getYposition() + 3 || food.getYposition() == head.head.getYposition() - 3)))) {

            firstEaten = true;
            return true;
        }
        return false;
    }

    public int getPoints() {
        return this.points;
    }
    public boolean gameOver(){
        return gameOver;
    }
}
