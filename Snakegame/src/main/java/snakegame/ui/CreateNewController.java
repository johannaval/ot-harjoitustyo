package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateNewController implements Initializable {

    private GameUi application;
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    public void setApplication(GameUi application) {
        this.application = application;
    }

    @FXML
    private void handleBack(ActionEvent event) {
        username.setText("");
        password.setText("");
        application.setloginScene();
    }

    @FXML
    private void handleCreate(ActionEvent event) {

        // täällä lisään pelaajan tiedot tietokantaan 
        if (username.getText().contains("ABC")) {
            error.setText("Oops! This username is already registered");

        } else {

            username.setText("");
            password.setText("");
            application.setloginScene();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

//    @FXML
//    private void handleNewUser(ActionEvent event) {
//    }
}
