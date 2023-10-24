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

public class BookRoomController {

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
     * The availableController.
     */
    private AvailableController availableController = new AvailableController();

    /**
     * The username of the user.
     */
    //#TODO fix after login page has been created
    private String username = "filler";


    @FXML
    private void goToBooking(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToAvailable(final ActionEvent event) throws IOException {
        if (fromPicker.getValue() == null || toPicker.getValue() == null) {
            return;
            //#TODO add error message
        }

        LocalDate from = fromPicker.getValue();
        LocalDate to = toPicker.getValue();
        availableController.setFrom(from);
        availableController.setTo(to);
        availableController.setUsername(username);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("available.fxml"));
        loader.setController(availableController);
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
    public void setUsername(String username) {
        this.username = username;
    }
}
