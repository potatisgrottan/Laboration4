package se.kth.olof.beyar.labb4.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import se.kth.olof.beyar.labb4.utils.MatrixImageConverter;

public class PictureView
{
    private WritableImage wImage;
    private int[][] ogPicture;

    public PictureView() {}

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

    public FlowPane createHistogram(Image image, int colorFilterSwitch) {
        HistogramView histogram = new HistogramView(image, colorFilterSwitch);
        return histogram.createHistogram();
    }
}
