
package snakegame.domain;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import snakegame.ui.GameBoardViewController;

/**
 * Pitää huolta peli/sovelluslogiikasta
 */
public class GameService {

    /**
     * AnchorPane, pelialueen runko/alusta
     */
    @FXML
    public AnchorPane pane;
    private int boardLength;
    private int boardWidth;
    private long start = System.nanoTime();
    /**
     * Madon palan koko
     */
    public int partSize = 10;
    /**
     * Madon koko
     */
    public int snakeSize = 5;
    /**
     * Pelialue
     */
    public Area area;
    /**
     * Boolean arvo sille, onko peli päättynyt
     */
    public boolean gameOver;
    /**
     * Pelaajan pistete
     */
    public int points;
    /**
     * Pelialueesta vastaava controller
     */
    public GameBoardViewController controller;
    /**
     * Tekstikenttä, jossa näkyy pelaajan pisteet
     */
    public Text text;
    /**
     * Boolean arvo sille, haluaako pelaaja reunat vai ei
     */
    public boolean withBorders;
    /**
     * Pelaajan valitsema teema
     */
    public String theme;

    /**
     * GameServicen konstruktori, parametreina saa AnchorPanen (pelin pohja), ja pelialueen controllerin
     *
     * @param pane       pohja
     * @param controller kontroller, vastaa pelialueesta (esim näppäinten kuuntelu)
     */
    public GameService(AnchorPane pane, GameBoardViewController controller) {
        this.pane = pane;
        this.boardLength = 400;
        this.boardWidth = 600;
        this.partSize = 10;
        this.snakeSize = 5;
        gameOver = false;
        this.controller = controller;
    }

    /**
     * Katsoo, halusiko pelaaja pelata reunoilla vai ilman, asettaa pelialueen boolean withBorders arvoksi saadun tuloksen
     */
    public void withBorders() {

        this.withBorders = controller.borders;
        area.withBorders = this.withBorders;
    }

    /**
     * Saa controllerilta teeman, jonka tallettaa teemaksi pelialueelle
     */
    public void theme() {

        this.theme = controller.theme;
        area.theme = this.theme;
        area.setTheme();
    }

    /**
     * Alustaa pelialueen
     */
    public void addGameArea() {

        this.area = new Area(400, 600, pane);

    }

    /**
     * Kutsuu metodia withBorders() asettamaan reunat mikäli pelaaja halusi, kutsuu theme() metodia asettamaan teman, lisää
     * pelialueelle ruoan ja madon, lisää pisteet näkymään, sekä kutsuu startTimer() metodia päivittämään peliä
     */
    public void move() {


        withBorders();
        theme();
        area.addFood();
        this.text = new Text(500, 370, "Points: " + area.getPoints());
        text.setStroke(Color.WHITE);
        if (theme.equals("3")) {
            text.setStroke(Color.BLACK);
        }
        pane.getChildren().add(text);

        area.addNewSnake(new SnakeHead(50, area));

        startTimer();

    }

    /**
     * Käynnistä timerin, jonka avulla peli etenee ja mato päivittyy.
     */
    public void startTimer() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                text.setText("Points: " + area.getPoints());
                if (area.gameOver() == false) {
                    if (now - start > 16000000) {
                        area.update();
                        start = now;
                    }
                } else {
                    stop();
                    gameIsOver();
                }
            }
        };
        timer.start();
        area.head.setDirection("RIGHT");
    }

    /**
     * Asettaa gameOver booleaniksi true ja entePressed false, poistaa pelialueelta kaiken, ja kutsuu controlleria
     * hoitamaan top-listan, jolle annetaan parametrina juuri saadut pisteet
     */
    public void gameIsOver() {

        this.points = area.getPoints();
        gameOver = true;
        area.enterPressed = false;
        pane.getChildren().removeAll();
        controller.handleTopList(points);

    }

    /**
     * Asettaa madon suunnaksi "alas"
     *
     * @see GameBoardViewController#handleKeyPressed(KeyEvent)
     */
    public void goDown() {

        area.head.setDirection("DOWN");
        area.head.switchDirection("DOWN");
    }

    /**
     * Asettaa madon suunnaksi "oikea"
     *
     * @see GameBoardViewController#handleKeyPressed(KeyEvent)
     */
    public void goRigh() {

        area.head.setDirection("RIGHT");
        area.head.switchDirection("RIGHT");
    }

    /**
     * Asettaa madon suunnaksi "vasen"
     *
     * @see GameBoardViewController#handleKeyPressed(KeyEvent)
     */
    public void goLeft() {

        area.head.setDirection("LEFT");
        area.head.switchDirection("LEFT");

    }

    /**
     * Asettaa madon suunnaksi "ylös"
     *
     * @see GameBoardViewController#handleKeyPressed(KeyEvent)
     */
    public void goUp() {

        area.head.setDirection("UP");
        area.head.switchDirection("UP");

    }

    /**
     * Laittaa enterPressed arvoksi true
     *
     * @see GameBoardViewController#handleKeyPressed(KeyEvent)
     */
    public void enterPressed() {

        area.enterPressed = true;
    }
}
