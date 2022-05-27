package View.Controller;

import Controller.ProfileMenuController;
import Cuphead.CupheadFXML;
import Model.User;
import com.sun.glass.ui.Clipboard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileMenuFXMLController {
    
    @FXML
    private Button passwordOKButton;

    @FXML
    private TextField changePasswordTextField;

    @FXML
    private Button changePassword;

    @FXML
    private Text usernameError;

    @FXML
    private Button usernameOKButton;

    @FXML
    private TextField changeUsernameTextField;

    @FXML
    private Button changeUsername;

    @FXML
    private VBox boxOfChangingUsernameAndPassword;

    @FXML
    private Button deleteAccountButton;

    @FXML
    private Text usernameText;

    @FXML
    private VBox boxOfAvatar;

    public static ProfileMenuController profileMenuController = new ProfileMenuController();
    private Desktop desktop = Desktop.getDesktop();

    @FXML
    public void initialize() {
        addAvatar();
        setUsername();
        setButtons();
    }

    private void setButtons() {
        if(profileMenuController.isUserGuest(User.loggedInUser)) {
            this.deleteAccountButton.setVisible(false);
            this.boxOfChangingUsernameAndPassword.setVisible(false);
        } else {
            this.deleteAccountButton.setVisible(true);
            this.boxOfChangingUsernameAndPassword.setVisible(true);
        }
    }

    private void setUsername() {
        usernameText.setText(User.loggedInUser.getUsername());
    }

    private void addAvatar() {
        Circle circle = new Circle(75);
        circle.setCursor(Cursor.HAND);
        circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setEffect(new DropShadow());
            }
        });
        circle.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                circle.setEffect(null);
            }
        });
        circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(boxOfAvatar.getChildren().get(1).isVisible()) {
                    hideAvatars();
                } else {
                    showAvatars();
                }
            }
        });
        Image image;
        image = new Image(User.loggedInUser.getAvatarURL());
        ImagePattern profilePicture = new ImagePattern(image);
        circle.setFill(profilePicture);
        boxOfAvatar.getChildren().add(circle);
        addAnotherAvatars();
    }

    private void addAnotherAvatars() {
        for (int i = 0; i < User.numberOfAvatars; i++) {
            Circle circle = new Circle(30);
            ImagePattern profilePicture = new ImagePattern(new Image(User.createAvatarURL(i + 1)));
            circle.setFill(profilePicture);
            circle.setVisible(false);
            int finalI = i;
            circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    hideAvatars();
                    ImagePattern profilePicture = new ImagePattern(new Image(User.createAvatarURL(finalI + 1)));
                    Circle circle = (Circle) boxOfAvatar.getChildren().get(0);
                    circle.setFill(profilePicture);
                    try {
                        User.loggedInUser.setAvatarURL(User.createAvatarURL(finalI + 1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            boxOfAvatar.getChildren().add(circle);
        }
    }

    private void showAvatars() {
        for (int i = 0; i < User.numberOfAvatars; i++) {
            boxOfAvatar.getChildren().get(i + 1).setVisible(true);
        }
    }

    private void hideAvatars() {
        for (int i = 0; i < User.numberOfAvatars; i++) {
            boxOfAvatar.getChildren().get(i + 1).setVisible(false);
        }
    }

    public void accountLogout(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("LoginMenuGraphics");
    }

    public void deleteAccount(MouseEvent mouseEvent) {
        User.removeUser(User.loggedInUser.getUsername());
        CupheadFXML.changeMenu("LoginMenuGraphics");
    }

    public void openUsernameTextField(MouseEvent mouseEvent) {
        this.changeUsernameTextField.setVisible(true);
        this.usernameOKButton.setVisible(true);
    }

    public void changeUsername(MouseEvent mouseEvent) throws IOException {
        String username = this.changeUsernameTextField.getText();
        if(!profileMenuController.isUsernameUnique(username)) {
            this.changeUsernameTextField.setStyle("-fx-border-color: red");
            this.changeUsernameTextField.setText("");
            this.usernameError.setText("Username is not unique");
            this.usernameError.setFill(Color.RED);
            return;
        }
        profileMenuController.changeUsername(username, User.loggedInUser);
        resetProfileMenu();
    }

    private void resetProfileMenu() {
        setUsername();
        setChangesButtons();
    }

    private void setChangesButtons() {
        this.changeUsernameTextField.setText("");
        this.changeUsernameTextField.setVisible(false);
        this.passwordOKButton.setDisable(true);
        this.usernameOKButton.setVisible(false);
        this.usernameError.setText("");
        this.changePasswordTextField.setText("");
        this.changePasswordTextField.setVisible(false);
        this.passwordOKButton.setDisable(true);
        this.passwordOKButton.setVisible(false);
    }

    public void visibleOkUsername(KeyEvent mouseEvent) {
        this.usernameError.setText("");
        this.changeUsernameTextField.setStyle("-fx-border-color: none");
        this.usernameOKButton.setDisable(false);
    }

    public void openPasswordTextField(MouseEvent mouseEvent) {
        this.changePasswordTextField.setVisible(true);
        this.passwordOKButton.setVisible(true);
    }

    public void visibleOkPassword(KeyEvent keyEvent) {
        this.passwordOKButton.setDisable(false);
    }

    public void changePassword(MouseEvent mouseEvent) throws IOException {
        String password = this.changePasswordTextField.getText();
        profileMenuController.changePassword(password, User.loggedInUser);
        resetProfileMenu();
    }

    public void loadAvatar(MouseEvent mouseEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(CupheadFXML.stage);
        if (file != null) {
            User.loggedInUser.setAvatarURL(file.toURI().toURL().toExternalForm());
            ImagePattern profilePicture = new ImagePattern(new Image(User.loggedInUser.getAvatarURL()));
            Circle circle = (Circle) boxOfAvatar.getChildren().get(0);
            circle.setFill(profilePicture);
        }
    }

    private void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("MainMenuGraphics");
    }
}
