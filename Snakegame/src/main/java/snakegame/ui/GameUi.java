package snakegame.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import java.util.Properties;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class GameUi extends Application {

    @Override
    public void start(Stage ikkuna) {

        LogIn loginNakyma = new LogIn();
        CreateUser createNakyma = new CreateUser();

        BorderPane asettelu = new BorderPane();
        HBox valikko = new HBox();
        valikko.setPadding(new Insets(40, 40, 40, 40));
        valikko.setSpacing(10);

        Button rekisteroidy = new Button("Create new user");
        Button rekisteroidytty = new Button("Creattte");

        valikko.getChildren().addAll(rekisteroidy);
        asettelu.setTop(valikko);

        rekisteroidy.setOnAction((event) -> asettelu.setCenter(createNakyma.getNakyma()));
        rekisteroidytty.setOnAction((eventt) -> asettelu.setCenter(loginNakyma.getNakyma()));

        asettelu.setCenter(loginNakyma.getNakyma());

        Scene nakyma = new Scene(asettelu, 700, 600);

        ikkuna.setScene(nakyma);
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
        // ...
    }
}
