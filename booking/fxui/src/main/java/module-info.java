module booking.fxui {
    exports booking.fxui.internal;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive java.net.http;
    requires transitive booking.core;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    opens booking.fxui to javafx.graphics, javafx.fxml;
}
