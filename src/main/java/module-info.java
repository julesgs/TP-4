module tps.tp4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens tps.tp4 to javafx.fxml;
    exports tps.tp4;
}