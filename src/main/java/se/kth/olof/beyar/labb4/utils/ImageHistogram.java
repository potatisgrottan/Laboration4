package se.kth.olof.beyar.labb4.utils;

import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

// Code from https://java-buddy.blogspot.com/2015/07/display-images-histogram-on-javafx.html
/**
 * A utility class that generates histograms for an image's alpha, red, green, and blue color channels.
 */
public class ImageHistogram {
    private Image image;

    private long alpha[] = new long[256];
    private long red[] = new long[256];
    private long green[] = new long[256];
    private long blue[] = new long[256];

    XYChart.Series seriesAlpha;
    XYChart.Series seriesRed;
    XYChart.Series seriesGreen;
    XYChart.Series seriesBlue;

    private boolean success;

    /**
     * Constructs an ImageHistogram for the given image.
     *
     * @param src The source image for which the histogram is to be generated.
     */
    public ImageHistogram(Image src) {
        image = src;
        success = false;

        //init
        for (int i = 0; i < 256; i++) {
            alpha[i] = red[i] = green[i] = blue[i] = 0;
        }

        PixelReader pixelReader = image.getPixelReader();
        if (pixelReader == null) {
            return;
        }

        //count pixels
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int argb = pixelReader.getArgb(x, y);
                int a = (0xff & (argb >> 24));
                int r = (0xff & (argb >> 16));
                int g = (0xff & (argb >> 8));
                int b = (0xff & argb);

                alpha[a]++;
                red[r]++;
                green[g]++;
                blue[b]++;

            }
        }

        seriesAlpha = new XYChart.Series();
        seriesRed = new XYChart.Series();
        seriesGreen = new XYChart.Series();
        seriesBlue = new XYChart.Series();
        seriesAlpha.setName("alpha");
        seriesRed.setName("red");
        seriesGreen.setName("green");
        seriesBlue.setName("blue");

        //copy alpha[], red[], green[], blue[]
        //to seriesAlpha, seriesRed, seriesGreen, seriesBlue
        for (int i = 0; i < 256; i++) {
            seriesAlpha.getData().add(new XYChart.Data(String.valueOf(i), alpha[i]));
            seriesRed.getData().add(new XYChart.Data(String.valueOf(i), red[i]));
            seriesGreen.getData().add(new XYChart.Data(String.valueOf(i), green[i]));
            seriesBlue.getData().add(new XYChart.Data(String.valueOf(i), blue[i]));
        }

        success = true;
    }

    /**
     * Checks if the histogram generation was successful.
     *
     * @return true if the histogram was successfully generated, false otherwise.
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Gets the series representing the alpha channel histogram.
     *
     * @return The series for the alpha channel histogram.
     */
    public XYChart.Series getSeriesAlpha() {
        return seriesAlpha;
    }

    /**
     * Gets the series representing the red channel histogram.
     *
     * @return The series for the red channel histogram.
     */
    public XYChart.Series getSeriesRed() {
        return seriesRed;
    }

    /**
     * Gets the series representing the green channel histogram.
     *
     * @return The series for the green channel histogram.
     */
    public XYChart.Series getSeriesGreen() {
        return seriesGreen;
    }

    /**
     * Gets the series representing the blue channel histogram.
     *
     * @return The series for the blue channel histogram.
     */
    public XYChart.Series getSeriesBlue() {
        return seriesBlue;
    }
}
