package booking.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import booking.core.Room;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class BookingAppTest extends ApplicationTest  {

    FXMLLoader loader;
    @Override
    public void start(Stage stage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testBookRoomAndShowBooking() {
        loader.getController();
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
    }
}
