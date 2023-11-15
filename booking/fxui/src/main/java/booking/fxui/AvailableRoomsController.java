package booking.fxui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import booking.core.Room;
import booking.fxui.internal.RestAccess;
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
 * A controller for the available rooms view.
 */
public final class AvailableRoomsController extends AbstractBookingController {

    /**
     * The current restAccess object.
     */
    private RestAccess restAccess;

    /**
     * The list of all rooms.
     */
    private List<Room> rooms;

    /**
     * List view for available rooms.
     */
    @FXML
    private ListView<Room> roomList;

    /**
     * The list of available rooms.
     */
    private ObservableList<Room> availableRooms;

    /**
     * The date the booking starts.
     */
    private LocalDate from;

    /**
     * The date the booking ends.
     */
    private LocalDate to;

    /**
     * The upper bound for capacity of a newly generated room.
     */
    private static final int UPPER_BOUND_CAPACITY = 8;

    /**
     * The upper bound for amount of rooms that can exist at one time.
     */
    private static final int AMT_OF_ROOMS = 10;

    /**
     * How much the room's cost increases with the capacity.
     */
    private static final int COST_PER_PERSON = 75;

    /**
     * Random object used to generate random numbers.
     */
    private final Random random;

    /**
     * Default constructor for AvailableRoomsController.
     *
     * @throws Exception If there is an error.
     */
    public AvailableRoomsController() throws Exception {
        restAccess = new RestAccess();
        rooms = restAccess.getAllRooms();
        availableRooms = FXCollections.observableArrayList();
        random = new Random();
    }

    public void setFrom(final LocalDate from) {
        this.from = from;
    }

    public void setTo(final LocalDate to) {
        this.to = to;
    }

    @FXML
    private void initialize() throws Exception {
        addObjects();
        roomList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
    }

    private void addObjects() throws Exception {
        if (rooms.size() < AMT_OF_ROOMS) {
            List<Integer> occupiedRoomNumbers = new ArrayList<>();
            for (int i = 0; i < rooms.size(); i++) {
                occupiedRoomNumbers.add(rooms.get(i).getRoomNumber());
            }
            for (int i = 1; i <= AMT_OF_ROOMS; i++) {
                if (occupiedRoomNumbers.contains(i)) {
                    continue;
                }
                int cap = random.nextInt(UPPER_BOUND_CAPACITY) + 1;
                restAccess.createRoom(i, cap, cap * COST_PER_PERSON);
            }
        }
        rooms = restAccess.getAllRooms();
        for (Room r : rooms) {
            if (r.isAvailableBetween(from, to)) {
                availableRooms.add(r);
            }
        }
        roomList.setItems(availableRooms);
    }

    @FXML
    private void book(final ActionEvent event) throws Exception {
        Room thisRoom = roomList.getSelectionModel().getSelectedItem();
        restAccess.bookRoomByNumber(thisRoom.getRoomNumber(), from, to, user.getUsername(),
                thisRoom.totalCostOfBooking(from, to), "both");
        availableRooms.remove(thisRoom);
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
