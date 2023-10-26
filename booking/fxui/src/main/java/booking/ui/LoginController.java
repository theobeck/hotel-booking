package booking.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A controller for the login view.
 */
public class LoginController {
    /**
     * The input field for the username.
     */
    @FXML
    private TextField inputUsername;

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        if (inputUsername.getText().equals("")) {
            return;
        }
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
}
