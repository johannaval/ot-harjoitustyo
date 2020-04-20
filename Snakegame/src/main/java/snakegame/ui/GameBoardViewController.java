package snakegame.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import snakegame.dao.PlayerSQL;
import snakegame.domain.GameService;
import snakegame.domain.PlayerService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameBoardViewController implements Initializable {

    private GameService service;

    private GameUi application;

    private PlayerService ps;

    @FXML
    public AnchorPane AP;

    @FXML
    private Label label;

    boolean enterPressed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AP.setFocusTraversable(true);
        start();
    }

    public void setApplication(GameUi application) {

        this.application = application;
    }

    @FXML
    public void start() {

        service = new GameService(AP, (this));
        service.startGame();
        AP.requestFocus();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) throws IOException {


        if (keyEvent.getCode().equals(KeyCode.ENTER) && this.enterPressed == false) {
            label.setText("");
            service.enterPressed();
            this.enterPressed = true;
            service.move();
        }

        if (this.enterPressed) {
            AP.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                String direction = service.area.head.direction;

                if (event.getCode().equals(KeyCode.DOWN) && direction != "UP") {
                    service.goDown();
                }
                if (event.getCode().equals(KeyCode.RIGHT) && direction != "LEFT") {
                    service.goRigh();
                }
                if (event.getCode().equals(KeyCode.LEFT) && direction != "RIGHT") {
                    service.goLeft();
                }
                if (event.getCode().equals(KeyCode.UP) && direction != "DOWN") {
                    service.goUp();
                }

            });
        }
    }

    public void handleTopList(int points) throws SQLException, IOException {

        PlayerService ps = application.ps();
        this.ps = application.ps();
        this.enterPressed=false;
        ps.setHighscore(points);
        application.setLastScore(points);
        application.setTopListScene();
    }
}



