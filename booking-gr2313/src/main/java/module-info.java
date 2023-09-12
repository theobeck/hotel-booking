module gr2313.booking {
    requires javafx.controls;
    requires javafx.fxml;

    opens gr2313.booking to javafx.fxml;
    exports gr2313.booking;
}
