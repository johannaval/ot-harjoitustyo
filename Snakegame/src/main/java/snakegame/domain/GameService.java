
package snakegame.domain;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import snakegame.ui.GameBoardViewController;
import java.io.IOException;
import java.sql.SQLException;


public class GameService {

    @FXML
    public AnchorPane pane;
    private int boardLength;
    private int boardWidth;
    private long start = System.nanoTime();
    public int partSize = 10;
    public int snakeSize = 5;
    public Area area;
    public boolean gameOver;
    public int points;

    public GameBoardViewController controller;

    public GameService(AnchorPane pane, GameBoardViewController controller) {
        this.pane = pane;
        this.boardLength = 400;
        this.boardWidth = 600;
        this.partSize = 10;
        this.snakeSize = 5;
        gameOver = false;
        this.controller = controller;
    }

    public void startGame() {

        this.area = new Area(400, 600, pane);
        area.addNewSnake(new SnakeHead(50, area));
        Text text = new Text(500, 370, "Points: " + area.getPoints());
        text.setStroke(Color.WHITE);
        pane.getChildren().add(text);

        AnimationTimer timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                int points = area.getPoints();
                text.setText("Points: " + points);


                if (area.gameOver() == false) {
                    if (now - start > 16000000) {
                        area.update();
                        start = now;
                    }
                } else {
                    stop();
                    try {
                        gameIsOver();
                    } catch (SQLException | IOException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        };
        timer.start();

        area.head.setDirection("RIGHT");
        move();

    }

    public void gameIsOver() throws SQLException, IOException {

        this.points=area.getPoints();
        gameOver = true;
        pane.getChildren().clear();
        startGame();
        controller.handleTopList(points);
    }

    public void move() {

        pane.addEventFilter(KeyEvent.KEY_PRESSED,
                event -> {

                    String direction = area.head.direction;

                    if (event.getCode().equals(KeyCode.DOWN) && direction != "UP") {
                        goDown();
                    }
                    if (event.getCode().equals(KeyCode.RIGHT) && direction != "LEFT") {
                        goRigh();
                    }
                    if (event.getCode().equals(KeyCode.LEFT) && direction != "RIGHT") {
                        goLeft();
                    }
                    if (event.getCode().equals(KeyCode.UP) && direction != "DOWN") {
                        goUp();
                    }
                });
        pane.requestFocus();
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
