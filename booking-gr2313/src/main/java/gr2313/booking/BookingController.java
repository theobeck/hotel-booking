package gr2313.booking;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class BookingController {

    @FXML
    private void switchToSecondary() throws IOException {
        BookingApp.setRoot("secondary");
    }

    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;

    
}
