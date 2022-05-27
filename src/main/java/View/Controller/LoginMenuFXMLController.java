package View.Controller;

import Controller.LoginMenuController;
import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;

public class LoginMenuFXMLController {

    @FXML
    private Text error;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private Button guest;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    public static LoginMenuController loginMenuController = new LoginMenuController();

    @FXML
    public void initialize() {
        User.loggedInUser = null;
    }

    public void RegisterButton(MouseEvent mouseEvent) {
        String username = this.username.getText();
        String password = this.password.getText();
        if(!loginMenuController.isUsernameUnique(username)) {
            this.username.setStyle("-fx-border-color: red");
            this.username.setText("");
            this.password.setStyle("-fx-border-color: red");
            this.password.setText("");
            this.error.setText("Username is not unique");
            this.error.setFill(Color.RED);
            return;
        }
        loginMenuController.register(username, password);
        this.error.setText("Registered successfully");
        this.error.setFill(Color.BLUE);
        this.username.setText("");
        this.password.setText("");
    }

    public void LoginButton(MouseEvent mouseEvent) {
        String username = this.username.getText();
        String password = this.password.getText();
        if(loginMenuController.isUserLoggedInWithGuestAccount(username, password)) {
            this.username.setStyle("-fx-border-color: red");
            this.username.setText("");
            this.password.setStyle("-fx-border-color: red");
            this.password.setText("");
            this.error.setText("You can't login with reserved account");
            this.error.setFill(Color.RED);
            return;
        }
        if(!loginMenuController.userExists(username, password)) {
            this.username.setStyle("-fx-border-color: red");
            this.username.setText("");
            this.password.setStyle("-fx-border-color: red");
            this.password.setText("");
            this.error.setText("Invalid username or password");
            this.error.setFill(Color.RED);
            return;
        }
        loginMenuController.login(username, password);
    }

    public void LoginWithGuestAccount(MouseEvent mouseEvent) {
        loginMenuController.loginWithGuestAccount();
    }

    public void setColorTransparent(KeyEvent keyEvent) {
        this.username.setStyle("-fx-border-color: none");
        this.password.setStyle("-fx-border-color: none");
        checkAbilityToClickForButtons();
    }

    private void checkAbilityToClickForButtons() {
        this.error.setText("");
        if(this.username.getText().length() != 0 && this.password.getText().length() != 0) {
            this.login.setDisable(false);
            this.register.setDisable(false);
            this.guest.setDisable(true);
        } else {
            this.guest.setDisable(false);
            this.login.setDisable(true);
            this.register.setDisable(true);
        }
    }

    public void Exit(MouseEvent mouseEvent) throws IOException {
        User.writeUsers("users.json");
        System.exit(0);
    }
}
