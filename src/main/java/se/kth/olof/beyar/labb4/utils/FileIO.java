package se.kth.olof.beyar.labb4.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for file input and output operations related to images.
 */
public class FileIO
{

    // Code from LoadImageIntoImageViewWithFileChooser.java

    /**
     * Opens a file chooser dialog to allow the user to select an image file, and loads the selected image into an ImageView.
     *
     * @param stage The stage on which the file chooser dialog will be displayed.
     * @return An ImageView containing the loaded image, or an empty ImageView if no file was selected.
     */
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

    //code from save image example in canvas
    /**
     * Saves the given image to a file named "copy.png" in the current directory.
     *
     * @param image The image to be saved.
     */
    public static void saveFile(Image image)
    {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        try
        {
            ImageIO.write(bufferedImage, "png", new File("copy.png"));
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
