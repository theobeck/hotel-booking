module booking.ui {
    exports booking.ui.internal;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive java.net.http;
    requires transitive booking.core;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    opens booking.ui to javafx.graphics, javafx.fxml;
}
