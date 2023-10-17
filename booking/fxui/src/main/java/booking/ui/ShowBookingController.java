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

public final class ShowBookingController {

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
     *  Initialize method for controller.
     */
    @FXML
    public void initialize() {
        show();
    }

    private void show() {
        rooms = filemanager.restoredListFromFile(filename);
        for (Room r : rooms) {
            if (r.isBooked()) {
                yourRooms.add(r);
            }
        }
        bookingList.setItems(yourRooms);
    }

    @FXML
    private void searchUser(final ActionEvent event) throws IOException {
        System.out.println("hei");
    }

    /*
    @FXML
    private void searchUser(ActionEvent event) throws IOException{
        for(Room r : rooms){
            if(r.getBookedBy() == username.getText()){
                yourRooms.add(r);
            }
        }
        bookingList.setItems(yourRooms);
    }
    */

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
}