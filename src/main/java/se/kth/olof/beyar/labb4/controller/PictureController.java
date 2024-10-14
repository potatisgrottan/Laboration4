package se.kth.olof.beyar.labb4.controller;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import se.kth.olof.beyar.labb4.model.ImageHistogram;
import se.kth.olof.beyar.labb4.model.PictureModel;
import se.kth.olof.beyar.labb4.view.PictureView;

public class PictureController
{

    private PictureModel model;
    private PictureView view;

    public PictureController(PictureModel model, PictureView view) {
        this.model = model;
        this.view = view;
    }

    public ImageView displayImagePreview() {
        Image image = model.getImage();
        return view.createImagePreview(image);
    }

    public void setImage(Image newImage) {
        model.setImage(newImage);
    }

    public FlowPane displayHistogram() {
        Image image = model.getImage();
        int colorFilterSwitch = model.getColorFilterSwitch();
        ImageHistogram imageHistogram = new ImageHistogram(image);
        return view.createHistogram(imageHistogram, image, colorFilterSwitch);
    }

    public HBox displayControlAndImage() {
        FlowPane histogramViewer = displayHistogram();
        ImageView imageViewer = displayImagePreview();

        // Skapar horizontell split mellan två vyer och lägger till komponenter
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        HBox.setHgrow(imageViewer, Priority.ALWAYS);
        hbox.getChildren().add(histogramViewer);
        hbox.getChildren().add(imageViewer);

        return hbox;
    }
}
