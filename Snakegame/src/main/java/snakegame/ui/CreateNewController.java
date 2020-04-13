package snakegame.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import snakegame.domain.Player;
import snakegame.dao.PlayerSQL;
import snakegame.domain.PlayerService;


public class CreateNewController implements Initializable {

    private GameUi application;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    private PlayerSQL playerSQL;
    private Player loggedIn;
    private LogInViewController livc;
    private PlayerService service;


    public void setApplication(GameUi application) {

        this.application = application;
    }

    @FXML
    private void handleBack(ActionEvent event) {
        username.setText("");
        password.setText("");
        error.setText("");
        application.setloginScene();
    }

    @FXML
    private void handleCreate(ActionEvent event) throws SQLException {


        String name = username.getText();
        String passw = password.getText();

        if (name.length() < 3) {
            error.setText("Too short password!");
            return;
        }
        if (service.isThereAccountWithThisName(name)) {
            username.setText("");
            password.setText("");
            error.setText("Oops! This username is already registered");
            return;

        }
        if (service.createUser(name, password.getText())) {

            username.setText("");
            password.setText("");
            error.setText("");
            application.setloginScene();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.livc = new LogInViewController();
        PlayerService pService = null;
        try {
            PlayerSQL playerSQL = new PlayerSQL();
            pService = new PlayerService(playerSQL, livc);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        this.service = pService;
        }
    }
