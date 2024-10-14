package se.kth.olof.beyar.labb4.controller;

import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.model.MenuModel;
import se.kth.olof.beyar.labb4.model.PictureModel;
import se.kth.olof.beyar.labb4.model.StatusModel;
import se.kth.olof.beyar.labb4.view.MenuView;
import se.kth.olof.beyar.labb4.view.PictureView;
import se.kth.olof.beyar.labb4.view.StatusView;

public class AppController {

    private MenuModel mModel;
    private MenuView mView;
    private Stage stage;

    private PictureModel pModel;
    private PictureView pView;

    private StatusModel sModel;
    private StatusView sView;

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
        mView.getOpenFileOptionButton().setOnAction(_ -> handleOpenFile());

        /*
        mView
                .getHistogramViewButton()
                .setOnAction(_ -> handleHistogramMenuButton());

        mView
                .getContrastSliderButton()
                .setOnAction(_ -> handleConstrastSliderMenuButton());

         */
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

    public ImageView displayImagePreview() {
        Image image = pModel.getImage();
        return pView.createImagePreview(image);
    }

    public void setImage(Image newImage) {
        pModel.setImage(newImage);
    }

    public FlowPane displayHistogram() {
        Image image = pModel.getImage();
        int colorFilterSwitch = pModel.getColorFilterSwitch();
        return pView.createHistogram(image, colorFilterSwitch);
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
