package snakegame.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import snakegame.domain.PlayerService;

/**
 * Luokka, joka huolehtii näkymien asettamisesta
 */
public class GameUi extends Application {

    private Stage stage;
    private Scene sceneLogin;
    private Scene sceneCreateNew;
    private Scene sceneGame;
    private Scene sceneHighscore;
    private Scene sceneGameBoard;
    private PlayerService ps;
    private int lastPoints = 0;
    /**
     * Kertoo, haluaako pelaaja pelata reunoilla vai ilman, (true=haluaa), (false=ei)
     */
    public boolean borders = false;
    /**
     * Kertoo pelaajan valitseman teeman (1-3)
     */
    public String theme;


    /**
     * Käynnistää ohjelman
     *
     * @param stage saa parametrinaan vaihtamisesta vastaavan olion
     */
    @Override
    public void start(Stage stage) {

        this.stage = stage;

        stage.setTitle("Snake game");
        setLogInScene();
        stage.show();
    }

    /**
     * Käynnistää ohjelman
     *
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * Vastaa ohjelman sulkemisesta
     */
    @Override
    public void stop() {

    }

    /**
     * Alustaa kirjautumis- ja uuden käyttäjänluontinäkymät
     */
    @Override
    public void init() {

        try {
            FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LogInView.fxml"));
            Parent loginPane = null;
            loginPane = loginSceneLoader.load();
            LogInViewController loginSceneController = loginSceneLoader.getController();
            loginSceneController.setApplication(this);
            sceneLogin = new Scene(loginPane);
        } catch (IOException e) {
            System.out.println("Kirjautumisnäkymän lataamisessa tapahtui virhe!");
        }

        try {
            FXMLLoader newUserSceneLoader = new FXMLLoader(getClass().getResource("/fxml/CreateNew.fxml"));
            Parent newUserPane = newUserSceneLoader.load();
            CreateNewController newUserSceneController = newUserSceneLoader.getController();
            newUserSceneController.setApplication(this);
            sceneCreateNew = new Scene(newUserPane);
        } catch (IOException e) {
            System.out.println("Käyttäjän luomis-näkymän lataamisessa tapahtui virhe!");
        }
    }

    /**
     * Kertoo vaihtamisesta huolehtivalle oliolle vaihtaa kirjautumisnäkymään
     */
    public void setLogInScene() {

        stage.setScene(sceneLogin);
        stage.setTitle("Log in");
        stage.setResizable(false);
    }

    /**
     * Kertoo vaihtamisesta huolehtivalle oliolle vaihtaa käyttäjänluontinäkymään
     */
    public void setNewUserScene() {

        stage.setScene(sceneCreateNew);
        stage.setTitle("Create new");
        stage.setResizable(false);
    }

    /**
     * alustaa GameScenen eli pelivalikko näkymän
     */
    public void setGameScene() {

        try {
            FXMLLoader gameStartLoader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
            Parent gamePane = null;
            gamePane = gameStartLoader.load();
            GameViewController gameSceneController = gameStartLoader.getController();
            gameSceneController.setApplication(this);
            sceneGame = new Scene(gamePane);
            stage.setResizable(false);

            stage.setScene(sceneGame);
            stage.setTitle("Start!");
        } catch (IOException e) {
            System.out.println("Pelivalikon lataamisessa tapahtui virhe!");
        }
    }

    /**
     * alustaa TopListScenen eli top-lista näkymän
     */
    public void setTopListScene() {

        try {
            FXMLLoader toplistLoader = new FXMLLoader(getClass().getResource("/fxml/TopListView.fxml"));
            Parent listPane = null;
            listPane = toplistLoader.load();
            TopListViewController listSceneController = toplistLoader.getController();
            listSceneController.setApplication(this);
            sceneHighscore = new Scene(listPane);
            stage.setResizable(false);

            stage.setScene(sceneHighscore);
            stage.setTitle("Score board");

        } catch (IOException e) {
            System.out.println("Top-lista näkymän lataamisessa tapahtui virhe!");
        }
    }

    /**
     * alustaa GameBoardScenen eli pelinäkymän
     */
    public void setGameBoardScene() {

        try {
            FXMLLoader gameboardLoader = new FXMLLoader(getClass().getResource("/fxml/GameBoardView.fxml"));
            Parent gameboardPane = null;
            gameboardPane = gameboardLoader.load();
            GameBoardViewController gameBoardSceneController = gameboardLoader.getController();
            gameBoardSceneController.setApplication(this);
            sceneGameBoard = new Scene(gameboardPane);

            stage.setScene(sceneGameBoard);
            stage.setResizable(false);
            stage.setTitle("Let's play!");

        } catch (IOException e) {
            System.out.println("Pelialueen lataamisessa tapahtui virhe!");
        }
    }

    /**
     * @param ps asettaa PlayerService olioksi parametrina saadun PlayerServicen
     */
    public void setPlayerService(PlayerService ps) {

        this.ps = ps;
    }

    /**
     * @return palauttaa PlayerServicen
     */
    public PlayerService ps() {

        return this.ps;
    }

    /**
     * Pitää tiedossa pelaajan viimeksi saadut pisteet
     *
     * @param points pisteet
     */
    public void setLastScore(int points) {

        this.lastPoints = points;
    }

    /**
     * Palauttaa pelaajan viimeksi saadut pisteet
     *
     * @return pisteet
     */
    public int getLastPoints() {

        return this.lastPoints;
    }

    /**
     * asettaa reunojen boolean arvon sen mukaan, halusi käyttäjä ne vai ei
     *
     * @param result boolean arvo reunoille
     */
    public void setBorders(boolean result) {

        this.borders = result;
    }

    /**
     * asettaa teeman käyttäjän valitseman teeman mukaan
     *
     * @param themeNumber teeman numero
     */
    public void setTheme(String themeNumber) {

        this.theme = themeNumber.substring(0, 1);
    }
}