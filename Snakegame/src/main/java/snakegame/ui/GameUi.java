package snakegame.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;

public class GameUi extends Application {

    private Stage stage;
    private Scene nakymaLogin;
    private Scene nakymaCreateNew;
    private Scene nakymaGame;
    private Scene nakymatoplista;
    private Scene nakymaGameBoard;


    @Override
    public void start(Stage stage) throws IOException {

        this.stage = stage;

        stage.setTitle("Ma-to-pe-li");
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

        FXMLLoader gameStartLoader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
        Parent gamePane = gameStartLoader.load();
        GameViewController gameSceneController = gameStartLoader.getController();
        gameSceneController.setApplication(this);
        nakymaGame = new Scene(gamePane);

        FXMLLoader gameboardLoader = new FXMLLoader(getClass().getResource("/fxml/GameBoardView.fxml"));
        Parent gameboardPane = gameboardLoader.load();
        GameBoardViewController gameBoardSceneController = gameboardLoader.getController();
        gameBoardSceneController.setApplication(this);
        nakymaGameBoard = new Scene(gameboardPane);

        FXMLLoader toplistLoader = new FXMLLoader(getClass().getResource("/fxml/TopListView.fxml"));
        Parent listPane = toplistLoader.load();
        TopListViewController listSceneController = toplistLoader.getController();
        listSceneController.setApplication(this);
        nakymatoplista = new Scene(listPane);

    }

    public void setloginScene() {

        stage.setScene(nakymaLogin);
        stage.setTitle("Log in");
    }

    public void setNewUserScene() {

        stage.setScene(nakymaCreateNew);
        stage.setTitle("Create new");
    }

    public void setGameScene() {

        stage.setScene(nakymaGame);
        stage.setTitle("Start!");
    }

    public void setTopListScene() {

        stage.setScene(nakymatoplista);
        stage.setTitle("Top10");
    }
    public void setGameBoardScene() {

        stage.setScene(nakymaGameBoard);
        stage.setResizable(false);
        stage.setTitle("Let's play!");

    }
}
