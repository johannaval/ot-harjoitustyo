package snakegame.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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


public class TopListViewController implements Initializable {

    private GameUi application;

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

    private String tableUrl = "jdbc:sqlite:testsql.db";

    ObservableList<Player> topList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AP.setFocusTraversable(true);

        try {
            Connection db = DriverManager.getConnection(tableUrl);
            ResultSet rs = db.createStatement().executeQuery("select * from Players order by highscore desc limit 10");

            while (rs.next()) {

                if (rs.getInt("highscore") > 0) {
                    topList.add(new Player(rs.getString("username"), rs.getString("password"), rs.getInt("highscore")));
                }
            }
            db.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        highscore.setCellValueFactory(new PropertyValueFactory<>("highscore"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        TopList.setItems(topList);
    }

    public void setApplication(GameUi application) {

        this.application = application;
        lastScore.setText("You got " + application.getLastPoints() + " points!");
    }

    @FXML
    private void handleExit(ActionEvent event) throws SQLException {

        PlayerService ps = application.ps();
        ps.logout();
        application.setLogInScene();
    }

    @FXML
    private void handleBackToGame(ActionEvent event) throws IOException {

        application.setGameScene();
    }

}
