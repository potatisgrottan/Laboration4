package se.kth.olof.beyar.labb4.view;

import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import se.kth.olof.beyar.labb4.controller.AppController;
import se.kth.olof.beyar.labb4.utils.ImageContrast;
import se.kth.olof.beyar.labb4.utils.ImageGrayScale;
import se.kth.olof.beyar.labb4.utils.ImageHistogram;
import se.kth.olof.beyar.labb4.utils.ImageInvertColor;
import se.kth.olof.beyar.labb4.utils.MatrixImageConverter;

import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicReference;

public class PictureView
{
    private WritableImage wImage;
    private int[][] ogPicture;
    private Slider windowSlider;
    private Slider levelSlider;
    private ImageContrast contrast;


    public PictureView() {
    }

    public ImageView createImagePreview(Image image)
    {
        // Skapar en ImageView för att visa bilden
        ogPicture = MatrixImageConverter.imageToIntMatrix(image);
        wImage = MatrixImageConverter.intMatrixToImage(ogPicture);
        ImageView firstView = new ImageView();
        firstView.setImage(image);
        FlowPane imageViewer = new FlowPane();
        imageViewer.getChildren().add(firstView);

        // Från PropertyBindingExample.java
        firstView.fitWidthProperty().bind(imageViewer.widthProperty());
        firstView.fitHeightProperty().bind(imageViewer.heightProperty());

        return firstView;
    }

    public FlowPane createHistogram(ImageHistogram imageHistogram, Image image, int colorFilterSwitch)
    {
        // Histogram, code from https://java-buddy.blogspot.com/2015/07/display-images-histogram-on-javafx.html
        Button updateButton = new Button("Update");
        ImageView imageDisplay = new ImageView();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> chartHistogram = new LineChart<>(xAxis, yAxis);
        chartHistogram.setCreateSymbols(false);


        updateButton.setOnAction(_ -> {
            imageDisplay.setImage(wImage);


            if (imageHistogram.isSuccess()) {
                chartHistogram.getData().clear();
                chartHistogram.getData().addAll(
                        //imageHistogram.getSeriesAlpha(),
                        imageHistogram.getSeriesRed(),
                        imageHistogram.getSeriesGreen(),
                        imageHistogram.getSeriesBlue()
                );
            }

            ogPicture = MatrixImageConverter.imageToIntMatrix(image);

           /* if (colorFilterSwitch == 0) {
                ogPicture = MatrixImageConverter.imageToIntMatrix(image);
            } else if (colorFilterSwitch == 1) {
                ImageGrayScale gray = new ImageGrayScale();
                ogPicture = gray.processImage(ogPicture);
            } else if (colorFilterSwitch == 2) {
                ImageInvertColor invert = new ImageInvertColor();
                ogPicture = invert.processImage(ogPicture);
            } else if (colorFilterSwitch == 3){
                ImageContrast con = new ImageContrast(150,50);
                ogPicture = con.processImage(ogPicture);
            } else {
                throw new IllegalStateException("We should not be able to increment to this level: " + colorFilterSwitch);
            }

            */

            //assert (ogPicture != null);
            wImage = MatrixImageConverter.intMatrixToImage(ogPicture);

            // Update the image view
            imageDisplay.setImage(wImage);
        });

        // Kör update första gången den renderas så man ser histogrammet
        updateButton.fire();

        // Sätter en marginal mellan update knappen och botten av fönstret
        FlowPane updateButtonBox = new FlowPane();
        // v: top, v1: right, v2: bottom, v3: left
        updateButtonBox.setPadding(new Insets(0, 0, 0, 20));
        updateButtonBox.getChildren().add(updateButton);

        FlowPane histogramViewer = new FlowPane();
        histogramViewer.getChildren().add(chartHistogram);
        histogramViewer.getChildren().add(updateButtonBox);

        // Skapa en grön ram runt vänstra sidan av fönstret
        BorderStroke borderStroke = new BorderStroke(
                Color.GREEN,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(2)
        );

        Border border = new Border(borderStroke);
        histogramViewer.setBorder(border);
        FlowPane.setMargin(histogramViewer, new Insets(15));

        return histogramViewer;
    }

    public FlowPane createContrast(Image image){
        windowSlider = new Slider(0,255,127);
        levelSlider = new Slider(0,255,127);

        windowSlider.setShowTickMarks(true);
        windowSlider.setShowTickLabels(true);
        windowSlider.setMajorTickUnit(55);
        windowSlider.setPrefSize(350, 50);

        levelSlider.setShowTickMarks(true);
        levelSlider.setShowTickLabels(true);
        levelSlider.setMajorTickUnit(55);
        levelSlider.setPrefSize(350, 50);

        ogPicture = MatrixImageConverter.imageToIntMatrix(image);
        windowSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            //System.out.println(observableValue + " " + oldValue + " " + newValue);
            handlecontrast();

        });

        levelSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            //System.out.println(observableValue + " " + oldValue + " " + newValue);
            handlecontrast();

        });

        FlowPane contrastViewer = new FlowPane();
        contrastViewer.getChildren().addAll(windowSlider, levelSlider);
        return contrastViewer;
    }

    public void handlecontrast(){
        contrast = new ImageContrast((int) getWindowSlider().getValue(),
                (int) getLevelSlider().getValue());
        System.out.println(getLevelSlider().getValue()+" "+getWindowSlider().getValue());
        ogPicture = contrast.processImage(ogPicture);
        wImage = MatrixImageConverter.intMatrixToImage(ogPicture);
        createImagePreview(wImage);
    }

    public Slider getWindowSlider() {
        return windowSlider;
    }

    public Slider getLevelSlider() {
        return levelSlider;
    }

    public int[][] getOgPicture() {
        return ogPicture;
    }
}
