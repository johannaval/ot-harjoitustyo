package snakegame.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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

        GameService gs = new GameService(AP, (this));
        gs.startGame();
        AP.requestFocus();
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) throws IOException {

        if (keyEvent.getCode().equals(KeyCode.ALT)) {
            application.setGameScene();
        }
    }

    public void handleTopList(int points) throws SQLException, IOException {

        PlayerService ps = application.ps();
        this.ps = application.ps();
        ps.setHighscore(points);

        application.setTopListScene();
    }
}



