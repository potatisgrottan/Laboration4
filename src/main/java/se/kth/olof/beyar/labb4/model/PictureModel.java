package se.kth.olof.beyar.labb4.model;

import java.net.URL;
import javafx.scene.image.Image;

public class PictureModel
{
    private Image image;

    public PictureModel() {
        setImageFromPath("/images/skull_ct.png");
    }

    public void setImageFromPath(String path) {
        URL resource = this.getClass().getResource(path);
        assert resource != null;

        String imagePath = resource.toString();
        image = new Image(imagePath);
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
