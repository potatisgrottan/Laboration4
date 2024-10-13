package se.kth.olof.beyar.labb4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.controller.ImageController;
import se.kth.olof.beyar.labb4.controller.MenuController;
import se.kth.olof.beyar.labb4.controller.StatusController;
import se.kth.olof.beyar.labb4.model.*;
import se.kth.olof.beyar.labb4.view.ImagePreview;
import se.kth.olof.beyar.labb4.view.MenuView;
import se.kth.olof.beyar.labb4.view.StatusView;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        MenuModel menuModel = new MenuModel();
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(
            menuModel,
            menuView,
            stage
        );

        ImageModel imageModel = new ImageModel();
        ImagePreview imageView = new ImagePreview();
        ImageController imageController = new ImageController(
            imageModel,
            imageView
        );
        HBox manipulateImageField = imageController.displayControlAndImage();

        // Genom att anropa följande så kommer den plocka den nya valde bilden vi har valt i
        // fileChoosser och skicka till image modellen
        imageController.setImage(menuController.getChosenImageViewFromMenu().getImage());

        StatusModel statusModel = new StatusModel();
        StatusView statusView = new StatusView();
        StatusController statusController = new StatusController(
            statusModel,
            statusView
        );
        statusController.setStatusMsg("Histogram generated");

        // Skapar en vertikal uppdelning
        VBox vbox = new VBox();
        VBox.setVgrow(manipulateImageField, Priority.ALWAYS);
        vbox.getChildren().add(menuController.getMenuBar());
        vbox.getChildren().add(manipulateImageField);
        vbox.getChildren().add(statusController.buildStatusView());

        // Skapar en stackpane som vi kan lagra komponenterna ovanpå
        StackPane root = new StackPane();
        root.getChildren().add(vbox);

        // Konfigurerar och visar fönstret
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        stage.setTitle("Image Processing");
        stage.show();
    }
}
