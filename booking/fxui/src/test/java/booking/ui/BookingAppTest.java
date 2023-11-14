package booking.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import booking.core.Booking;
import booking.core.Room;
import booking.core.User;
import booking.ui.internal.RestAccess;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * A test class for the {@link BookingApp} class.
 */
public class BookingAppTest extends ApplicationTest {

    private BookingApp bookingApp;

    public BookingAppTest() {
        bookingApp = new BookingApp();
    }

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
        bookingApp.start(stage);
    }

    @Test
    public void testBookRoomAndShowBooking() {

        RestAccess restAccess = new RestAccess();
        Room roomTen = restAccess.getRoomByNumber(10);
        restAccess.deleteRoomByNumber(10);

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
        roomList.getSelectionModel().select(0);
        clickOn("#book");
        roomList = lookup("#roomList").query();
        assertEquals(current - 2, roomList.getItems().size());

        clickOn("#back");

        clickOn("#showBooking");

        ListView<Room> bookingList = lookup("#bookingList").query();
        List<Booking> userBookings = restAccess.getBookingsByUsername("test");

        assertEquals(userBookings.size(), bookingList.getItems().size());

        bookingList.getSelectionModel().select(0);
        clickOn("#btnCancelBooking");

        userBookings = restAccess.getBookingsByUsername("test");
        assertEquals(userBookings.size(), bookingList.getItems().size());

        clickOn("#btnBack");

        clickOn("#btnSignOut");

        clickOn("#btnLogin");
        List<User> users = restAccess.getAllUsers();
        for (User user : users) {
            restAccess.deleteUserByUsername(user.getUsername());
        }
        clickOn("#btnLogin");
        for (User user : users) {
            restAccess.createUser(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPassword(),
                    user.getGender());
            for (Booking booking : user.getBookings()) {
                restAccess.bookRoomByNumber(booking.getRoomNumber(), booking.getFrom(), booking.getTo(),
                        booking.getBookedBy(), booking.getTotalCostOfBooking(), "user");
            }
        }

        clickOn("#inputUsername");
        write("test2");
        clickOn("#inputPassword");
        write("test2");
        clickOn("#btnLogin");

        clickOn("#bookRoom");

        clickOn("#back");

        clickOn("#showBooking");

        bookingList = lookup("#bookingList").query();
        assertEquals(0, bookingList.getItems().size());

        clickOn("#btnBack");

        clickOn("#btnSignOut");

        assertTrue(null == restAccess.getUserByUsername("test3"));

        clickOn("#btnSignup");
        clickOn("#btnBack");
        clickOn("#btnSignup");

        clickOn("#inputUsername");
        write("test3");
        clickOn("#inputPassword");
        write("test3");
        clickOn("#inputFirstName");
        write("test3");
        clickOn("#inputLastName");
        write("test3");
        clickOn("#genderCombobox");
        theRobot.push(KeyCode.DOWN);
        theRobot.push(KeyCode.ENTER);
        clickOn("#btnRegister");

        assertFalse(null == restAccess.getUserByUsername("test3"));
        restAccess.deleteUserByUsername("test3");
        assertTrue(null == restAccess.getUserByUsername("test3"));

        userBookings = restAccess.getBookingsByUsername("test");

        for (Booking booking : userBookings) {
            restAccess.cancelBooking(booking.getRoomNumber(), booking.getBookedBy(), booking.getFrom(), booking.getTo(),
                    booking.getTotalCostOfBooking());
        }

        userBookings = restAccess.getBookingsByUsername("test");

        assertEquals(0, userBookings.size());

        assertTrue(restAccess.getAllRooms().size() == 10);
        restAccess.deleteRoomByNumber(10);
        restAccess.createRoom(roomTen.getRoomNumber(), roomTen.getRoomCapacity(), roomTen.getPricePerNight());
        for (Booking booking : roomTen.getBookings()) {
            restAccess.bookRoomByNumber(booking.getRoomNumber(), booking.getFrom(), booking.getTo(),
                    booking.getBookedBy(), booking.getTotalCostOfBooking(), "room");

        }
    }
}
