package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.utils.FileIO;

public class MenuModel {

    private ImageView imageView;
    private boolean imageUploaded;

    public MenuModel() {
        this.imageView = new ImageView();
        this.imageUploaded = false;
    }

    public void openAndReadFile(Stage stage) {
        imageView = FileIO.openFile(stage);

        if (imageView.getImage() != null)
            imageUploaded = true;
        else
            imageUploaded = false;
    }

    public void saveFile(Image image) {
        FileIO.saveFile(image);
    }

    public ImageView getChosenImageViewFromMenu() {
        return imageView;
    }

    public boolean isImageUploaded()
    {
        return imageUploaded;
    }
}
