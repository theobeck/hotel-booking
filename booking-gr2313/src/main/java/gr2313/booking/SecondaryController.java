package gr2313.booking;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        BookingApp.setRoot("booking");
    }
}
//yo