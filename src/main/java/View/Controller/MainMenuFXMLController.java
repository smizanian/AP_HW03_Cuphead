package View.Controller;

import Cuphead.CupheadFXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainMenuFXMLController {

    @FXML
    private Button NewGame;

    @FXML
    private Button Scoreboard;

    @FXML
    private Button ProfileMenu;

    @FXML
    private Button Setting;

    public void goToSettingMenu(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("SettingMenuGraphics");
    }

    public void goToProfileMenu(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("ProfileMenuGraphics");
    }

    public void goToScoreboardMenu(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("ScoreboardMenuGraphics");
    }

    public void newGame(MouseEvent mouseEvent) {
        CupheadFXML.enterGame();
    }
}
