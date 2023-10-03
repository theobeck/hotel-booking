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

public class ShowBookingController {

    ReadWrite filemanager = new ReadWrite();
    String filename = "rom.ser";

    List<Room> rooms = filemanager.restoredListFromFile(filename);

    private ObservableList<Room> yourRooms = FXCollections.observableArrayList();

    /*
    @FXML
    TextField username;
    */

    @FXML
    ListView<Room> bookingList;

    @FXML
    public void initialize(){
        show();
    }

    private void show(){
        for(Room r : rooms){
            System.out.println(r.getIsBooked());
            if(r.getIsBooked()){
                yourRooms.add(r);
            }
        }
        bookingList.setItems(yourRooms);
    }

    @FXML
    private void searchUser(ActionEvent event) throws IOException{
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
    private void goToBooking(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}