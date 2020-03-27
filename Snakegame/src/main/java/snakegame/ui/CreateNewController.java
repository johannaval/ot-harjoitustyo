package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CreateNewController implements Initializable {

    private GameUi application;

    @FXML
    private TextField username;

    public void setApplication(GameUi application) {
        this.application = application;
    }

    @FXML
    private void handleBack(ActionEvent event) {
        application.setloginScene();
    }

    @FXML
    private void handleCreate(ActionEvent event) {
        System.out.println("You clicked me!");
        application.setloginScene();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleNewUser(ActionEvent event) {
        application.setloginScene();
        
    }

}
