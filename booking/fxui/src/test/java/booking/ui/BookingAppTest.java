package booking.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import booking.core.Room;
import booking.json.ReadWrite;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

/**
 * A test class for the {@link BookingApp} class.
 */
public class BookingAppTest extends ApplicationTest {

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testBookRoomAndShowBooking() {

        // Create a TestFX robot
        FxRobot theRobot = new FxRobot();

        clickOn("#inputUsername");
        write("test");
        clickOn("#inputPassword");
        write("test");
        clickOn("#btnLogin");

        clickOn("#bookRoom");

        clickOn("#fromPicker");
        write("10/17/2023");
        theRobot.push(KeyCode.ENTER);
        clickOn("#toPicker");
        write("10/19/2023");
        theRobot.push(KeyCode.ENTER);
        clickOn("#search");

        ListView<Room> roomList = lookup("#roomList").query();
        int current = roomList.getItems().size();

        roomList.getSelectionModel().select(0);
        clickOn("#book");
        roomList.getSelectionModel().select(1);
        clickOn("#book");
        clickOn("#back");

        clickOn("#bookRoom");

        clickOn("#fromPicker");
        write("10/17/2023");
        theRobot.push(KeyCode.ENTER);
        clickOn("#toPicker");
        write("10/19/2023");
        theRobot.push(KeyCode.ENTER);
        clickOn("#search");

        roomList = lookup("#roomList").query();
        assertEquals(current - 2, roomList.getItems().size());

        clickOn("#back");

        clickOn("#showBooking");

        ListView<Room> bookingList = lookup("#bookingList").query();
        String filePath = "src/main/resources/booking/ui/bookings.json";
        ReadWrite rw = new ReadWrite();
        List<Room> rooms = rw.readRoomsFromFile(filePath);
        int bookedRooms = 0;

        for (Room room : rooms) {
            if (room.isBookedBy("test")) {
                bookedRooms++;
            }
        }
        assertEquals(bookedRooms, bookingList.getItems().size());

        bookingList.getSelectionModel().select(0);
        clickOn("#btnCancelBooking");

        rooms = rw.readRoomsFromFile(filePath);
        bookedRooms = 0;

        for (Room room : rooms) {
            if (room.isBookedBy("test")) {
                bookedRooms++;
            }
        }
        assertEquals(bookedRooms, bookingList.getItems().size());

        clickOn("#btnBack");

        clickOn("#btnSignOut");

        clickOn("#inputUsername");
        write("test2");
        clickOn("#inputPassword");
        write("test2");
        clickOn("#btnLogin");

        clickOn("#showBooking");

        bookingList = lookup("#bookingList").query();
        assertEquals(0, bookingList.getItems().size());

        rooms = rw.readRoomsFromFile(filePath);
        bookedRooms = 0;
        for (Room room : rooms) {
            if (room.isBookedBy("test")) {
                bookedRooms++;
                room.cancelBooking("test");
            }
        }
        assertEquals(1, bookedRooms);

        rw.writeRoomsToFile(rooms, filePath);

    }
}
