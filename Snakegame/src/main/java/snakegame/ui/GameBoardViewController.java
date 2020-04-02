package snakegame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import snakegame.domain.gameService;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardViewController implements Initializable {

   // private snakegame.domain.gameService service;

    private GameUi application;

    @FXML
    private Label highscore;

    @FXML
    public AnchorPane AP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setApplication(GameUi application) {

        this.application = application;
    }

    public void showHighscore(Integer hs) {
        highscore.setText("Your highscore is: " + hs);
    }

    @FXML
    private void handleExit(ActionEvent event) {
        application.setloginScene();

    }

    @FXML
    private void handleTopList(ActionEvent event) {
        application.setTopListScene();

    }

    @FXML
    public void start () {

        gameService gs = new gameService(AP);
        gs.startGame();

    }
}