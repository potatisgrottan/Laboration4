package se.kth.olof.beyar.labb4;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.controller.MenuController;
import se.kth.olof.beyar.labb4.model.*;
import se.kth.olof.beyar.labb4.utils.MatrixImageConverter;
import se.kth.olof.beyar.labb4.view.MenuView;

import java.net.URL;

public class Main extends Application {

    private int colorFilterSwitch = 0;
    private int[][]ogPicture;
    private WritableImage wImage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initierar och visar huvudscenen för bildbehandlingsapplikationen.
     * Denna metod skapar användargränssnittet genom att:
     * 1. Ladda en exempelbild (skull_ct.png).
     * 2. Skapa en uppdateringsknapp.
     * 3. Placera bilden och knappen i en HBox-layout.
     * 4. Konfigurera och visa huvudfönstret.
     *
     * @param stage Huvudscenen för applikationen.
     */
    @Override
    public void start(Stage stage) {
        // Skapar menubar instans temporärt tills vi har egna modeller
        MenuModel menuModel = new MenuModel();
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(menuModel, menuView);

        // Laddar exempelbilden från resursmappen
        URL resource = this.getClass().getResource("/images/skull_ct.png");
        assert resource != null;
        Image image = new Image(resource.toString());

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

        // Histogram, code from https://java-buddy.blogspot.com/2015/07/display-images-histogram-on-javafx.html
        Button updateButton = new Button("Update");
        ImageView imageView = new ImageView();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> chartHistogram = new LineChart<>(xAxis, yAxis);
        chartHistogram.setCreateSymbols(false);
        updateButton.setOnAction(_ -> {
            firstView.setImage(wImage);
            imageView.setImage(image);
            chartHistogram.getData().clear();

            // Egentligen ska skötas via Controller, ImageHistogram ska vara privat model
            ImageHistogram imageHistogram = new ImageHistogram(image);

            if (imageHistogram.isSuccess()) {
                chartHistogram.getData().addAll(
                        //imageHistogram.getSeriesAlpha(),
                        imageHistogram.getSeriesRed(),
                        imageHistogram.getSeriesGreen(),
                        imageHistogram.getSeriesBlue()
                );
            }

            ogPicture = MatrixImageConverter.imageToIntMatrix(image);

            if (colorFilterSwitch == 0) {
                ogPicture = MatrixImageConverter.imageToIntMatrix(image);
            } else if (colorFilterSwitch == 1) {
                ImageGrayScale gray = new ImageGrayScale();
                ogPicture = gray.processImage(ogPicture);
            } else if (colorFilterSwitch == 2) {
                ImageInvertColor invert = new ImageInvertColor();
                ogPicture = invert.processImage(ogPicture);
            } else {
                throw new IllegalStateException("We should not be able to increment to this level: " + colorFilterSwitch);
            }

            assert (ogPicture != null);
            wImage = MatrixImageConverter.intMatrixToImage(ogPicture);

            // Update the image view
            firstView.setImage(wImage);

            if (colorFilterSwitch == 2)
                colorFilterSwitch = 0;
            else
                colorFilterSwitch++;
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

        // Skapar horizontell split mellan två vyer och lägger till komponenter
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        HBox.setHgrow(imageViewer, Priority.ALWAYS);
        hbox.getChildren().add(histogramViewer);
        hbox.getChildren().add(imageViewer);

        // Ny text område längst ner
        FlowPane textPane = new FlowPane();
        textPane.setPadding(new Insets(15));
        textPane.getChildren().add(new Text("Histogram generated."));

        // Skapar en vertikal split
        VBox vbox = new VBox();
        VBox.setVgrow(hbox, Priority.ALWAYS);
        vbox.getChildren().add(menuController.getMenuBar());
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(textPane);

        // Skapar en stackpane som vi kan lagra komponenterna ovanpå
        StackPane root = new StackPane();
        root.getChildren().add(vbox);

        // Konfigurerar och visar huvudscenen
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(true);
        stage.setTitle("Image Processing");
        stage.show();
    }
}
