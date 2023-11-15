package booking.fxui;

import java.io.IOException;
import java.util.List;

import booking.core.User;
import booking.fxui.internal.RestAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    /**
     * The input field for the password.
     */
    @FXML
    private TextField inputPassword;

    /**
     * The error message.
     */
    @FXML
    private Text errorMsg;

    /**
     * The room access object.
     */
    private RestAccess restAccess;

    /**
     * The list of users.
     */
    private List<User> users;

    /**
     * Default constructor for LoginController.
     *
     * @throws Exception
     */
    public LoginController() throws Exception {
        restAccess = new RestAccess();
        users = restAccess.getAllUsers();
    }

    @FXML
    private void login(final ActionEvent event) throws IOException {
        if (inputUsername.getText().equals("") || inputPassword.getText().equals("")) {
            return;
        }
        if (users.isEmpty()) {
            return;
        }
        for (User targetUser : users) {
            if (targetUser.getUsername().equals(inputUsername.getText())
                    && targetUser.getPassword().equals(inputPassword.getText())) {
                MainMenuController mainMenuController = new MainMenuController();
                mainMenuController.setUser(targetUser);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
                loader.setController(mainMenuController);
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
        }
        errorMsg.setText("Wrong username or password");
    }

    @FXML
    private void signup(final ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
