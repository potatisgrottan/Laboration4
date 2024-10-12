package se.kth.olof.beyar.labb4.model;

import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

// Code from https://java-buddy.blogspot.com/2015/07/display-images-histogram-on-javafx.html
@SuppressWarnings("unchecked")
public class ImageHistogram<K, V> {
    private Image image;

    private long alpha[] = new long[256];
    private long red[] = new long[256];
    private long green[] = new long[256];
    private long blue[] = new long[256];

    XYChart.Series<K, V> seriesAlpha;
    XYChart.Series<K, V> seriesRed;
    XYChart.Series<K, V> seriesGreen;
    XYChart.Series<K, V> seriesBlue;

    private boolean success;

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

        seriesAlpha = new XYChart.Series<K, V>();
        seriesRed = new XYChart.Series<K, V>();
        seriesGreen = new XYChart.Series<K, V>();
        seriesBlue = new XYChart.Series<K, V>();
        seriesAlpha.setName("alpha");
        seriesRed.setName("red");
        seriesGreen.setName("green");
        seriesBlue.setName("blue");

        //copy alpha[], red[], green[], blue[]
        //to seriesAlpha, seriesRed, seriesGreen, seriesBlue
        for (int i = 0; i < 256; i++) {
            K key = (K) String.valueOf(i);
            V alphaValue = (V) Long.valueOf(alpha[i]);
            V redValue = (V) Long.valueOf(red[i]);
            V greenValue = (V) Long.valueOf(green[i]);
            V blueValue = (V) Long.valueOf(blue[i]);

            seriesAlpha.getData().add(new XYChart.Data<>(key, alphaValue));
            seriesRed.getData().add(new XYChart.Data<>(key, redValue));
            seriesGreen.getData().add(new XYChart.Data<>(key, greenValue));
            seriesBlue.getData().add(new XYChart.Data<>(key, blueValue));
        }

        success = true;
    }

    public boolean isSuccess() {
        return success;
    }

    public XYChart.Series<K, V> getSeriesAlpha() {
        return seriesAlpha;
    }

    public XYChart.Series<K, V> getSeriesRed() {
        return seriesRed;
    }

    public XYChart.Series<K, V> getSeriesGreen() {
        return seriesGreen;
    }

    public XYChart.Series<K, V> getSeriesBlue() {
        return seriesBlue;
    }
}
