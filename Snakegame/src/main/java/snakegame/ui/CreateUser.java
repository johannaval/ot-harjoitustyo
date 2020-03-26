
package snakegame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


class CreateUser {
    
   

    public CreateUser() {
    }

    public Parent getNakyma(){
        
        GridPane asettelu = new GridPane();

        Label nimiOhje = new Label("Write your user name (contains more than 3 letters)");
        TextField nimiKentta = new TextField();
        Label salasanaOhje = new Label("Write your password");
        PasswordField salasanaKentta = new PasswordField();

        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(10, 10, 10, 10));

        Button lisaanappi = new Button("Create ");

        asettelu.add(nimiOhje, 0, 0);
        asettelu.add(nimiKentta, 0, 1);
        asettelu.add(salasanaOhje, 0, 2);
        asettelu.add(salasanaKentta, 0, 3);
        asettelu.add(lisaanappi, 0, 4);
        

        lisaanappi.setOnMouseClicked((event) -> {
            String nimi = nimiKentta.getText();
            String salasana = salasanaKentta.getText();

            LogIn.getNakyma(); // ei toimi

            nimiKentta.clear();
            salasanaKentta.clear();
            
        });

        return asettelu;
    }

    
}
