package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.Image;

import java.net.URL;

public class ImageModel
{
    private Image image;
    private String imagePath;
    private int colorFilterSwitch;

    public ImageModel() {
        setImageFromPath("/images/skull_ct.png");
        this.colorFilterSwitch = 0;
    }

    public void setImageFromPath(String path)
    {
        URL resource = this.getClass().getResource(path);
        assert resource != null;

        imagePath = resource.toString();
        image = new Image(resource.toString());
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public Image getImage()
    {
        return image;
    }

    public int getColorFilterSwitch()
    {
        return colorFilterSwitch;
    }

    public void incrementColorFilterSwitch()
    {
        if (colorFilterSwitch == 3)
            colorFilterSwitch = 0;
        else
            colorFilterSwitch++;
    }
}
