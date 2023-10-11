package booking.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import booking.core.Room;
import booking.json.ReadWrite;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class BookingAppTest extends ApplicationTest  {

    @BeforeAll
    public static void setUpClass() {
        if (Boolean.getBoolean("headless")) {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testBookRoomAndShowBooking() {
        clickOn("#bookRoom");
        clickOn("#search");

        ListView<Room> roomList = lookup("#roomList").query();
        int current = roomList.getItems().size();

        roomList.getSelectionModel().select(0);
        clickOn("#book");
        clickOn("#back");
        clickOn("#bookRoom");
        clickOn("#search");

        roomList = lookup("#roomList").query();
        assertEquals(current-1, roomList.getItems().size());

        clickOn("#back");
        clickOn("#showBooking");

        ListView<Room> bookingList = lookup("#bookingList").query();
        assertEquals(1, bookingList.getItems().size());

        String fileName = "src/main/resources/booking/ui/bookings.json";
        ReadWrite rw = new ReadWrite();
        List<Room> rooms = rw.restoredListFromFile(fileName);
        for (Room room : rooms) {
            if (room.getIsBooked()) {
                room.setBooked(false);
            }
        }
        rw.writeToFile(rooms, fileName);

    }
}
