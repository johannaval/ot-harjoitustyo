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
    private Scene nakymaLogin;
    private Scene nakymaCreateNew;
    private Scene nakymaGame;
    private Scene nakymatoplista;
    private Scene nakymaGameBoard;
    private PlayerService ps;


    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;

        stage.setTitle("Snake game");
        setloginScene();
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
        nakymaLogin = new Scene(loginPane);

        FXMLLoader newUserSceneLoader = new FXMLLoader(getClass().getResource("/fxml/CreateNew.fxml"));
        Parent newUserPane = newUserSceneLoader.load();
        CreateNewController newUserSceneController = newUserSceneLoader.getController();
        newUserSceneController.setApplication(this);
        nakymaCreateNew = new Scene(newUserPane);

        FXMLLoader gameboardLoader = new FXMLLoader(getClass().getResource("/fxml/GameBoardView.fxml"));
        Parent gameboardPane = gameboardLoader.load();
        GameBoardViewController gameBoardSceneController = gameboardLoader.getController();
        gameBoardSceneController.setApplication(this);
        nakymaGameBoard = new Scene(gameboardPane);

    }

    public void setloginScene() {

        stage.setScene(nakymaLogin);
        stage.setTitle("Log in");
    }

    public void setNewUserScene() {

        stage.setScene(nakymaCreateNew);
        stage.setTitle("Create new");
    }

    public void setGameScene() throws IOException {

        FXMLLoader gameStartLoader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
        Parent gamePane = gameStartLoader.load();
        GameViewController gameSceneController = gameStartLoader.getController();
        gameSceneController.setApplication(this);
        nakymaGame = new Scene(gamePane);

        stage.setScene(nakymaGame);
        stage.setTitle("Start!");

    }

    public void setTopListScene() throws IOException {

        FXMLLoader toplistLoader = new FXMLLoader(getClass().getResource("/fxml/TopListView.fxml"));
        Parent listPane = toplistLoader.load();
        TopListViewController listSceneController = toplistLoader.getController();
        listSceneController.setApplication(this);
        nakymatoplista = new Scene(listPane);

        stage.setScene(nakymatoplista);
        stage.setTitle("Score board");
    }

    public void setGameBoardScene() {

        stage.setScene(nakymaGameBoard);
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