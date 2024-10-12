module se.kth.olof.beyar.labb4 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    exports se.kth.olof.beyar.labb4;
    opens se.kth.olof.beyar.labb4 to javafx.fxml;

    exports se.kth.olof.beyar.labb4.examples;
    opens se.kth.olof.beyar.labb4.examples to javafx.fxml;

    exports se.kth.olof.beyar.labb4.model;
    opens se.kth.olof.beyar.labb4.model to javafx.fxml;

    exports se.kth.olof.beyar.labb4.view;
    opens se.kth.olof.beyar.labb4.view to javafx.fxml;

    exports se.kth.olof.beyar.labb4.controller;
    opens se.kth.olof.beyar.labb4.controller to javafx.fxml;
}
