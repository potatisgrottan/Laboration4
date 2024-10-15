package se.kth.olof.beyar.labb4.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.utils.ImageContrast;
import se.kth.olof.beyar.labb4.utils.ImageHistogram;
import se.kth.olof.beyar.labb4.utils.MatrixImageConverter;

/**
 * A view class for displaying and manipulating images, including image preview, histogram, and contrast adjustment.
 */
public class PictureView
{
    private WritableImage wImage;
    private int[][] ogPicture;
    private ImageContrast contrast;
    private Runnable contrastChangeCallback;
    private ImageView imageView;

    /**
     * Constructs a PictureView object.
     */
    public PictureView() {}

    /**
     * Creates an ImageView for displaying the given image.
     *
     * @param image The image to be displayed.
     * @return The ImageView displaying the image.
     */
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
        imageView = firstView;

        return firstView;
    }

    /**
     * Creates a FlowPane containing a histogram of the given image.
     *
     * @param imageHistogram The histogram data for the image.
     * @param image The image for which the histogram is to be created.
     * @return The FlowPane containing the histogram and update button.
     */
    public FlowPane createHistogram(ImageHistogram imageHistogram, Image image)
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
                        // imageHistogram.getSeriesAlpha(),
                        imageHistogram.getSeriesRed(),
                        imageHistogram.getSeriesGreen(),
                        imageHistogram.getSeriesBlue()
                );
            }

            ogPicture = MatrixImageConverter.imageToIntMatrix(image);
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

    /**
     * Creates a FlowPane containing sliders for adjusting the contrast of the given image.
     *
     * @param image The image for which the contrast is to be adjusted.
     * @return The FlowPane containing the contrast adjustment sliders.
     */
    public FlowPane createContrast(Image image, Slider windowSlider, Slider levelSlider){
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
            if (contrastChangeCallback != null)
                contrastChangeCallback.run();
        });

        levelSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (contrastChangeCallback != null)
                contrastChangeCallback.run();
        });

        FlowPane contrastViewer = new FlowPane();
        contrastViewer.getChildren().addAll(windowSlider, levelSlider);
        return contrastViewer;
    }

    /**
     * Sets a callback to be run when the contrast changes.
     *
     * @param callback The callback to be run when the contrast changes.
     */
    public void setContrastChangeCallback(Runnable callback) {
        this.contrastChangeCallback = callback;
    }

    /**
     * Gets the original image matrix.
     *
     * @return The original image matrix.
     */
    public int[][] getOgPicture()
    {
        return ogPicture;
    }

    /**
     * Gets the ImageView displaying the image.
     *
     * @return The ImageView displaying the image.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Displays an image preview.
     *
     * @return An ImageView containing the image preview.
     */
    public ImageView displayImagePreview(Image mImage, Image pImage) {
        Image image;

        if (mImage == null) {
            image = pImage;
        } else {
            image = mImage;
        }


        return createImagePreview(image);
    }

    /**
     * Displays the appropriate control (histogram or contrast) along with the image.
     *
     * @return An HBox containing the controls and the image.
     */
    public HBox displayControlAndImage(Image mImage, Image pImage, FlowPane chosenDisplay) {
        ImageView imageViewer = displayImagePreview(mImage,pImage);


        // Skapar horizontell split mellan två vyer och lägger till komponenter
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        HBox.setHgrow(imageViewer, Priority.ALWAYS);

        hbox.getChildren().add(chosenDisplay);
        hbox.getChildren().add(imageViewer);

        return hbox;
    }

    /**
     * Displays the histogram view.
     *
     * @return A FlowPane containing the histogram.
     */
    public FlowPane displayHistogram(Image pImage) {
        ImageHistogram imageHistogram = new ImageHistogram(pImage);
        return createHistogram(imageHistogram, pImage);
    }

    /**
     * Displays the contrast adjustment view.
     *
     * @return A FlowPane containing the contrast sliders.
     */
    public FlowPane displayContrast(Image pImage){

        return createContrast(pImage);
    }

    /**
     * Re-renders the application UI.
     */
    public void reRenderApp(Image pImage, Image mImage, Stage stage, MenuBar menuBar,FlowPane status, FlowPane chosenDisplay){

        HBox manipulateImageField = displayControlAndImage(mImage,pImage,chosenDisplay);
        updateImageView(pImage);

        VBox vbox = new VBox();
        VBox.setVgrow(manipulateImageField, Priority.ALWAYS);
        vbox.getChildren().add(menuBar);
        vbox.getChildren().add(manipulateImageField);
        vbox.getChildren().add(status);

        StackPane root = new StackPane();
        root.getChildren().add(vbox);

        // Konfigurerar och visar fönstret
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        stage.setTitle("Image Processing");
        stage.show();
    }

    /**
     * Updates the image view with a new image.
     *
     * @param newImage The new image to be displayed.
     */
    public void updateImageView(Image newImage) {
        if (imageView != null) {
            imageView.setImage(newImage);
        }
    }
}
