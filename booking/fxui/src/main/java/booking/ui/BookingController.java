package booking.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookingController {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The showBookingController.
     */
    private ShowBookingController showBookingController = new ShowBookingController();

    /**
     * The bookRoomController.
     */
    private BookRoomController bookRoomController = new BookRoomController();

    @FXML
    private void goToShowBooking(final ActionEvent event) throws IOException {
        showBookingController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showBooking.fxml"));
        loader.setController(showBookingController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToBookRoom(final ActionEvent event) throws IOException {
        bookRoomController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookRoom.fxml"));
        loader.setController(bookRoomController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username Change the username of the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
