package se.kth.olof.beyar.labb4.view;

import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

public class StatusView
{
    public StatusView() {}

    public FlowPane createStatus(String msg)
    {
        // Ny text område längst ner
        FlowPane textPane = new FlowPane();
        textPane.setPadding(new Insets(15));
        textPane.getChildren().add(new Text(msg));
        return textPane;
    }
}
