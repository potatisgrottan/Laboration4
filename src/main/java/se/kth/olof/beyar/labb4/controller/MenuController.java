package se.kth.olof.beyar.labb4.controller;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import se.kth.olof.beyar.labb4.model.MenuModel;
import se.kth.olof.beyar.labb4.view.MenuView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
			view.getOpenFileOption().setOnAction(_ -> handleOpenFile());
			view.getGenerateMenu().setOnAction(_ -> handleGenerate());
	}

	// Implementera logik för att öppna fil här
	private void handleOpenFile() {
			openAndReadFile();
	}

	// Implementera logik för generering här
	private void handleGenerate() {
			System.out.println("Generate button clicked!");
	}

	// Från FileChooserExample.java
	private void openAndReadFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(stage);

		if (file != null) {
			String path = file.getPath();
			Label fileInfoLabel = new Label();
			fileInfoLabel.setText(path);

			TextArea textArea = new TextArea();

            try (BufferedReader in = new BufferedReader(new FileReader(path)))
            {
                String line = in.readLine();
                while (line != null)
                {
                    textArea.appendText(line + "\n");
                    line = in.readLine();
                }

				model.setFileOpen(true);
				model.setImage(path);
				System.out.println("File at " + path + " read!");
            } catch (IOException ie)
            {
                textArea.appendText("Unable to read file.");
            }
		}
	}

	public MenuBar getMenuBar() {
			return view.getMenuBar();
	}
}
