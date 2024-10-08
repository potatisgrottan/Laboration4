module se.kth.olof.beyar.labb4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens se.kth.olof.beyar.labb4 to javafx.fxml;
    exports se.kth.olof.beyar.labb4;
    exports se.kth.olof.beyar.labb4.examples;
    opens se.kth.olof.beyar.labb4.examples to javafx.fxml;
}