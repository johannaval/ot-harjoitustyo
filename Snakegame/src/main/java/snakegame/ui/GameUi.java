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
    public boolean borders = false;
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
     *
     * @throws Exception heittää poikkeuksen, jos lataaminen epäonnistuu
     */
    @Override
    public void init() throws Exception {

        FXMLLoader loginSceneLoader = new FXMLLoader(getClass().getResource("/fxml/LogInView.fxml"));
        Parent loginPane = loginSceneLoader.load();
        LogInViewController loginSceneController = loginSceneLoader.getController();
        loginSceneController.setApplication(this);
        sceneLogin = new Scene(loginPane);

        FXMLLoader newUserSceneLoader = new FXMLLoader(getClass().getResource("/fxml/CreateNew.fxml"));
        Parent newUserPane = newUserSceneLoader.load();
        CreateNewController newUserSceneController = newUserSceneLoader.getController();
        newUserSceneController.setApplication(this);
        sceneCreateNew = new Scene(newUserPane);

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
     *
     * @throws IOException heittää I/O-poikkeuksen, jos lataaminen ei onnistu
     */
    public void setGameScene() throws IOException {

        FXMLLoader gameStartLoader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
        Parent gamePane = gameStartLoader.load();
        GameViewController gameSceneController = gameStartLoader.getController();
        gameSceneController.setApplication(this);
        sceneGame = new Scene(gamePane);
        stage.setResizable(false);

        stage.setScene(sceneGame);
        stage.setTitle("Start!");

    }

    /**
     * alustaa TopListScenen eli top-lista näkymän
     *
     * @throws IOException heittää I/O-poikkeuksen, jos lataaminen ei onnistu
     */
    public void setTopListScene() throws IOException {

        FXMLLoader toplistLoader = new FXMLLoader(getClass().getResource("/fxml/TopListView.fxml"));
        Parent listPane = toplistLoader.load();
        TopListViewController listSceneController = toplistLoader.getController();
        listSceneController.setApplication(this);
        sceneHighscore = new Scene(listPane);
        stage.setResizable(false);

        stage.setScene(sceneHighscore);
        stage.setTitle("Score board");
    }

    /**
     * alustaa GameBoardScenen eli pelinäkymän
     *
     * @throws IOException heittää I/O-poikkeuksen, jos lataaminen ei onnistu
     */
    public void setGameBoardScene() throws IOException {

        FXMLLoader gameboardLoader = new FXMLLoader(getClass().getResource("/fxml/GameBoardView.fxml"));
        Parent gameboardPane = gameboardLoader.load();
        GameBoardViewController gameBoardSceneController = gameboardLoader.getController();
        gameBoardSceneController.setApplication(this);
        sceneGameBoard = new Scene(gameboardPane);

        stage.setScene(sceneGameBoard);
        stage.setResizable(false);
        stage.setTitle("Let's play!");

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
     * @param points asettaa pelaajan viimeiset pisteet
     */
    public void setLastScore(int points) {

        this.lastPoints = points;
    }

    /**
     * @return palauttaa pelaajan viimeiset pisteet
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
     * @param themeNumber teemanumero
     */
    public void setTheme(String themeNumber) {

        this.theme = themeNumber.substring(0, 1);
    }
}