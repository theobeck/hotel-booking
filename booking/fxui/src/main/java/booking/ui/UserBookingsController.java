package booking.ui;

import java.io.IOException;
import java.util.List;

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
     * The list of all rooms.
     */
    private List<Room> rooms;

    /**
     * The list of available rooms.
     */
    private ObservableList<Room> yourRooms = FXCollections.observableArrayList();

    /**
     * List view of booked rooms.
     */
    @FXML
    private ListView<Room> bookingList;

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
        for (Room r : rooms) {
            if (r.isBookedBy(username)) {
                yourRooms.add(r);
            }
        }
        bookingList.setItems(yourRooms);
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