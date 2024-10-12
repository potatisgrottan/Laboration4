package se.kth.olof.beyar.labb4;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class MatrixImageConverter {

    public static WritableImage intMatrixToImage(int[][] imageMatrix){
        int height = imageMatrix.length;
        int width = imageMatrix[0].length;
        WritableImage image = new WritableImage(width,height);
        PixelWriter pixelWriter = image.getPixelWriter();

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                pixelWriter.setArgb(x,y,imageMatrix[y][x]);
            }
        }

        return image;
    }

    public static int[][] imageToIntMatrix(Image image){
        int height = (int)image.getHeight();
        int width = (int)image.getWidth();
        int[][] pictureMatrix = new int[height][width];
        PixelReader pixelReader = image.getPixelReader();
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                pictureMatrix[y][x]=pixelReader.getArgb(x,y);
            }
        }
        return pictureMatrix;
    }
}
