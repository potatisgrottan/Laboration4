package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageInvertColor {

    private WritableImage wImage;
    private Image image;

    public ImageInvertColor(Image src, WritableImage wsrc){
        image=src;
        wImage=wsrc;
        PixelWriter pixelWriter = wImage.getPixelWriter();


        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixelReader().getColor(x,y);

                Color invertedColor = new Color(
                        1.0-color.getRed(),1.0- color.getGreen(),
                        1.0-color.getBlue(), color.getOpacity());

                pixelWriter.setColor(x,y,invertedColor);

            }
        }

    }

    public Image getImage() {
        return wImage;
    }
}
