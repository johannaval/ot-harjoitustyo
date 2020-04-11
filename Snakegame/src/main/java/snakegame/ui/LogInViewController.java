package snakegame.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import snakegame.dao.Player;
import snakegame.dao.PlayerSQL;

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
    private void handleLogin(ActionEvent event) throws SQLException {


        String name = username.getText().toLowerCase();
        String passw = password.getText().toLowerCase();

        PlayerSQL dd = new PlayerSQL();
        GameViewController GVC = new GameViewController();

        Player player = dd.findUser(name);


        if (player == null) {
            username.setText("");
            password.setText("");
            error.setText("Create your account first!");

        } else {
            player = dd.isLogInOK(name, passw);

            if (player != null) {

                application.setGameScene();
           //     GVC.m(player);

                username.setText("");
                password.setText("");
                error.setText("");

            } else {
                error.setText("Wrong password!");
            }
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