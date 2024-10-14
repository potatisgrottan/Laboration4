package se.kth.olof.beyar.labb4.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileIO
{

    // Från LoadImageIntoImageViewWithFileChooser.java
    public static ImageView openFile(Stage stage)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "png files",
                "*.png"
        );
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("Open Image File");

        File file = fileChooser.showOpenDialog(stage);
        ImageView imageView = new ImageView();

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);

            // this will scale the image/image view to fit its container (pane)
            imageView.fitWidthProperty().bind(stage.widthProperty());
            imageView.setPreserveRatio(true);
            imageView.setSmooth(true);
        }

        return imageView;
    }
}
