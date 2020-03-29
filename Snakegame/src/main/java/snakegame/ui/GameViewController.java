package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GameViewController implements Initializable {

    private GameUi application;

    @FXML
    private Label highscore;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setApplication(GameUi application) {

        this.application = application;
    }

    public void showHighscore(Integer hs){
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
}
