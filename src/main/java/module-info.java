module se.kth.olof.beyar.labb4.labboration4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.olof.beyar.labb4.labboration4 to javafx.fxml;
    exports se.kth.olof.beyar.labb4.labboration4;
}