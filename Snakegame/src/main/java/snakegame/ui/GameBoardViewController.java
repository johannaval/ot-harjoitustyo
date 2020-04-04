package snakegame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import snakegame.domain.GameService;

import java.net.URL;
import java.util.ResourceBundle;

public class GameBoardViewController implements Initializable {

    private GameService service;

    private GameUi application;

    @FXML
    private Label highscore;

    @FXML
    public AnchorPane AP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start();
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
    public void start() {

        GameService gs = new GameService(AP);
        gs.startGame();


    }
}