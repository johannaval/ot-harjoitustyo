package snakegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

class LogIn {

    public LogIn() {
    }

    public static Parent getNakyma() {

        GridPane asettelu = new GridPane();

        Label nimiOhje = new Label("User name");
        TextField nimiKohta = new TextField();

        Label salasanaOhje = new Label("Password");
        PasswordField salasanaKohta = new PasswordField();
        Label virheviesti = new Label("");

        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(10, 10, 10, 10));
        

        Button lisaanappi = new Button("Log in");

        asettelu.add(nimiOhje, 0, 0);
        asettelu.add(nimiKohta, 0, 1);
        asettelu.add(salasanaOhje, 0, 2);
        asettelu.add(salasanaKohta, 0, 3);
        asettelu.add(lisaanappi, 0, 4);
        asettelu.add(virheviesti, 0, 5);

        lisaanappi.setOnMouseClicked((event) -> {
            
             if (!salasanaKohta.getText().trim().equals("ABC")) {
              virheviesti.setText("Rekisteröidy ensin!");
          }
            String nimi = nimiKohta.getText();
            String salasana = salasanaKohta.getText();
            nimiKohta.clear();
            salasanaKohta.clear();
        });

        return asettelu;
    }

}
