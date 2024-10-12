package se.kth.olof.beyar.labb4.model;

import se.kth.olof.beyar.labb4.controller.IProcessor;

public class ImageInvertColor implements IProcessor {

    public ImageInvertColor(){}
    @Override
    public int[][] processImage(int[][] originalImage) {
        int height = originalImage.length;
        int width = originalImage[0].length;
        int[][] processedImage = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int argb=originalImage[y][x];

                int a = 0xff & (argb >> 24);
                int r = 0xff & (argb >> 16);
                int g = 0xff & (argb >> 8);
                int b = 0xff & argb;

                r = 255-r;
                g = 255-g;
                b = 255-b;

                int invertedArgb = (a<<24) | (r<<16) | (g<<8) | b;
                processedImage[y][x]=invertedArgb;
            }
        }
        return processedImage;
    }
}
