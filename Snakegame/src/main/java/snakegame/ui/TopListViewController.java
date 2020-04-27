package snakegame.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import snakegame.domain.Player;
import snakegame.domain.PlayerService;


/**
 * Top-listanäkymästä vastaava luokka (controller)
 */
public class TopListViewController implements Initializable {

    private GameUi application;
    /**
     * AP, eli AnchorPane, näkymän pohja
     */
    @FXML
    public AnchorPane AP;
    @FXML
    private TableView<Player> TopList;
    @FXML
    private Label lastScore;
    @FXML
    private TableColumn<Player, String> username;
    @FXML
    private TableColumn<Player, Integer> highscore;
    private ObservableList<Player> topList = FXCollections.observableArrayList();

    /**
     * Metodi
     *
     * @param url url
     * @param rb  ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AP.setFocusTraversable(true);
    }

    /**
     * Sovellus asettaa näkymäksi tämän näkymän
     * Hakee ennätykset Daolta ja asettaa ne listalle
     *
     * @param application Parametrina vaihdosta vastaava luokka
     */
    public void setApplication(GameUi application) {

        this.application = application;
        PlayerService ps = application.ps();

        this.topList = ps.topList(topList);

        highscore.setCellValueFactory(new PropertyValueFactory<>("highscore"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        TopList.setItems(topList);

        lastScore.setText("You got " + application.getLastPoints() + " points!");
    }


    @FXML
    private void handleExit(ActionEvent event) {

        PlayerService ps = application.ps();
        ps.logout();
        application.setLogInScene();
    }

    @FXML
    private void handleBackToGame(ActionEvent event) {

        application.setGameScene();
    }

}
