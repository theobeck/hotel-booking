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

public class AvailableController {

    /**
     * The file manager object.
     */
    private ReadWrite filemanager = new ReadWrite();

    /**
     * The filepath bookings are saved to.
     */
    private String filename = "src/main/resources/booking/ui/bookings.json";


    /**
     * The list of all rooms.
     */
    private List<Room> rooms = filemanager.restoredListFromFile(filename);

    /**
     * List view for available rooms.
     */
    @FXML
    private ListView<Room> roomList;

    /**
     * The list of available rooms.
     */
    private ObservableList<Room> availableRooms = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        addObjects();
        roomList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
    }


    private void addObjects() {
        for (Room r : rooms) {
            if (!r.getIsBooked()) {
                availableRooms.add(r);
            }
        }
        roomList.setItems(availableRooms);
    }

    @FXML
    private void book(final ActionEvent event) throws IOException {
        Room thisRoom = roomList.getSelectionModel().getSelectedItem();
        thisRoom.setBooked(true);
        filemanager.writeToFile(rooms, filename);
    }

    @FXML
    private void goToBooking(final ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return The filepath bookings are saved to.
     */
    public String getFilename() {
        return filename;
    }


    /**
     * @param newFilename The filepath bookings will be saved to.
     */
    public void setFilename(final String newFilename) {
        filename = newFilename;
    }

    /**
     * @return The list of available rooms.
     */
    public List<Room> getRooms() {
        return rooms;
    }


    /**
     * @param newRooms Change the list of available rooms.
     */
    public void setRooms(final List<Room> newRooms) {
        rooms = newRooms;
    }
}
