
package snakegame.domain;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class GameService {


    @FXML
    public AnchorPane pane;

    private int boardLength;
    private int boardWidth;
    private long start = System.nanoTime();
    public int partSize = 10;
    public int snakeSize = 5;

    public GameService(AnchorPane pane) {
        this.pane = pane;
        this.boardLength = 400;
        this.boardWidth = 600;
        this.partSize = 10;
        this.snakeSize = 5;
    }

    public void startGame() {

        Area area = new Area(400, 600, pane);
        area.addNewSnake(new SnakeHead(50, area));
        Text text = new Text(500, 370, "Points: 0");
        pane.getChildren().add(text);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - start > 10000000) {
                    area.update();
                    start = now;
                }
            }
        };
        timer.start();


        pane.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.UP)) {
                area.head.switchDirection("UP");
            }
            if (event.getCode().equals(KeyCode.DOWN)) {
                area.head.switchDirection("DOWN");
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                area.head.switchDirection("RIGHT");
            }
            if (event.getCode().equals(KeyCode.LEFT)) {
                area.head.switchDirection("LEFT");
            }
        });
    }
}


