package se.kth.olof.beyar.labb4.controller;

import javafx.scene.control.Slider;
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

/**
 * The controller class for managing the application's overall behavior.
 */
public class AppController
{
    private final Stage stage;

    private final MenuModel mModel;
    private final MenuView mView;

    private final PictureModel pModel;
    private final PictureView pView;

    private final StatusModel sModel;
    private final StatusView sView;

    private int histogramContrastSwitch;

    /**
     * Constructs an AppController object with the specified models, views, and stage.
     *
     * @param mModel The menu model.
     * @param mView  The menu view.
     * @param stage  The main application stage.
     * @param pModel The picture model.
     * @param pView  The picture view.
     * @param sModel The status model.
     * @param sView  The status view.
     */
    public AppController(MenuModel mModel, MenuView mView, Stage stage, PictureModel pModel,
                         PictureView pView, StatusModel sModel, StatusView sView) {
        this.mModel = mModel;
        this.mView = mView;
        this.stage = stage;
        this.pModel = pModel;
        this.pView = pView;
        this.sModel = sModel;
        this.sView = sView;

        initializeListeners();
    }

    /**
     * Initializes event listeners for the menu options and picture view.
     */
    private void initializeListeners() {
        mView.getOpenFileOptionButton().setOnAction(_ -> {
            handleOpenFile();
            reRenderApp();
        });
        mView.getSaveOptionButton().setOnAction(_ -> handleSaveFile());
        mView.getExitOptionButton().setOnAction(_ -> handleExit());
        mView.getHistogramViewButton().setOnAction(_ -> handleHistogramMenuButton());
        mView.getContrastSliderButton().setOnAction(_ -> handleConstrastSliderMenuButton());
        mView.getGrayScaleButton().setOnAction(_ -> handleGrayScale());
        mView.getInvertedColorButton().setOnAction(_ -> handleInvertedColor());
        pView.setContrastChangeCallback(() -> handleContrast());
    }

    /**
     * Handles the action of opening and reading a file.
     */
    private void handleOpenFile() {
        mModel.openAndReadFile(stage);

        if (mModel.isImageUploaded())
        {
            pModel.setImage(mModel.getChosenImageViewFromMenu().getImage());
            pModel.resetSlidersState();
        }
    }

    /**
     * Handles the action of saving a file.
     */
    private void handleSaveFile() {
        mModel.saveFile(pModel.getImage());
    }

    /**
     * Handles the action of exiting the app
     */
    private void handleExit() {
        final int success = 0;
        System.exit(success);
    }

    /**
     * Handles the action of displaying the histogram view.
     */
    private void handleHistogramMenuButton() {
        histogramContrastSwitch = 0;
        FlowPane displayHistogram = pView.displayHistogram(pModel.getImage());
        sModel.setStatusMsg("Histogram generated");
        pView.displayControlAndImage(mModel.getChosenImageViewFromMenu().getImage(), pModel.getImage(), displayHistogram);
        reRenderApp();
    }

    /**
     * Handles the action of displaying the contrast slider view.
     */
    private void handleConstrastSliderMenuButton() {
        histogramContrastSwitch = 1;
        Slider windowValue = pModel.getWindowSlider();
        Slider levelValue = pModel.getLevelSlider();
        FlowPane displayContrast = pView.createContrast(pModel.getImage(), windowValue, levelValue);
        sModel.setStatusMsg("Contrast generated");
        pView.displayControlAndImage(mModel.getChosenImageViewFromMenu().getImage(), pModel.getImage(), displayContrast);
        reRenderApp();
    }

    /**
     * Handles the action of updating the contrast of the image.
     */
    private void handleContrast() {
        int windowValue = (int) pModel.getWindowSlider().getValue();
        int levelValue = (int) pModel.getLevelSlider().getValue();
        ImageContrast contrast = new ImageContrast(windowValue, levelValue);

        int[][] processedPicture = contrast.processImage(pView.getOgPicture());
        WritableImage wImage = MatrixImageConverter.intMatrixToImage(processedPicture);
        pModel.setImage(wImage);

        pView.updateImageView(wImage);
    }

    /**
     * Handles the action of converting the image to grayscale.
     */
    private void handleGrayScale() {
        ImageGrayScale gray = new ImageGrayScale();

        int[][] processedPicture = gray.processImage(pView.getOgPicture());
        WritableImage wImage = MatrixImageConverter.intMatrixToImage(processedPicture);
        pModel.setImage(wImage);
        mModel.getChosenImageViewFromMenu().setImage(wImage);
        reRenderApp();
    }

    /**
     * Handles the action of inverting the colors of the image.
     */
    private void handleInvertedColor() {
        ImageInvertColor invert = new ImageInvertColor();

        int[][] processedPicture = invert.processImage(pView.getOgPicture());
        WritableImage wImage = MatrixImageConverter.intMatrixToImage(processedPicture);
        pModel.setImage(wImage);
        mModel.getChosenImageViewFromMenu().setImage(wImage);
        reRenderApp();
    }

    /**
     * Re-renders the application UI by calling reRenderAppFunction in PictureView.
     */
    public void reRenderApp()
    {
        FlowPane chosenDisplay;
        Slider windowValue = pModel.getWindowSlider();
        Slider levelValue = pModel.getLevelSlider();

        if (histogramContrastSwitch == 0) {
            sModel.setStatusMsg("Histogram generated");
            chosenDisplay = pView.displayHistogram(pModel.getImage());
        } else {
            sModel.setStatusMsg("Sliders generated");
            chosenDisplay = pView.createContrast(pModel.getImage(), windowValue, levelValue);
        }

        pView.reRenderApp(
                pModel.getImage(),
                mModel.getChosenImageViewFromMenu().getImage(),
                stage,
                mView.getMenuBar(),
                sView.createStatus(sModel.getStatusMsg()),
                chosenDisplay
        );
    }
}
