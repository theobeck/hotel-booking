module booking.ui {
    requires com.fasterxml.jackson.databind;

    requires java.net.http;

    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    requires booking.core;
    requires booking.springboot.restserver;

    opens booking.ui to javafx.graphics, javafx.fxml;
}
