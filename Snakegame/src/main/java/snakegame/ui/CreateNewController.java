package snakegame.ui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import snakegame.dao.*;
import snakegame.dao.Player;
import snakegame.dao.PlayerDao;

public class CreateNewController implements Initializable {

    private PlayerDao dao;

    private Player p;

    private GameUi application;
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    @FXML
    private Label usernameLabel;

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

        PlayerDao d = new PlayerDao();

        String name = username.getText();

        if(name.length()<3){
            usernameLabel.setText("Username: (must contain at least 3 letters) !!!!!!!!!!!!!");
          //  error.setText("Practice your reading skills....");
            return;
        }

        if (d.findUser(username.getText()) != null) {
            error.setText("Oops! This username is already registered");
            return;

        } else {

            Player p = new Player(username.getText(), password.getText(), 0);

            d.create(p);
            username.setText("");
            password.setText("");
            usernameLabel.setText("Username: (must contain at least 3 letters)");
            error.setText("");

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
