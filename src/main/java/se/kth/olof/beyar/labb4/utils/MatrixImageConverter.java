package se.kth.olof.beyar.labb4.utils;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 * Utility class for converting between int matrices and JavaFX Images.
 */
public class MatrixImageConverter {

    /**
     * Converts a matrix of ARGB integer values to a WritableImage.
     *
     * @param imageMatrix The matrix of ARGB values representing the image.
     * @return A WritableImage created from the given int matrix.
     * */
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

    /**
     * Converts a matrix of ARGB integer values to a WritableImage.
     *
     * @param image the image that is converted to a matrix.
     * @return A matrix containing the argb values of the image.
     * */
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
