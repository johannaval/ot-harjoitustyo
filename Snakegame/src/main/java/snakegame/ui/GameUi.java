package snakegame.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.util.Properties;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import snakegame.domain.Player;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;

public class GameUi extends Application {

    private Stage ikkuna;
    private Scene nakymaLogin;
    private Scene nakymaCreateNew;
    private Parent root;
    private Scene nakymaGame;
    private Scene nakymatoplista;

    @Override
    public void start(Stage ikkuna) throws IOException {

        this.ikkuna = ikkuna;

        ikkuna.setTitle("Ma-to-pe-li");
        setloginScene();
        ikkuna.show();
    }

    public static void main(String[] args) {

        launch(args);
        System.out.println("Tervetuloa");
    }

    @Override
    public void stop() {
        System.out.println("Heippa! ");
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

        FXMLLoader gameboardLoader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
        Parent gamePane = gameboardLoader.load();
        GameViewController gameSceneController = gameboardLoader.getController();
        gameSceneController.setApplication(this);
        nakymaGame = new Scene(gamePane);

        FXMLLoader toplistLoader = new FXMLLoader(getClass().getResource("/fxml/TopListView.fxml"));
        Parent listPane = toplistLoader.load();
        TopListViewController listSceneController = toplistLoader.getController();
        listSceneController.setApplication(this);
        nakymatoplista = new Scene(listPane);

    }

    public void setloginScene() {
        ikkuna.setScene(nakymaLogin);
    }

    public void setNewUserScene() {
        ikkuna.setScene(nakymaCreateNew);
    }

    public void setGameScene() {
        ikkuna.setScene(nakymaGame);
    }
     public void setTopListScene() {
        ikkuna.setScene(nakymatoplista);
    
}
}
