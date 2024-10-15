package se.kth.olof.beyar.labb4.view;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * A view class for displaying status messages in the application.
 */
public class StatusView
{
    /**
     * Constructs a StatusView object.
     */
    public StatusView() {}

    /**
     * Creates a FlowPane containing a status message.
     *
     * @param msg The status message to be displayed.
     * @return The FlowPane containing the status message.
     */
    public FlowPane createStatus(String msg)
    {
        // Ny text område längst ner
        FlowPane textPane = new FlowPane();
        textPane.setPadding(new Insets(15));
        textPane.getChildren().add(new Text(msg));
        return textPane;
    }
}
