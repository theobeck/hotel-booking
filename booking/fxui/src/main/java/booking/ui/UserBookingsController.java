package booking.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import booking.core.Booking;
import booking.core.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * A controller for the user bookings view.
 */
public final class UserBookingsController extends AbstractBookingController {

    /**
     * The file manager object.
     */
    private RestAccess restAccess;

    /**
     * The list of user's bookings.
     */
    private ObservableList<Booking> yourBookings;

    /**
     * List view of booked rooms.
     */
    @FXML
    private ListView<Booking> bookingList;

    /**
     * Default constructor for UserBookingsController.
     */
    public UserBookingsController() {
        restAccess = new RestAccess();
        rooms = restAccess.getAllRooms();
    }

    /**
     * Initialize method for controller.
     */
    @FXML
    public void initialize() {
        show();
    }

    private void show() {
        for (Booking booking : usersAccess.getBookingsByUsername(user)) {
            yourBookings.add(booking);
        }
        bookingList.setItems(yourBookings);
    }

    @FXML
    private void cancelBooking(final ActionEvent event) throws IOException {
        Room room = bookingList.getSelectionModel().getSelectedItem();
        if (room != null) {
            restAccess.cancelBooking(room.getRoomNumber(), username);
            yourRooms.remove(room);
        }
    }

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.setUser(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(mainMenuController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}