package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GameViewController implements Initializable {

    private GameUi application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setApplication(GameUi application) {
        this.application = application;
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
