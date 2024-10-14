package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.utils.FileIO;

public class MenuModel {

    private ImageView imageView;

    public MenuModel() {
        this.imageView = new ImageView();
    }

    public void openAndReadFile(Stage stage) {
        imageView = new ImageView();
        imageView = FileIO.openFile(stage);
    }

    public ImageView getChosenImageViewFromMenu() {
        return imageView;
    }
}
