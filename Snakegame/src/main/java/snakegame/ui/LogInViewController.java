package snakegame.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import snakegame.dao.PlayerSQL;
import snakegame.domain.Player;
import snakegame.domain.PlayerService;

public class LogInViewController implements Initializable {

    private GameUi application;

    private PlayerService service;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    private PlayerSQL playerSQL;
    private Player loggedIn;
    private PlayerService servise;


    public void setApplication(GameUi application) {
        this.application = application;
    }

    @FXML
    private void handleLogin(ActionEvent event) throws SQLException, IOException {

        String name = username.getText().toLowerCase();
        String passw = password.getText().toLowerCase();

        if (!service.login(name, passw)) {

            if (!error.getText().equals("Wrong password!")) {
                error.setText("Create your account first!");
                username.setText("");
                password.setText("");
            }
        } else {

            application.setPlayerService(this.service);

            application.setGameScene();
            username.setText("");
            password.setText("");
            error.setText("");
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
    public void wrongPassword() {
        error.setText("Wrong password!");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PlayerService pService = null;
        try {
            PlayerSQL playerSQL = new PlayerSQL();
            pService = new PlayerService(playerSQL, this);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.service = pService;
    }
}