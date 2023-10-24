package booking.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    /**
     * The username of the user.
     */
    private String username;

    /**
     * The input field for the username.
     */
    @FXML
    private TextField inputUsername;

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.setUsername(inputUsername.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(mainMenuController);
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
