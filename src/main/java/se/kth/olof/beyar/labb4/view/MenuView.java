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
        MenuItem openFileOption = new MenuItem("Open");
        fileMenu.getItems().add(openFileOption);

        Menu generateMenu = new Menu("Generate");

        menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, generateMenu);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public MenuItem getOpenFileOption() {
        return ((Menu) menuBar.getMenus().get(0)).getItems().get(0);
    }

    public Menu getGenerateMenu() {
        return menuBar.getMenus().get(1);
    }
}
