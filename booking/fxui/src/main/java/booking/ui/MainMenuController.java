package booking.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A controller for the main menu view.
 */
public final class MainMenuController {

    /**
     * The username of the user.
     */
    private String username;

    /**
     * Default constructor for MainMenuController.
     */
    public MainMenuController() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    @FXML
    private void goToLogin(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToUserBookings(final ActionEvent event) throws IOException {
        UserBookingsController showUserBookingsController = new UserBookingsController();
        showUserBookingsController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("userBookings.fxml"));
        loader.setController(showUserBookingsController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void goToSearchForRooms(final ActionEvent event) throws IOException {
        SearchForRoomsController searchForRoomsController = new SearchForRoomsController();
        searchForRoomsController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchForRooms.fxml"));
        loader.setController(searchForRoomsController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
