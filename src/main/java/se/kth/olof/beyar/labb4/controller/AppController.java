package se.kth.olof.beyar.labb4.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.model.MenuModel;
import se.kth.olof.beyar.labb4.model.PictureModel;
import se.kth.olof.beyar.labb4.model.StatusModel;
import se.kth.olof.beyar.labb4.utils.*;
import se.kth.olof.beyar.labb4.view.MenuView;
import se.kth.olof.beyar.labb4.view.PictureView;
import se.kth.olof.beyar.labb4.view.StatusView;

public class AppController {
    private Stage stage;

    private MenuModel mModel;
    private MenuView mView;

    private PictureModel pModel;
    private PictureView pView;

    private StatusModel sModel;
    private StatusView sView;

    private int histogramContrastSwitch;

    public AppController(MenuModel mModel,MenuView mView,Stage stage, PictureModel pModel,
                         PictureView pView, StatusModel sModel,StatusView sView)
    {
        this.mModel=mModel;
        this.mView=mView;
        this.stage=stage;
        this.pModel=pModel;
        this.pView=pView;
        this.sModel=sModel;
        this.sView=sView;
        initializeListeners();
    }

    private void initializeListeners() {
        mView.getOpenFileOptionButton().setOnAction(_ -> {
            handleOpenFile();
            reRenderApp();
        });


        mView.getHistogramViewButton().setOnAction(_ ->
                handleHistogramMenuButton()
        );

        mView.getContrastSliderButton().setOnAction(_ ->
                handleConstrastSliderMenuButton()
        );

        mView.getGrayScaleButton().setOnAction(_-> handleGrayScale());

        mView.getInvertedColorButton().setOnAction(_-> handleInvertedColor());

        pView.setContrastChangeCallback(() -> {
            handleContrast();
        });
    }

    public MenuBar getMenuBar() {
        return mView.getMenuBar();
    }

    public FlowPane buildStatusView()
    {
        return sView.createStatus(sModel.getStatusMsg());
    }

    private void handleOpenFile(){
        mModel.openAndReadFile(stage);
        pModel.setImage(mModel.getChosenImageViewFromMenu().getImage());
    }

    private void handleHistogramMenuButton() {
        histogramContrastSwitch = 1;
        displayControlAndImage();
        reRenderApp();
        System.out.println("Histogram button pressed!");
    }

    private void handleConstrastSliderMenuButton() {
        histogramContrastSwitch = 0;
        displayControlAndImage();
        reRenderApp();
        System.out.println("Contrast slider option pressed!");
    }

    private void handleContrast() {
        int windowValue = pView.getWindowValue();
        int levelValue = pView.getLevelValue();

        // System.out.println("Values { Window: " + windowValue + ", Level: " + levelValue + "}");
        ImageContrast contrast = new ImageContrast(windowValue, levelValue);

        int[][] processedPicture = contrast.processImage(pView.getOgPicture());
        WritableImage wImage = MatrixImageConverter.intMatrixToImage(processedPicture);
        pModel.setImage(wImage);
        updateImageView(wImage);
    }

    private void handleGrayScale(){
        ImageGrayScale gray = new ImageGrayScale();

        int[][] processedPicture = gray.processImage(pView.getOgPicture());
        WritableImage wImage = MatrixImageConverter.intMatrixToImage(processedPicture);
        pModel.setImage(wImage);
        mModel.getChosenImageViewFromMenu().setImage(wImage);
        reRenderApp();
    }

    private void handleInvertedColor(){
        ImageInvertColor invert = new ImageInvertColor();

        int[][] processedPicture = invert.processImage(pView.getOgPicture());
        WritableImage wImage = MatrixImageConverter.intMatrixToImage(processedPicture);
        pModel.setImage(wImage);
        mModel.getChosenImageViewFromMenu().setImage(wImage);
        reRenderApp();
    }

    public ImageView displayImagePreview() {
        Image image;

        if (mModel.getChosenImageViewFromMenu().getImage() == null) {
            image = pModel.getImage();
        } else {
            image = mModel.getChosenImageViewFromMenu().getImage();
        }

        if (histogramContrastSwitch==1) {
            sModel.setStatusMsg("Histogram generated");
        } else {
            sModel.setStatusMsg("Sliders generated");
        }

        return pView.createImagePreview(image);
    }

    public FlowPane displayHistogram() {
        Image image = pModel.getImage();
        ImageHistogram imageHistogram = new ImageHistogram(image);
        return pView.createHistogram(imageHistogram, image);
    }

    public FlowPane displayContrast(){
        Image image = pModel.getImage();
        return pView.createContrast(image);
    }

    public HBox displayControlAndImage() {
        FlowPane histogramView = displayHistogram();
        FlowPane contrastVeiw = displayContrast();
        ImageView imageViewer = displayImagePreview();

        // Skapar horizontell split mellan två vyer och lägger till komponenter
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        HBox.setHgrow(imageViewer, Priority.ALWAYS);

        if (histogramContrastSwitch == 1) {
            hbox.getChildren().add(histogramView);
        } else {
            hbox.getChildren().add(contrastVeiw);
        }

        hbox.getChildren().add(imageViewer);

        return hbox;
    }

    private void updateImageView(Image newImage) {
        ImageView imageView = pView.getImageView();
        if (imageView != null) {
            imageView.setImage(newImage);
        }
    }

    public void reRenderApp(){
        HBox manipulateImageField = displayControlAndImage();
        updateImageView(pModel.getImage());

        VBox vbox = new VBox();
        VBox.setVgrow(manipulateImageField, Priority.ALWAYS);
        vbox.getChildren().add(getMenuBar());
        vbox.getChildren().add(manipulateImageField);
        vbox.getChildren().add(buildStatusView());

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
}
