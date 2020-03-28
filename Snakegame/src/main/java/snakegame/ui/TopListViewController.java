package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TopListViewController implements Initializable {

    private GameUi application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } // mikä tää on?

    public void setApplication(GameUi application) {
        this.application = application;
    }

    @FXML
    private void handleExit(ActionEvent event) {
        application.setloginScene();

    }

    @FXML
    private void handleBackToGame(ActionEvent event) {
       application.setGameScene();

    }

}
