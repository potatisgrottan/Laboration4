package se.kth.olof.beyar.labb4.controller;

import javafx.scene.control.MenuBar;
import se.kth.olof.beyar.labb4.model.MenuModel;
import se.kth.olof.beyar.labb4.view.MenuView;

public class MenuController {
	private MenuModel model;
	private MenuView view;

	public MenuController(MenuModel model, MenuView view) {
			this.model = model;
			this.view = view;
			initializeListeners();
	}

	private void initializeListeners() {
			view.getOpenFileOption().setOnAction(_ -> handleOpenFile());
			view.getGenerateMenu().setOnAction(_ -> handleGenerate());
	}

	// Implementera logik för att öppna fil här
	private void handleOpenFile() {
			System.out.println("Open file clicked!");
			model.setFileOpen(true);
	}

	// Implementera logik för generering här
	private void handleGenerate() {
			System.out.println("Generate button clicked!");
	}

	public MenuBar getMenuBar() {
			return view.getMenuBar();
	}
}
