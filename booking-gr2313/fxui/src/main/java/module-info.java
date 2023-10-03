module gr2313.booking.ui {
    requires com.fasterxml.jackson.databind;

    requires java.net.http;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.fontawesome;

    requires gr2313.booking;

    opens gr2313.booking.ui to javafx.graphics, javafx.fxml;
}
