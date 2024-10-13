package se.kth.olof.beyar.labb4.controller;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import se.kth.olof.beyar.labb4.model.ImageModel;
import se.kth.olof.beyar.labb4.view.ImagePreview;

public class ImageController
{
    private ImageModel model;
    private ImagePreview view;
    private ImageView imageView;

    public ImageController(ImageModel model, ImagePreview view) {
        this.model = model;
        this.view = view;
        this.imageView = new ImageView();
    }

    public ImageView displayImagePreview() {
        Image image = model.getImage();
        imageView = view.createImagePreview(image);
        return imageView;
    }

    public void updateImage(Image newImage) {
        model.setImage(newImage);
        imageView.setImage(newImage);
    }

    public FlowPane displayHistogram() {
        Image image = model.getImage();
        int colorFilterSwitch = model.getColorFilterSwitch();
        return view.createHistogram(image, colorFilterSwitch);
    }

    public HBox displayControlAndImage()
    {
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
