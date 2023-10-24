package booking.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuController {

    /**
     * The username of the user.
     */
    private String username;

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
    private void goToShowUserBookings(final ActionEvent event) throws IOException {
        ShowUserBookingsController showUserBookingsController = new ShowUserBookingsController();
        showUserBookingsController.setUsername(username);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showUserBookings.fxml"));
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
