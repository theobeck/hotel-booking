module booking_gr2313.fxui {
    requires com.fasterxml.jackson.databind;

    requires java.net.http;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.fontawesome;

    requires booking_gr2313.core;

    opens booking.ui to javafx.graphics, javafx.fxml;
}
