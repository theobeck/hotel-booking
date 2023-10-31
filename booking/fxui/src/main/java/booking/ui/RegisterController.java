package booking.ui;

import java.io.IOException;
import java.util.List;

import booking.core.User;
import booking.springboot.restserver.UsersAccess;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * A controller for the register user view.
 */
public class RegisterController {
    /**
     * The input field for the username.
     */
    @FXML
    private TextField inputUsername;

    /**
     * The input field for the first name.
     */
    @FXML
    private TextField inputFirstName;

    /**
     * The input field for the last name.
     */
    @FXML
    private TextField inputLastName;

    /**
     * The input field for the password.
     */
    @FXML
    private PasswordField inputPassword;

    /**
     * The error message.
     */
    @FXML
    private Text errorMsg;

    /**
     * The users access object.
     */
    private UsersAccess usersAccess;

    /**
     * The list of users.
     */
    private List<User> users;

    /**
     * Default constructor for RegisterUserController.
     */
    public RegisterController() {
        usersAccess = new UsersAccess();
        users = usersAccess.getAllUsers();
    }

    @FXML
    private void register(final ActionEvent event) throws IOException {
        if (inputUsername.getText().equals("") || inputPassword.getText().equals("")) {
            return;
        }
        for (User u : users) {
            if (u.getUsername().equals(inputUsername.getText())) {
                errorMsg.setText("Username is taken.");
                return;
            }
        }
        MainMenuController mainMenuController = new MainMenuController();
        mainMenuController.setUsername(inputUsername.getText());

        usersAccess.createUser(inputUsername.getText(), inputFirstName.getText(), inputLastName.getText(),
                inputPassword.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(mainMenuController);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
}
