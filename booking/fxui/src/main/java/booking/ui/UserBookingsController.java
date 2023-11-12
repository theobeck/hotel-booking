package booking.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import booking.core.Booking;
import booking.core.Room;
import booking.springboot.restserver.RoomAccess;
import booking.springboot.restserver.UsersAccess;
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

    // /**
    // * The file manager object.
    // */
    // private RoomAccess roomAccess;

    // /**
    // * The list of all rooms.
    // */
    // private List<Room> rooms;

    /**
     * The file manager object.
     */
    private UsersAccess usersAccess;

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
        // roomAccess = new RoomAccess();
        // rooms = roomAccess.getAllRooms();
        usersAccess = new UsersAccess();
        yourBookings = FXCollections.observableArrayList();
    }

    /**
     * Initialize method for controller.
     */
    @FXML
    public void initialize() {
        show();
    }

    private void show() {
        for (Booking booking : usersAccess.getBookingsByUsername(username)) {
            yourBookings.add(booking);
        }
        bookingList.setItems(yourBookings);
    }

    // private void show() {
    // for (Room r : rooms) {
    // if (r.isBookedBy(username)) {
    // for (Booking userBooking : r.getBookingsByUser(username)) {
    // yourBookings.add(userBooking);
    // }
    // }
    // }
    // bookingList.setItems(yourBookings);
    // }

    @FXML
    private void cancelBooking(final ActionEvent event) throws IOException {
        Booking booking = bookingList.getSelectionModel().getSelectedItem();
        if (booking != null) {
            usersAccess.cancelBooking(username, booking.getRoom().getRoomNumber(), booking.getFrom(),
                    booking.getTo());
            yourBookings.remove(booking);
        }
    }

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(mainMenuController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}