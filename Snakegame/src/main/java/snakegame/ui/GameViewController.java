package snakegame.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import snakegame.domain.PlayerService;

public class GameViewController implements Initializable {

    private GameUi application;

    @FXML
    public AnchorPane AP;

    @FXML
    private Label highscore;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AP.setFocusTraversable(true);
    }

    public void setApplication(GameUi application) {

        this.application = application;
        showHighscore();
    }

    public void showHighscore() {

        if (application.ps() != null) {
            PlayerService ps = application.ps();

            if (ps != null) {
                highscore.setText("Your highscore is: " + ps.getLoggedUser().getHighscore() + " !");
            }
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        PlayerService ps = application.ps();
        ps.logout();
        application.setLogInScene();
    }

    @FXML
    private void handleTopList(ActionEvent event) throws IOException {
        application.setTopListScene();
    }

    @FXML
    private void handleStartGame(ActionEvent event) {
        application.setGameBoardScene();

    }
}
