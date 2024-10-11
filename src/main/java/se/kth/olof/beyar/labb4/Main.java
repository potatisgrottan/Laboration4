package se.kth.olof.beyar.labb4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;

import java.net.URL;

public class Main extends Application {

    private Canvas canvas;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        URL resource = this.getClass().getResource("/images/skull_ct.png");
        assert resource != null;
        Image image = new Image(resource.toString());

        ImageView firstView = new ImageView();
        firstView.setImage(image);

        HBox root = new HBox();
        root.getChildren().add(firstView);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setTitle("Image editor");
        stage.show();
    }
}