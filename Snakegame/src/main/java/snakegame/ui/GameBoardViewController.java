package snakegame.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import snakegame.domain.GameService;
import snakegame.domain.PlayerService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Pelialue-näkymästä vastaava luokka (controller)
 */
public class GameBoardViewController implements Initializable {

    private GameService service;
    private GameUi application;
    private PlayerService ps;
    /**
     * AP, eli AnchorPane, näkymän pohja
     */
    @FXML
    public AnchorPane AP;
    @FXML
    private Label label;
    private boolean enterPressed;
    /**
     * Kertoo, haluaako pelaaja pelata reunoilla vai ilman (true=haluaa, false=ei)
     */
    public boolean borders;
    /**
     * Kertoo pelaajan valitseman teeman (1-3)
     */
    public String theme;


    /**
     * kutsuu metodia aloittamaan pelin
     * @param url url
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AP.setFocusTraversable(true);
        start();
    }

    /**
     * Alustaa GameUi:n applikaatioksi ja tarkistaa siltä, onko pelaaja halunnut reunat, ja minkä teeman hän on valinnut
     * @param application saa parametrinaan gameUi:n
     *
     */
    public void setApplication(GameUi application) {

        this.application = application;
       this.borders=application.borders;
       this.theme=application.theme;
    }

    /**
     * Alustaa GameServicen eli pelilogiikasta vastaavan luokan ja aloittaa pelin
     */
    @FXML
    public void start() {

        service = new GameService(AP, (this));
        service.addGameArea();
        AP.requestFocus();
    }

    /**
     * Kertoo pelilogiikasta vastaavalle luokalle GameServicelle käyttäjän painamista näppäimistä
     * @param keyEvent näppäinten tapahtumat
     */
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent)  {


        if (keyEvent.getCode().equals(KeyCode.ENTER) && this.enterPressed == false) {
            label.setText("");
            service.enterPressed();
            this.enterPressed = true;
            service.move();
        }

        if (this.enterPressed) {
            AP.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

                String direction = service.area.head.direction;

                if (event.getCode().equals(KeyCode.DOWN) && direction != "UP") {
                    service.goDown();
                }
                if (event.getCode().equals(KeyCode.RIGHT) && direction != "LEFT") {
                    service.goRigh();
                }
                if (event.getCode().equals(KeyCode.LEFT) && direction != "RIGHT") {
                    service.goLeft();
                }
                if (event.getCode().equals(KeyCode.UP) && direction != "DOWN") {
                    service.goUp();
                }

            });
        }
    }

    /**
     * Alustaa PlayerServicen ja kutsuu sitä päivittämään ennätyksen, vaihtaa enterin painamisen falseksi, kutsuu GameUi:ta asettamaan asettamaan  pisteet ja
     * asettamaan näkymäksi top-listan
     * @param points pisteet, jotka pelaaja juuri sai
     */
    public void handleTopList(int points) {

        PlayerService ps = application.ps();
        this.ps = application.ps();
        this.enterPressed = false;
        ps.setHighscore(points);
        application.setLastScore(points);
        application.setTopListScene();
    }
}



