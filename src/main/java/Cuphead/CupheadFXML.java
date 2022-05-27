package Cuphead;

import Model.User;
import View.Components.Plane;
import View.Controller.GameFXMLController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class CupheadFXML {
    public static Scene scene;
    public static Stage stage;
    public static boolean gameStarted;

    public static void writeUsers() throws IOException {
        User.writeUsers("users.json");
    }

    public static void readUsers() throws IOException {
        User.readUsers("users.json");
        addGuest();
    }

    private static void addGuest() {
        if(User.getUserByUsername("Guest") == null) {
            User user = new User("Guest", "123456");
            User.addUser(user);
        }
    }

    public static void changeMenu(String name){
        Parent root = loadFXMLMenus(name);
        CupheadFXML.scene.setRoot(root);
    }

    public static void enterGame() {
        gameStarted = true;
        Pane root = loadGame("Cuphead");
        CupheadFXML.scene.setRoot(root);
        root.getChildren().get(1).requestFocus();
    }

    private static Pane loadGame(String name){
        try {
            URL address = new URL(Objects.requireNonNull(CupheadFXML.class.getResource("/fxml/Game/" + name + ".fxml")).toString());
            return FXMLLoader.load(address);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Pane loadFXMLMenus(String name){
        gameStarted = false;
        try {
            URL address = new URL(Objects.requireNonNull(CupheadFXML.class.getResource("/fxml/Menus/" + name + ".fxml")).toString());
            return FXMLLoader.load(address);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void restartGame() {
        Plane.nullInstance();
        changeMenu("MainMenuGraphics");
        enterGame();
    }
}
