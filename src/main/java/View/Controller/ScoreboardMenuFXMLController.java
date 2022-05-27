package View.Controller;

import Controller.ScoreboardController;
import Cuphead.CupheadFXML;
import Model.User;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static javafx.scene.paint.Color.*;

public class ScoreboardMenuFXMLController {

    @FXML
    private VBox menu;

    @FXML
    private VBox scoreboard;

    @FXML
    private Text levelText;

    @FXML
    private Button total;

    @FXML
    private Button level1;

    @FXML
    private Button level2;

    @FXML
    private Button level3;

    @FXML
    private Button devilMode;

    private int numberOfUsers;

    private static final ScoreboardController scoreboardController = new ScoreboardController();

    @FXML
    public void initialize() {
        showTotalScore();
    }

    public void showTotalScore(MouseEvent mouseEvent) {
        deleteAll();
        showTotalScore();
    }

    private void deleteAll() {
        if (this.numberOfUsers > 0) {
            this.scoreboard.getChildren().subList(0, this.numberOfUsers).clear();
        }
    }

    private void showTotalScore() {
        scoreboardController.sortUsersInTotal(User.users);
        int index = 0;
        String id = "score";
        for (User user : User.users) {
            if(index == 10) {
                break;
            }
            index++;
            Text textUsername = new Text(user.getUsername());
            Text textScore = new Text(Integer.toString(user.getScoreLevel1() + user.getScoreLevel2() + user.getScoreLevel3() + user.getScoreDevilMode()));
            textScore.setText(textScore.getText() + "\t time: " + user.getMinTime());
            fillScoreboard(index, id, textUsername, textScore);
        }
        numberOfUsers = index;
    }

    public void showLevel1Score(MouseEvent mouseEvent) {
        deleteAll();
        scoreboardController.sortUsersInLevel1(User.users);
        int index = 0;
        String id = "score";
        for (User user : User.users) {
            if(index == 10) {
                break;
            }
            index++;
            Text textUsername = new Text(user.getUsername());
            Text textScore = new Text(Integer.toString(user.getScoreLevel1()));
            textScore.setText(textScore.getText() + "\t time: " + user.getMinTime());
            fillScoreboard(index, id, textUsername, textScore);
        }
        numberOfUsers = index;
    }

    private void fillScoreboard(int index, String id, Text textUsername, Text textScore) {
        textUsername.setId(id);
        textScore.setId(id);
        if(index == 1) {
            textUsername.setFill(GOLD);
            textScore.setFill(GOLD);
        } else if (index == 2) {
            textUsername.setFill(SILVER);
            textScore.setFill(SILVER);
        } else if (index == 3) {
            textUsername.setFill(BROWN);
            textScore.setFill(BROWN);
        }
        HBox hBox = new HBox();
        hBox.getChildren().add(textUsername);
        hBox.getChildren().add(textScore);
        hBox.setSpacing(30);
        scoreboard.getChildren().add(hBox);
    }

    public void showLevel2Score(MouseEvent mouseEvent) {
        deleteAll();
        scoreboardController.sortUsersInLevel2(User.users);
        int index = 0;
        String id = "score";
        for (User user : User.users) {
            if(index == 10) {
                break;
            }
            index++;
            Text textUsername = new Text(user.getUsername());
            Text textScore = new Text(Integer.toString(user.getScoreLevel2()));
            textScore.setText(textScore.getText() + "\t time: " + user.getMinTime());
            fillScoreboard(index, id, textUsername, textScore);
        }
        numberOfUsers = index;
    }

    public void showLevel3Score(MouseEvent mouseEvent) {
        deleteAll();
        scoreboardController.sortUsersInLevel3(User.users);
        int index = 0;
        String id = "score";
        for (User user : User.users) {
            if(index == 10) {
                break;
            }
            index++;
            Text textUsername = new Text(user.getUsername());
            Text textScore = new Text(Integer.toString(user.getScoreLevel3()));
            textScore.setText(textScore.getText() + "\t time: " + user.getMinTime());
            fillScoreboard(index, id, textUsername, textScore);
        }
        numberOfUsers = index;
    }

    public void showDevilModeScore(MouseEvent mouseEvent) {
        deleteAll();
        scoreboardController.sortUsersInDevilMode(User.users);
        int index = 0;
        String id = "score";
        for (User user : User.users) {
            if(index == 10) {
                break;
            }
            index++;
            Text textUsername = new Text(user.getUsername());
            Text textScore = new Text(Integer.toString(user.getScoreDevilMode()));
            textScore.setText(textScore.getText() + "\t time: " + user.getMinTime());
            fillScoreboard(index, id, textUsername, textScore);
        }
        numberOfUsers = index;
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        CupheadFXML.changeMenu("MainMenuGraphics");
    }
}
