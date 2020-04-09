
package snakegame.domain;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import snakegame.ui.GameBoardViewController;


import static javafx.scene.input.KeyEvent.*;

public class GameService {

    @FXML
    public AnchorPane pane;


    private int boardLength;
    private int boardWidth;
    private long start = System.nanoTime();
    public int partSize = 10;
    public int snakeSize = 5;
    public Area area;

    public GameService(AnchorPane pane) {
        this.pane = pane;
        this.boardLength = 400;
        this.boardWidth = 600;
        this.partSize = 10;
        this.snakeSize = 5;
    }

    public void startGame() {

        this.area = new Area(400, 600, pane);
        area.addNewSnake(new SnakeHead(50, area));
        Text text = new Text(500, 370, "Points: " + area.getPoints());
        pane.getChildren().add(text);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int points = area.getPoints();
                text.setText("Points: " + points);

                if (now - start > 10000000) {
                    area.update();
                    start = now;
                }
            }
        };
        timer.start();

        area.head.setDirection("RIGHT");
        move();
    }

    public void move() {

        pane.addEventHandler(KEY_PRESSED, new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().equals(KeyCode.DOWN)) {
                    goDown();
                }
                if (event.getCode().equals(KeyCode.RIGHT)) {
                    goRigh();
                }
                if (event.getCode().equals(KeyCode.LEFT)) {
                    goLeft();
                }
                if (event.getCode().equals(KeyCode.UP)) {
                    goUp();
                }
                if (event.getCode().equals(KeyCode.ENTER)) {
                    goUp();
                }
                if (event.getCode().equals(KeyCode.CONTROL)) {
                    goLeft();
                }
            }
        });
        pane.requestFocus();
        //  System.out.println("pressed " + event.getCode());
    }

    public void goDown() {
        area.head.setDirection("DOWN");
        area.head.switchDirection("DOWN");
    }

    public void goRigh() {
        area.head.setDirection("RIGHT");
        area.head.switchDirection("RIGHT");
    }

    public void goLeft() {
        area.head.setDirection("LEFT");
        area.head.switchDirection("LEFT");

    }

    public void goUp() {
        area.head.setDirection("UP");
        area.head.switchDirection("UP");

    }
}

