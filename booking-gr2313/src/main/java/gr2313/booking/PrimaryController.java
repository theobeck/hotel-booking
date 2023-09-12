package gr2313.booking;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML private DatePicker fromDate;
    @FXML private DatePicker toDate;
}
