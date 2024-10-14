package se.kth.olof.beyar.labb4.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuView {
    private MenuBar menuBar;

    public MenuView() {
        createMenuBar();
    }

    private void createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem openFileOption = new MenuItem("Load image");
        MenuItem saveImageOption = new MenuItem("Save image");
        fileMenu.getItems().addAll(openFileOption, saveImageOption);

        Menu generateMenu = new Menu("Generate");
        MenuItem histogramView = new MenuItem("Histogram");
        MenuItem contrastSliderView = new MenuItem("Contrast slider");
        MenuItem grayScaleOption = new MenuItem("Gray Scale");
        MenuItem invertColorOption = new MenuItem("Invert Color");

        generateMenu.getItems().addAll(histogramView,contrastSliderView,grayScaleOption,invertColorOption);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, generateMenu);
    }


    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getOpenFileOptionButton() {
        return menuBar.getMenus().getFirst().getItems().getFirst();
    }

    public MenuItem getSaveOptionButton() {
        return menuBar.getMenus().getFirst().getItems().get(1);
    }

    public MenuItem getHistogramViewButton() {
        return menuBar.getMenus().get(1).getItems().getFirst();
    }

    public MenuItem getContrastSliderButton() {return menuBar.getMenus().get(1).getItems().get(1);}

    public MenuItem getGrayScaleButton(){return menuBar.getMenus().get(1).getItems().get(2);}

    public MenuItem getInvertedColorButton(){return menuBar.getMenus().get(1).getItems().get(3);}
}
