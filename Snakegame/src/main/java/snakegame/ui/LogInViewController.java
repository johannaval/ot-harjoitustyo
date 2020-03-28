package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogInViewController implements Initializable {
    
    private GameUi application;
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField password;
    
    @FXML
    private Label error;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setApplication(GameUi application) {
        this.application = application;
    }
    
    @FXML
    private void handleLogin(ActionEvent event) {
        
        //hae käyttäjän tiedot

        // luo boolean userFound() jolla katsotaan onko pelaaja jo rekisteröitynyt ja onko tiedot tietokannassa
        
        if (password.getText().contains("ABC")) {
            error.setText("Create your account first");
        } else {
            username.setText("");
            password.setText("");
            application.setGameScene();
        }
    }
    
    @FXML
    private void handleNewUser(ActionEvent event) {
        username.setText("");
        password.setText("");
        error.setText("");
        application.setNewUserScene();
        
    }
    
    @FXML
    private void startGame(ActionEvent event) {
        username.setText("");
        password.setText("");
        error.setText("");
        application.setGameScene();
    }
    
}
