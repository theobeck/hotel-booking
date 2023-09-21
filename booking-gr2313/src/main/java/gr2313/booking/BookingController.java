package gr2313.booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookingController {


    @FXML
    private void goToShowBooking(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showBooking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void goToBookRoom(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("bookRoom.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }




    ArrayList<Room> rooms = new ArrayList<>();

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void generateRooms(){
        Random random = new Random();
        for(int i = 0; i<10; i++){
            int cap = random.nextInt(8)+1;
            Room room = new Room(i, cap, cap*750);
        }
    }

}
