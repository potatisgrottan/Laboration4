package se.kth.olof.beyar.labb4.model;


import se.kth.olof.beyar.labb4.controller.IProcessor;

public class ImageContrast implements IProcessor {
    int valueWindowSlider;
    int valueLevelSlider;

    public ImageContrast(int valueWindowSlider, int valueLevelSlider){
        this.valueWindowSlider=valueWindowSlider;
        this.valueLevelSlider=valueLevelSlider;
    }

    @Override
    public int[][] processImage(int[][] originalImage) {
        int height = originalImage.length;
        int width = originalImage[0].length;
        int[][] processedImage = new int[height][width];

        int min = valueLevelSlider - valueWindowSlider/2;
        int max = valueLevelSlider + valueWindowSlider/2;


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb=originalImage[y][x];

                int a = 0xff & (argb >> 24);
                int r = 0xff & (argb >> 16);
                int g = 0xff & (argb >> 8);
                int b = 0xff & argb;

                r = (r-min) * 255 / (max-min);
                g = (g-min) * 255 / (max-min) ;
                b = (b-min) * 255 / (max-min);

                int contrastArgb = (a<<24) | (r<<16) | (g<<8) | b;
                processedImage[y][x]=contrastArgb;
            }
        }

        return processedImage;
    }
}
