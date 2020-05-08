package snakegame.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import snakegame.domain.PlayerService;

/**
 * Pelivalikko-näkymästä vastaava luokka (controller)
 */
public class GameViewController implements Initializable {

    private GameUi application;
    @FXML
    public AnchorPane AP;
    @FXML
    private Label highscore;
    @FXML
    private CheckBox borders;
    @FXML
    private ComboBox theme;
    @FXML
    private Label error;
    ObservableList<String> themes = FXCollections.observableArrayList("1. Rainbow", "2. Space", "3. Beach");


    /**
     * Lisää teema-valikkoon teemat sekä ruksin reunojen valintaan
     * Alustaa näkymän
     *
     * @param url url
     * @param rb  ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AP.setFocusTraversable(true);
        theme.setValue("Theme?");
        theme.setItems(themes);
        borders.setSelected(true);
    }

    /**
     * Sovellus asettaa näkymäksi tämän näkymän
     * Kutsuu metodia showHighscore() näyttämään kirjautuneen pelaajan ennätyksen
     *
     * @param application Parametrina gameUi:n luokka, joka huolehtii näkymien vaihtamisesta
     */
    public void setApplication(GameUi application) {

        this.application = application;
        showHighscore();
    }

    /**
     * Asettaa tekstikenttään käyttäjän ennätyksen
     */
    public void showHighscore() {

        if (application.ps() != null) {
            PlayerService ps = application.ps();

            if (ps != null) {
                highscore.setText("Your highscore is: " + ps.getLoggedUser().getHighscore() + " !");
            }
        }
    }


    @FXML
    private void handleExit(ActionEvent event) {

        PlayerService ps = application.ps();
        ps.logout();
        application.setLogInScene();
    }


    @FXML
    private void handleStartGame(ActionEvent event) {

        if (!theme.getSelectionModel().getSelectedItem().toString().contains("1.") && !theme.getSelectionModel().getSelectedItem().toString().contains("2.") &&
                !theme.getSelectionModel().getSelectedItem().toString().contains("3.")) {
            error.setText("Choose your theme first!");
        } else {

            error.setText("");
            application.setBorders(this.borders.isSelected());


            String themeNumber = theme.getSelectionModel().getSelectedItem().toString();
            application.setTheme(themeNumber);


            application.setGameBoardScene();
        }
    }
}
