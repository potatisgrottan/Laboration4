package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.utils.FileIO;

/**
 * A model class representing the menu functionality for image handling.
 */
public class MenuModel {

    private ImageView imageView;
    private boolean imageUploaded;

    /**
     * Constructs a MenuModel object, initializing the image view and upload status.
     */
    public MenuModel() {
        this.imageView = new ImageView();
        this.imageUploaded = false;
    }

    /**
     * Opens a file chooser dialog to allow the user to select and read an image file.
     *
     * @param stage The stage on which the file chooser dialog will be displayed.
     */
    public void openAndReadFile(Stage stage) {
        imageView = FileIO.openFile(stage);

        if (imageView.getImage() == null)
            imageUploaded = false;
        else
            imageUploaded = true;
    }

    /**
     * Saves the given image to a file.
     *
     * @param image The image to be saved.
     */
    public void saveFile(Image image) {
        FileIO.saveFile(image);
    }

    /**
     * Gets the chosen ImageView from the menu.
     *
     * @return The ImageView representing the chosen image.
     */
    public ImageView getChosenImageViewFromMenu() {
        return imageView;
    }

    /**
     * Checks if an image has been uploaded.
     *
     * @return true if an image has been uploaded, false otherwise.
     */
    public boolean isImageUploaded()
    {
        return imageUploaded;
    }
}
