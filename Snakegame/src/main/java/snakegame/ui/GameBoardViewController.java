package snakegame.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

     //   AP.setFocusTraversable(true);
        System.out.println("! :(((");
        start();
    }

    public void setApplication(GameUi application) {

        this.application = application;
    }

    public void showHighscore(Integer hs) {

        highscore.setText("Your highscore is: " + hs);
    }

    @FXML
    public void handleExit(ActionEvent event) {
        application.setloginScene();

    }

    @FXML
    public void handleTopList(ActionEvent event) {
        if (service.gameOver == true) {
            application.setTopListScene();
        }
    }

    @FXML
    public void start() {

        System.out.println("millon tääl");
        GameService gs = new GameService(AP, (this));
            gs.startGame();
            AP.requestFocus();
    }
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode().equals(KeyCode.ALT)) {
            application.setGameScene();
        }
    }

    public void handleTopList(){

        application.setTopListScene();
    }
}



