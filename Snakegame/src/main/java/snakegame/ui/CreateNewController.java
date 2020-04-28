package snakegame.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import snakegame.domain.Player;
import snakegame.dao.PlayerSQL;
import snakegame.domain.PlayerService;

/**
 * Uuden käyttäjän luomis-näkymästä vastaava luokka (controller)
 */
public class CreateNewController implements Initializable {

    private GameUi application;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label error;
    private PlayerSQL playerSQL;
    private Player loggedIn;
    private LogInViewController livc;
    private PlayerService service;


    /**
     * Metodi alustaa PlayerSQL ja PlayerService -luokat, hakee config.properties tiedostosta yhteyden playerSQL:lle
     *
     * @param url url
     * @param rb  resourceBundle?
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Konfiguroinnissa virhe!");
        }

        String urlForDao = properties.getProperty("urlForDao");


        this.livc = new LogInViewController();
        PlayerService pService = null;

        try {
            PlayerSQL playerSQL = new PlayerSQL(urlForDao);
            pService = new PlayerService(playerSQL, livc);
        } catch (Exception e) {
            System.out.println("Virhe ladattaessa config.properties tiedostoa!");
        }

        this.service = pService;
    }

    /**
     * Alustaa GameUi:n applikaatioksi
     *
     * @param application saa parametrinaan gameUi:n
     */
    public void setApplication(GameUi application) {

        this.application = application;
    }


    @FXML
    private void handleBack(ActionEvent event) {
        username.setText("");
        password.setText("");
        error.setText("");
        application.setLogInScene();
    }


    @FXML
    private void handleCreate(ActionEvent event) {


        String name = username.getText().toLowerCase();
        String passw = password.getText().toLowerCase();

        try {

            if (name.length() < 3) {
                error.setText("Too short username!");
                return;
            }
            if (name.length() > 20) {
                error.setText("Too long username! Limit is 20!");
                return;

            }
            if (passw.length() > 20) {
                error.setText("Too long password! Limit is 20!");
                return;

            }
            if (passw.isEmpty()) {
                error.setText("Password must contain at least 1 letter!");
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

                application.setLogInScene();
            }

        } catch (Exception e) {
            System.out.println("Käyttäjän luomisessa tapahtui virhe!");

        }
    }
}
