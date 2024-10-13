package se.kth.olof.beyar.labb4.controller;

import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.model.MenuModel;
import se.kth.olof.beyar.labb4.view.MenuView;

public class MenuController {

    private MenuModel model;
    private MenuView view;
    private Stage stage;

    public MenuController(MenuModel model, MenuView view, Stage stage) {
        this.model = model;
        this.view = view;
        this.stage = stage;
        initializeListeners();
    }

    private void initializeListeners() {
        view.getOpenFileOptionButton().setOnAction(_ -> handleOpenFile());

        view
            .getHistogramViewButton()
            .setOnAction(_ -> handleHistogramMenuButton());

        view
            .getContrastSliderButton()
            .setOnAction(_ -> handleConstrastSliderMenuButton());
    }

    // Implementera logik för att öppna fil här
    private void handleOpenFile() {
        model.openAndReadFile(stage);
        System.out.println("New image loaded!");
    }

    public ImageView getChosenImageViewFromMenu() {
        return model.getChosenImageViewFromMenu();
    }

    private void handleHistogramMenuButton() {
        System.out.println("Histogram button pressed!");
    }

    private void handleConstrastSliderMenuButton() {
        System.out.println("Contrast slider option pressed!");
    }

    public MenuBar getMenuBar() {
        return view.getMenuBar();
    }
}
