package booking.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX Booking App.
 */
public final class BookingApp extends Application {

    /**
     * Scene element for app.
     */
    private static Scene scene;

    @Override
    public void start(final Stage stage) throws IOException {
        Parent parent = loadFXML("login");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    static void setRoot(final String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(final String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * Main method for launching app.
     *
     * @param args
     */
    public static void main(final String[] args) {
        launch();
    }
}