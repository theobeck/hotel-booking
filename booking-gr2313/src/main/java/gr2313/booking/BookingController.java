package gr2313.booking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class BookingController {

    @FXML
    private void switchToSecondary() throws IOException {
        BookingApp.setRoot("secondary");
    }

    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;

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
