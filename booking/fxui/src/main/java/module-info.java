module booking.ui {
    exports booking.ui.internal;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive booking.core;

    requires java.net.http;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    opens booking.ui to javafx.graphics, javafx.fxml;
}
