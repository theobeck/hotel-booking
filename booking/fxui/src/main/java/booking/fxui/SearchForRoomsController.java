package booking.fxui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A controller for the search for rooms view.
 */
public final class SearchForRoomsController extends AbstractBookingController {

    /**
     * The date picker for the start of the booking.
     */
    @FXML
    private DatePicker toPicker;
    /**
     * The date picker for the end of the booking.
     */
    @FXML
    private DatePicker fromPicker;

    /**
     * The error message.
     */
    @FXML
    private Text errorMsg;

    /**
     * Default constructor for SearchForRoomsController.
     */
    public SearchForRoomsController() {
    }

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.setUser(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(mainMenuController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAvailableRooms(final ActionEvent event) throws Exception {
        AvailableRoomsController availableRoomsController = new AvailableRoomsController();
        if (fromPicker.getValue() == null || toPicker.getValue() == null) {
            return;
        }

        if (fromPicker.getValue().isAfter(toPicker.getValue())) {
            errorMsg.setText("From date must be before to date");
            return;
        }

        LocalDate from = fromPicker.getValue();
        LocalDate to = toPicker.getValue();
        availableRoomsController.setFrom(from);
        availableRoomsController.setTo(to);
        availableRoomsController.setUser(user);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("availableRooms.fxml"));
        loader.setController(availableRoomsController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
