package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import snakegame.dao.Player;
import snakegame.domain.GameService;
import snakegame.dao.Player;

public class GameViewController implements Initializable {

    private GameUi application;

    private GameService gs;

    private Player player;

    @FXML
    public AnchorPane AP;

    @FXML
    private Label highscore;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showHighscore();


    }

    public void setApplication(GameUi application) {
        this.application = application;
    }

    public void showHighscore() {


        if (player == null) {
            System.out.println("!!");
            return;
        } else {
            System.out.println(player.getUsername());
           // highscore.setText("Your highscore is: ");
        }
    }

    public void m(Player loggedIn) {
        this.player = loggedIn;
        System.out.println(loggedIn.getHighscore() + ", "+ loggedIn.getUsername());
        showHighscore();
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
    private void handleStartGame(ActionEvent event) {
        application.setGameBoardScene();


    }
}
