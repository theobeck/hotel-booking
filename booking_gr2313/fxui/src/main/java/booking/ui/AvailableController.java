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

    ReadWrite filemanager = new ReadWrite();
    String filename = "rom.ser";

    List<Room> rooms = filemanager.restoredListFromFile(filename);

    @FXML
    ListView<Room> roomList;

    private ObservableList<Room> availableRooms = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        addObjects();
        roomList.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.SINGLE);
    }

    
    private void addObjects(){
        for(Room r : rooms){
            if(!r.isBooked()){
                availableRooms.add(r);
            }
        }
        roomList.setItems(availableRooms);
    }

    @FXML
    private void book(ActionEvent event) throws IOException{
        Room thisRoom = roomList.getSelectionModel().getSelectedItem();
        System.out.println(thisRoom);
        System.out.println(thisRoom.getIsBooked());
        thisRoom.setBooked(true);
        filemanager.writeToFile(rooms, filename);
    }
    
    @FXML
    private void goToBooking(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
