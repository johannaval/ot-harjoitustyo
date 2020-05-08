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
import snakegame.dao.PlayerSQL;
import snakegame.domain.PlayerService;

/**
 * Kirjautumisnäkymästä vastaava luokka (controller)
 */
public class LogInViewController implements Initializable {

    private GameUi application;
    private PlayerService service;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label error;

    /**
     * Metodi alustaa PlayerSQL ja PlayerService -luokat, hakee config.properties tiedostosta yhteyden playerSQL:lle
     * Alustaa näkymän
     *
     * @param url url
     * @param rb  resourceBundle
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

        PlayerService pService = null;

        try {
            PlayerSQL playerSQL = new PlayerSQL(urlForDao);
            pService = new PlayerService(playerSQL, this);
        } catch (Exception e) {
            System.out.println("Virhe ladattaessa config.properties tiedostoa!");
        }

        this.service = pService;
    }

    /**
     * Sovellus asettaa näkymäksi tämän näkymän
     *
     * @param application Parametrina gameUi:n luokka, joka huolehtii näkymien vaihtamisesta
     */
    public void setApplication(GameUi application) {

        this.application = application;
    }

    @FXML
    private void handleLogin(ActionEvent event) {

        String name = username.getText().toLowerCase();
        String passw = password.getText().toLowerCase();

        try {

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
        } catch (Exception e) {
            System.out.println("Kirjautumisessa tapahtui virhe!");
        }
    }


    @FXML
    private void handleNewUser(ActionEvent event) {
        username.setText("");
        password.setText("");
        error.setText("");
        application.setNewUserScene();
    }

    /**
     * Laittaa virheviestiksi "Wrong password!", jos käyttäjätunnus löytyy, mutta salasana ei vastaa käyttänimelle tallennettua salasanaa
     */
    @FXML
    public void wrongPassword() {
        error.setText("Wrong password!");

    }
}