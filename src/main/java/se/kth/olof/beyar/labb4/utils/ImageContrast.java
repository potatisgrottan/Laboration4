package se.kth.olof.beyar.labb4.utils;

/**
 * A utility class that adjusts the contrast of an image using window and level values.
 */
public class ImageContrast implements IProcessor {
    private int valueWindowSlider;
    private int valueLevelSlider;

    /**
     * Constructs an ImageContrast object with the specified window and level values.
     *
     * @param valueWindowSlider The window value for contrast adjustment.
     * @param valueLevelSlider The level value for contrast adjustment.
     */
    public ImageContrast(int valueWindowSlider, int valueLevelSlider){
        this.valueWindowSlider=valueWindowSlider;
        this.valueLevelSlider=valueLevelSlider;
    }

    /**
     * Processes the original image to adjust its contrast based on the window and level values.
     *
     * @param originalImage The original image represented as a 2D array of ARGB values.
     * @return A 2D array of ARGB values representing the processed image with adjusted contrast.
     */
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

                if(max-min == 0){
                    r = (r-min) * 255;
                    g = (g-min) * 255;
                    b = (b-min) * 255;
                }
                else{
                    r = clamp((r-min) * 255 / (max-min));
                    g = clamp((g-min) * 255 / (max-min));
                    b = clamp((b-min) * 255 / (max-min));
                }

                int contrastArgb = (a<<24) | (r<<16) | (g<<8) | b;
                processedImage[y][x]=contrastArgb;
            }
        }

        return processedImage;
    }

    /**
     * Clamps the value to the range 0-255.
     *
     * @param value The value to be clamped.
     * @return The clamped value within the range 0-255.
     */
    private int clamp(int value){
        return Math.max(0, Math.min(255,value));
    }
}
