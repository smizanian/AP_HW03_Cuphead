import Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import Cuphead.CupheadFXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {



    public static void main(String[] args) throws IOException {
        CupheadFXML.readUsers();
        launch();
        CupheadFXML.writeUsers();
    }


    @Override
    public void start(Stage stage) throws Exception {
        CupheadFXML.stage = stage;
        Pane root = CupheadFXML.loadFXMLMenus("LoginMenuGraphics");
        CupheadFXML.scene = new Scene(root);
        stage.setScene(CupheadFXML.scene);
        stage.show();
    }



    /*
    To create a JAR file from a Maven project in IntelliJ IDEA, go to the Maven Tool Window
    (View → Tool Windows → Maven), expand your project in the tree,
     expand Lifecycle, and then double-click on package.
     Maven will compile your package, and the compiled JAR file will be written to the target/ directory.
    */

}
