package booking.ui;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
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
     * Default constructor for SearchForRoomsController.
     */
    public SearchForRoomsController() {
    }

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(mainMenuController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAvailableRooms(final ActionEvent event) throws IOException {
        AvailableRoomsController availableRoomsController = new AvailableRoomsController();
        if (fromPicker.getValue() == null || toPicker.getValue() == null) {
            return;
        }

        LocalDate from = fromPicker.getValue();
        LocalDate to = toPicker.getValue();
        availableRoomsController.setFrom(from);
        availableRoomsController.setTo(to);
        availableRoomsController.setUsername(username);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("availableRooms.fxml"));
        loader.setController(availableRoomsController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username Change the username of the user.
     */
    public void setUsername(final String username) {
        this.username = username;
    }
}
