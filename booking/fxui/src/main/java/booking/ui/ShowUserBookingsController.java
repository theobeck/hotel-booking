package booking.ui;

import java.io.IOException;
import java.util.List;

import booking.core.Room;
import booking.json.ReadWrite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public final class ShowUserBookingsController {

    /**
     * The file manager object.
     */
    private ReadWrite filemanager = new ReadWrite();

    /**
     * The filepath bookings are saved to.
     */
    private String filePath = "src/main/resources/booking/ui/bookings.json";

    /**
     * The list of all rooms.
     */
    private List<Room> rooms = filemanager.restoredListFromFile(filePath);

    /**
     * The list of available rooms.
     */
    private ObservableList<Room> yourRooms = FXCollections.observableArrayList();

    // @FXML
    // TextField username;

    /**
     * List view of booked rooms.
     */
    @FXML
    private ListView<Room> bookingList;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * Initialize method for controller.
     */
    @FXML
    public void initialize() {
        show();
    }

    private void show() {
        rooms = filemanager.restoredListFromFile(filePath);
        for (Room r : rooms) {
            if (r.isBooked() && r.getBookedBy().equals(username)) {
                yourRooms.add(r);
            }
        }
        bookingList.setItems(yourRooms);
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

    /**
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username Change the username of the user.
     */
    public void setUsername(final String username) {
        this.username = username;
    }
}