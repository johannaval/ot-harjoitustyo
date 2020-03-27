/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class LogInViewController implements Initializable {

    private GameUi application;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setApplication(GameUi application) {
        this.application = application;
    }


    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("You clicked me!");
        startGame(event);
    }

    @FXML
    private void handleNewUser(ActionEvent event) {
        application.setNewUserScene();
        
    }
    @FXML
    private void startGame(ActionEvent event) {
        application.setGameScene();
    }

}
