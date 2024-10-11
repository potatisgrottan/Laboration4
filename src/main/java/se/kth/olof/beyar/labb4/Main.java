package se.kth.olof.beyar.labb4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initierar och visar huvudscenen för bildbehandlingsapplikationen.
     * Denna metod skapar användargränssnittet genom att:
     * 1. Ladda en exempelbild (skull_ct.png).
     * 2. Skapa en uppdateringsknapp.
     * 3. Placera bilden och knappen i en HBox-layout.
     * 4. Konfigurera och visa huvudfönstret.
     *
     * @param stage Huvudscenen för applikationen.
     */
    @Override
    public void start(Stage stage) {
        // Laddar exempelbilden från resursmappen
        URL resource = this.getClass().getResource("/images/skull_ct.png");
        assert resource != null;
        Image image = new Image(resource.toString());

        // Skapar och konfigurerar uppdateringsknappen
        Button updateButton = new Button("Update");
        FlowPane pane = new FlowPane();
        pane.setAlignment(Pos.BOTTOM_LEFT);
        pane.setPadding(new Insets(0, 0, 100, 10));
        pane.getChildren().add(updateButton);
        updateButton.setOnAction(_ -> { System.out.println("Update button was clicked!"); });

        // Skapar en ImageView för att visa bilden
        ImageView firstView = new ImageView();
        firstView.setImage(image);

        // Skapar horizontell split mellan två vyer och lägger till komponenter
        HBox hbox = new HBox();
        hbox.getChildren().add(pane);
        hbox.getChildren().add(firstView);

        // Ny text område längst ner
        FlowPane textPane = new FlowPane();
        textPane.setPadding(new Insets(25));
        textPane.getChildren().add(new Text("Hello, world!"));

        // Skapar en vertikal split
        VBox vbox = new VBox();
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(textPane);

        // Konfigurerar och visar huvudscenen
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        stage.setTitle("Image editor");
        stage.show();
    }
}