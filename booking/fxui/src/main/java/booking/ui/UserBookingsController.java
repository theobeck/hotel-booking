package booking.ui;

import java.io.IOException;
import booking.core.Booking;
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
        // for (Booking booking : restAccess.getBookingsByUser(username)) {
        // yourBookings.add(booking);
        // }
        // bookingList.setItems(yourBookings);
    }

    @FXML
    private void cancelBooking(final ActionEvent event) throws IOException {
        // Room room = bookingList.getSelectionModel().getSelectedItem();
        // if (room != null) {
        // roomAccess.cancelBooking(room.getRoomNumber(), username);
        // yourRooms.remove(room);
    }

    /**
     * Go to the main menu.
     *
     * @param event the event
     * @throws IOException if an error occurs during loading
     */
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