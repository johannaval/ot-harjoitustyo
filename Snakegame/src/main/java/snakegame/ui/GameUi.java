package snakegame.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import snakegame.domain.PlayerService;


public class GameUi extends Application {

    private Stage stage;
    private Scene sceneLogin;
    private Scene sceneCreateNew;
    private Scene sceneGame;
    private Scene sceneHighscore;
    private Scene sceneGameBoard;
    private PlayerService ps;


    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;

        stage.setTitle("Snake game");
        setLogInScene();
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void stop() {

    }

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

        FXMLLoader gameboardLoader = new FXMLLoader(getClass().getResource("/fxml/GameBoardView.fxml"));
        Parent gameboardPane = gameboardLoader.load();
        GameBoardViewController gameBoardSceneController = gameboardLoader.getController();
        gameBoardSceneController.setApplication(this);
        sceneGameBoard = new Scene(gameboardPane);

    }

    public void setLogInScene() {

        stage.setScene(sceneLogin);
        stage.setTitle("Log in");
    }

    public void setNewUserScene() {

        stage.setScene(sceneCreateNew);
        stage.setTitle("Create new");
    }

    public void setGameScene() throws IOException {

        FXMLLoader gameStartLoader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
        Parent gamePane = gameStartLoader.load();
        GameViewController gameSceneController = gameStartLoader.getController();
        gameSceneController.setApplication(this);
        sceneGame = new Scene(gamePane);

        stage.setScene(sceneGame);
        stage.setTitle("Start!");

    }

    public void setTopListScene() throws IOException {

        FXMLLoader toplistLoader = new FXMLLoader(getClass().getResource("/fxml/TopListView.fxml"));
        Parent listPane = toplistLoader.load();
        TopListViewController listSceneController = toplistLoader.getController();
        listSceneController.setApplication(this);
        sceneHighscore = new Scene(listPane);

        stage.setScene(sceneHighscore);
        stage.setTitle("Score board");
    }

    public void setGameBoardScene() {

        stage.setScene(sceneGameBoard);
        stage.setResizable(false);
        stage.setTitle("Let's play!");

    }

    public void setPlayerService(PlayerService ps) {
        this.ps = ps;
    }

    public PlayerService ps(){
        return this.ps;
    }
}