package View.Controller;

import Cuphead.CupheadFXML;
import Model.GameModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class SettingMenuFXMLController {

    @FXML
    private Button mute;

    @FXML
    private Button level1;

    @FXML
    private Button level2;

    @FXML
    private Button level3;

    @FXML
    public void initialize() {
        defaultLevel();
    }

    public void level1(MouseEvent mouseEvent) {
        GameModel.gameLevel = 1;
        GameModel.vulnerability = 1;
        GameModel.injury = 3;
        GameModel.hitPoint = 1000;
    }

    public static void defaultLevel() {
        GameModel.gameLevel = 2;
        GameModel.vulnerability = 2;
        GameModel.injury = 2;
        GameModel.hitPoint = 500;
    }

    public void level2(MouseEvent mouseEvent) {
        defaultLevel();
    }


    public void level3(MouseEvent mouseEvent) {
        GameModel.gameLevel = 3;
        GameModel.vulnerability = 3;
        GameModel.injury = 1;
        GameModel.hitPoint = 200;
    }

    public void DevilMode(MouseEvent mouseEvent) {
        if(level1.isDisable()) {
            defaultLevel();
            level1.setDisable(false);
            level2.setDisable(false);
            level3.setDisable(false);
        } else {
            level3(mouseEvent);
            GameModel.gameLevel = 4;
            level1.setDisable(true);
            level2.setDisable(true);
            level3.setDisable(true);
        }
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("MainMenuGraphics");
    }

    public void muteMusic(MouseEvent mouseEvent) {
        if(GameModel.music) {
            GameModel.music = false;
            mute.setText("Unmute");
        } else {
            GameModel.music = true;
            mute.setText("Mute");
        }
    }
}
