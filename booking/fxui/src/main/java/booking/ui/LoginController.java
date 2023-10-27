package booking.ui;

import java.io.IOException;
import java.util.List;

import booking.core.User;
import booking.json.ReadWrite;
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

    @FXML
    private Text errorMsg;

    /**
     * The file manager object.
     */
    private ReadWrite fileManager = new ReadWrite();

    /**
     * The file path to the users file.
     */
    private String filePath = "src/main/resources/booking/ui/users.json";

    /**
     * The list of users.
     */
    private List<User> users = fileManager.readUserFromFile(filePath);


    /**
     * Default constructor for LoginController.
     */
    public LoginController() {
    }

    @FXML
    private void goToMainMenu(final ActionEvent event) throws IOException {
        if (inputUsername.getText().equals("") && inputPassword.getText().equals("")) {
            return;
        }
        for(User u: users){
            if(u.getUsername().equals(inputUsername.getText()) && u.getPassword().equals(inputPassword.getText())){
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
        errorMsg.setText("Wrong username or password");
    }

    @FXML
    private void goToMainMenuSignup(final ActionEvent event) throws IOException {
        if (inputUsername.getText().equals("") && inputPassword.getText().equals("")) {
            errorMsg.setText("Please enter a username and password");
            return;
        }
        for(User u: users){
            if(u.getUsername().equals(inputUsername.getText())){
                errorMsg.setText("Username already exists");
                return;
            }
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
