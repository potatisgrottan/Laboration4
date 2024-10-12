package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageGrayScale {

    private WritableImage grayScaleImage;
    private Image image;
    private double grayColor;

    public ImageGrayScale(Image src, WritableImage wsrc){
        image=src;
        grayScaleImage=wsrc;
        PixelWriter pixelWriter = grayScaleImage.getPixelWriter();


        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = image.getPixelReader().getColor(x,y);

                grayColor= (color.getBlue()+color.getRed()+color.getGreen())/3;

                Color grayScaleColor = new Color(grayColor, grayColor,
                        grayColor, color.getOpacity());

                pixelWriter.setColor(x,y,grayScaleColor);

            }
        }

    }
}
