package se.kth.olof.beyar.labb4.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MenuModel {
	private String imagePath;
	private ImageView imageView;
	private FileChooser fileChooser;

	public MenuModel() {
		imageView = new ImageView();
		fileChooser = new FileChooser();
	}

	public void setImage(String image)
	{
		this.imagePath = image;
	}

	// Från LoadImageIntoImageViewWithFileChooser.java
	public void openAndReadFile(Stage stage) {
		imageView = new ImageView();
		fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter =
				new FileChooser.ExtensionFilter(
						"jpg files", "*.jpg");

		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setTitle("Open Image File");

		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
			Image image = new Image(file.toURI().toString());
			imageView.setImage(image);

			// this will scale the image/image view to fit its container (pane)
			imageView.fitWidthProperty().bind(stage.widthProperty());
			imageView.setPreserveRatio(true);

			imageView.setSmooth(true);
			// exception handling needed?
		} else {
			// show an Alert
			System.out.println("Something went wrong");
		}
	}

	public ImageView getImageView()
	{
		return imageView;
	}
}
