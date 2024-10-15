package se.kth.olof.beyar.labb4.model;

import java.net.URL;
import javafx.scene.image.Image;

/**
 * A model class representing an image in the application.
 */
public class PictureModel
{
    private Image image;

    /**
     * Constructs a PictureModel object and sets an initial image from a predefined path.
     */
    public PictureModel() {
        setImageFromPath("/images/skull_ct.png");
    }

    /**
     * Sets the image from a specified path.
     *
     * @param path The path to the image file.
     */
    public void setImageFromPath(String path) {
        URL resource = this.getClass().getResource(path);
        assert resource != null;

        String imagePath = resource.toString();
        image = new Image(imagePath);
    }

    /**
     * Sets the image for the model.
     *
     * @param image The image to be set.
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Gets the image from the model.
     *
     * @return The image currently set in the model.
     */
    public Image getImage() {
        return image;
    }
}
