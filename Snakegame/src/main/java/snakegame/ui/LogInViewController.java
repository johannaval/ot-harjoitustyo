package snakegame.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import snakegame.dao.PlayerSQL;
import snakegame.domain.Player;
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
    private PlayerSQL playerSQL;
    private Player loggedIn;
    private PlayerService servise;


    /**
     * Metodi alustaa PlayerSQL ja PlayerService -luokat, hakee config.properties tiedostosta yhteyden playerSQL:lle
     *
     * @param url url
     * @param rb  resourceBundle?
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

     /*   String urlForDao = "";

        try (InputStream input = new FileInputStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            System.out.println(prop.getProperty("urlForDao") + "!!");
            urlForDao = prop.getProperty("urlForDao");

        } catch (IOException ex) {
            System.out.println("ei onnistunut");
        }



        System.out.println(getConfigProperties());

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Konfiguroinnissa virhe!");
        }

        String urlForDao = properties.getProperty("urlForDao");

        System.out.println(urlForDao);

        System.out.printf(properties.getProperty("testi1"));

*/
        PlayerService pService = null;

     //   PlayerSQL playerSQL = new PlayerSQL(urlForDao);
        PlayerSQL playerSQL = new PlayerSQL();
        pService = new PlayerService(playerSQL, this);

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
    private void handleLogin(ActionEvent event) throws  IOException {

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

    /**
     * Laittaa virheviestiksi "Wrong password!", jos käyttäjätunnus löytyy, mutta salasana ei vastaa käyttänimelle tallennettua salasanaa
     */
    @FXML
    public void wrongPassword() {
        error.setText("Wrong password!");

    }


    public String getConfigProperties() {

        String db = "";

        try (InputStream input = LogInViewController.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return null;
            }
            prop.load(input);

            db = prop.getProperty("urlForDao");
            System.out.println(prop.getProperty("urlForDao"));

        } catch (IOException ex) {
            System.out.println("noupp :(");
            return null;
        }
        return db;
    }
}