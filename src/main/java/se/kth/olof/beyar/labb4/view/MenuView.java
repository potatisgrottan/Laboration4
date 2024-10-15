package se.kth.olof.beyar.labb4.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * A class representing the view for the menu bar in the application.
 */
public class MenuView {
    private MenuBar menuBar;

    /**
     * Constructs a MenuView object and creates the menu bar.
     */
    public MenuView() {
        createMenuBar();
    }

    /**
     * Creates the menu bar with 'File' and 'Generate' menus, including their respective menu items.
     */
    private void createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem openFileOption = new MenuItem("Load image");
        MenuItem saveImageOption = new MenuItem("Save image");
        MenuItem exitOption = new MenuItem("Exit");
        fileMenu.getItems().addAll(openFileOption, saveImageOption, exitOption);

        Menu generateMenu = new Menu("Generate");
        MenuItem histogramView = new MenuItem("Histogram");
        MenuItem contrastSliderView = new MenuItem("Contrast slider");
        MenuItem grayScaleOption = new MenuItem("Gray Scale");
        MenuItem invertColorOption = new MenuItem("Invert Color");
        generateMenu.getItems().addAll(histogramView,contrastSliderView,grayScaleOption,invertColorOption);

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, generateMenu);
    }


    /**
     * Gets the MenuBar object.
     *
     * @return The MenuBar object.
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Gets the 'Load image' menu item.
     *
     * @return The MenuItem for loading an image.
     */
    public MenuItem getOpenFileOptionButton() {
        return menuBar.getMenus().getFirst().getItems().getFirst();
    }

    /**
     * Gets the 'Save image' menu item.
     *
     * @return The MenuItem for saving an image.
     */
    public MenuItem getSaveOptionButton() {
        return menuBar.getMenus().getFirst().getItems().get(1);
    }

    /**
     * Exits the window
     *
     * @return The MenuItem exiting the app.
     */
    public MenuItem getExitOptionButton() {
        return menuBar.getMenus().getFirst().getItems().get(2);
    }

    /**
     * Gets the 'Histogram' menu item.
     *
     * @return The MenuItem for viewing the histogram.
     */
    public MenuItem getHistogramViewButton() {
        return menuBar.getMenus().get(1).getItems().getFirst();
    }

    /**
     * Gets the 'Contrast slider' menu item.
     *
     * @return The MenuItem for adjusting contrast with sliders.
     */
    public MenuItem getContrastSliderButton() {return menuBar.getMenus().get(1).getItems().get(1);}

    /**
     * Gets the 'Gray Scale' menu item.
     *
     * @return The MenuItem for converting the image to gray scale.
     */
    public MenuItem getGrayScaleButton(){return menuBar.getMenus().get(1).getItems().get(2);}

    /**
     * Gets the 'Invert Color' menu item.
     *
     * @return The MenuItem for inverting the colors of the image.
     */
    public MenuItem getInvertedColorButton(){return menuBar.getMenus().get(1).getItems().get(3);}
}
