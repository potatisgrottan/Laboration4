package se.kth.olof.beyar.labb4.model;

import java.net.URL;
import javafx.scene.image.Image;

public class PictureModel
{

    private Image image;
    private int colorFilterSwitch;

    public PictureModel() {
        // Ta bort hårdkodningen
        setImageFromPath("/images/skull_ct.png");
        this.colorFilterSwitch = 0;
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

    public int getColorFilterSwitch() {
        return colorFilterSwitch;
    }

    public void incrementColorFilterSwitch() {
        if (colorFilterSwitch == 3) colorFilterSwitch = 0;
        else colorFilterSwitch++;
    }
}
