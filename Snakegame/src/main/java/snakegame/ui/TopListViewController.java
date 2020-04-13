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
    public TableView<Player> TopList;

    @FXML
    public TableColumn<Player, String> username;

    @FXML
    public TableColumn<Player, Integer> highscore;

    private String tableUrl = "jdbc:sqlite:testsql.db";

    ObservableList<Player> topList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        AP.setFocusTraversable(true);

        try {
            Connection db = DriverManager.getConnection(tableUrl);
            ResultSet rs = db.createStatement().executeQuery("select * from Players");

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
    }

    @FXML
    private void handleExit(ActionEvent event) {
        PlayerService ps = application.ps();
        ps.logout();
        application.setloginScene();
    }

    @FXML
    private void handleBackToGame(ActionEvent event) throws IOException {
        application.setGameScene();
    }

}
