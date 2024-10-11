package se.kth.olof.beyar.labb4;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.model.ImageHistogram;

import java.net.URL;

public class Main extends Application {
    private MenuBar menubar;

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
        createMenuBar();
        MenuBar menuBar = getMenubar();

        // Laddar exempelbilden från resursmappen
        URL resource = this.getClass().getResource("/images/skull_ct.png");
        assert resource != null;
        Image image = new Image(resource.toString());

        // Skapar en ImageView för att visa bilden
        ImageView firstView = new ImageView();
        firstView.setImage(image);

        // Histogram
        // Code from https://java-buddy.blogspot.com/2015/07/display-images-histogram-on-javafx.html
        Button updateButton = new Button("Update");
        ImageView imageView = new ImageView();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String, Number> chartHistogram = new LineChart<>(xAxis, yAxis);
        chartHistogram.setCreateSymbols(false);

        updateButton.setOnAction(_ -> {
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
        });

        // Kör update första gången den renderas så man ser histogrammet
        updateButton.fire();

        // Skapa en grön ram runt den histogrammet
        BorderStroke borderStroke = new BorderStroke(
                Color.GREEN,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(1)
        );

        Border border = new Border(borderStroke);
        chartHistogram.setBorder(border);

        FlowPane updateButtonBox = new FlowPane();
        // v: top, v1: right, v2: bottom, v3: left
        updateButtonBox.setPadding(new Insets(0, 0, 75, 10));
        updateButtonBox.getChildren().add(updateButton);

        // Skapar horizontell split mellan två vyer och lägger till komponenter
        HBox hbox = new HBox();
        hbox.getChildren().add(chartHistogram);
        hbox.getChildren().add(firstView);

        // Ny text område längst ner
        FlowPane textPane = new FlowPane();
        textPane.setPadding(new Insets(25));
        textPane.getChildren().add(new Text("Histogram generated."));

        // Skapar en vertikal split
        VBox vbox = new VBox();
        vbox.getChildren().add(menuBar);
        vbox.getChildren().add(hbox);
        vbox.getChildren().add(updateButtonBox);
        vbox.getChildren().add(textPane);

        // Skapar en stackpane som vi kan lagra komponenterna på
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


    private void createMenuBar()
    {
        Menu fileMenu = new Menu("File");
        MenuItem openFileOption = new MenuItem("Open");
        fileMenu.getItems().add(openFileOption);
        openFileOption.addEventHandler(ActionEvent.ACTION, _ -> System.out.println("Open file clicked!"));

        Menu generateMenu = new Menu("Generate");
        generateMenu.addEventHandler(ActionEvent.ACTION, _ -> System.out.println("Generate button clicked!"));

        menubar = new MenuBar();
        menubar.getMenus().addAll(fileMenu, generateMenu);
    }

    private MenuBar getMenubar()
    {
        return menubar;
    }
}