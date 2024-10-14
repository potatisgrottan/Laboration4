package se.kth.olof.beyar.labb4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.controller.AppController;
import se.kth.olof.beyar.labb4.model.MenuModel;
import se.kth.olof.beyar.labb4.model.PictureModel;
import se.kth.olof.beyar.labb4.model.StatusModel;
import se.kth.olof.beyar.labb4.view.MenuView;
import se.kth.olof.beyar.labb4.view.PictureView;
import se.kth.olof.beyar.labb4.view.StatusView;

public class TestMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuModel menuModel = new MenuModel();
        MenuView menuView = new MenuView();

        PictureModel pictureModel = new PictureModel();
        PictureView pictureView = new PictureView();

        StatusModel statusModel = new StatusModel();
        StatusView statusView = new StatusView();

        AppController app = new AppController(menuModel,menuView,stage,
                pictureModel,pictureView,statusModel,statusView);

        app.reRenderApp();
    }
}
