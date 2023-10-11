package booking.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import booking.core.Room;
import booking.ui.BookRoomController;
import booking.ui.ShowBookingController;

import static org.testfx.api.FxAssert.verifyThat;

public class BookingAppTest extends ApplicationTest  {

    private BookRoomController bookRoomController;
    private ShowBookingController showBookingController;

    @Override
    public void start(Stage stage) throws Exception {
        // Load the initial scene (the landing page) for the tests
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        // Load the controllers for the pages we want to test
        bookRoomController = new BookRoomController();
        showBookingController = new ShowBookingController();
    }

    @Test
    public void testBookRoomAndShowBooking() {
        // Interaction with the "Book Room" page
        clickOn("#bookRoom"); // Go to the landing page
        clickOn("#back"); // Go to the "Book Room" page
        clickOn("#showBooking"); // Go back to the landing page

        // Interaction with the "Show Booking" page
        clickOn("#back"); // Go to the "Show Booking" page

        // Add a room to the list (assuming you have a method to add rooms in your code)
        Room room = new Room(/* Initialize with appropriate details */);
        bookRoomController.getRooms().add(room);

        // Reload the "Show Booking" page to reflect the changes
        showBookingController.initialize();

        // Verify that the room is displayed in the list
        verifyThat("#bookingList", NodeMatchers.hasChildren(1)); // Assuming 1 room is in the list
    }
}
