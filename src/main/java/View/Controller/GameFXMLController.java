package View.Controller;

import Cuphead.CupheadFXML;
import Model.CupheadModel;
import Model.GameModel;
import Model.User;
import View.Components.*;
import View.Transitions.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameFXMLController {

    @FXML
    private Pane pane;

    private boolean bomb;
    public static boolean rocketValid;
    private VBox mainVBox;
    private VBox guideVBox;
    private Rectangle bombOrBulletIcon;
    private static Rectangle background;
    private Boss boss;
    private TimeTransition timeTransition;


    @FXML
    public void initialize() {
        TransitionImages.start();
        setBackground();
        bomb = false;
        rocketValid = true;
        Plane plane = setCuphead();
        pane.getChildren().add(plane);
        setPauseMenu();
        setBombOrBulletIcon();
        setBossAndMiniBoss();
        setCupheadHitPoint();
        setRocketUsage();
        setTimeText();
    }

    private void setRocketUsage() {
        Rectangle rectangle = new Rectangle(500, 10, 150, 30);
        Rectangle redRectangle = new Rectangle(500, 10, 150, 30);
        redRectangle.setFill(new ImagePattern(new Image("/pics/Game/BossAndMiniBoss/BossHitPoint.jpg")));
        Text text = new Text();
        text.setText("Validation of using rocket");
        text.setLayoutX(500);
        text.setLayoutY(60);
        RocketTransition rocketTransition = new RocketTransition(redRectangle);
        rocketTransition.play();
        pane.getChildren().add(rectangle);
        pane.getChildren().add(redRectangle);
        pane.getChildren().add(text);
    }

    private void setTimeText() {
        Text text = new Text("00:00" + "\n" + "Score: 0");
        text.setLayoutY(30);
        text.setLayoutX(1000);
        pane.getChildren().add(text);
        timeTransition = new TimeTransition(text);
        timeTransition.play();
    }


    private void setBossAndMiniBoss() {
        boss = new Boss(this);
        boss.setFill(new ImagePattern(new Image("/pics/Game/BossAndMiniBoss/BossFly/1.png")));
        boss.setPane(pane);
        pane.getChildren().add(boss);
        boss.fly();
        setBossHitPoint();
    }

    private void setBossHitPoint() {
        Rectangle rectangle = new Rectangle(1100, 10, 150, 30);
        Rectangle redRectangle = new Rectangle(1100, 10, 150, 30);
        redRectangle.setFill(new ImagePattern(new Image("/pics/Game/BossAndMiniBoss/BossHitPoint.jpg")));
        Text text = new Text();
        text.setText("Boss hit point: ");
        text.setLayoutX(1100);
        text.setLayoutY(60);
        BossReduceHitPoint bossReduceHitPoint = new BossReduceHitPoint(boss, redRectangle, text);
        bossReduceHitPoint.play();
        pane.getChildren().add(rectangle);
        pane.getChildren().add(redRectangle);
        pane.getChildren().add(text);
    }

    private void setCupheadHitPoint() {
        Rectangle rectangle = new Rectangle(800, 10, 150, 30);
        Rectangle redRectangle = new Rectangle(800, 10, 150, 30);
        redRectangle.setFill(new ImagePattern(new Image("/pics/Game/Plane/cupheadHitPoint.png")));
        Text text = new Text();
        text.setText("Cuphead hit point: ");
        text.setLayoutX(800);
        text.setLayoutY(60);
        CupheadReduceHitPointTransition cupheadReduceHitPointTransition = new CupheadReduceHitPointTransition(redRectangle, text);
        cupheadReduceHitPointTransition.play();
        pane.getChildren().add(rectangle);
        pane.getChildren().add(redRectangle);
        pane.getChildren().add(text);
    }

    private void setBombOrBulletIcon() {
        bombOrBulletIcon = new Rectangle(80, 0, 50, 50);
        bombOrBulletIcon.setFill(new ImagePattern(new Image("/pics/Game/Plane/bulletIcon.png")));
        pane.getChildren().add(bombOrBulletIcon);
    }

    private void pause() {
        for (Node child : pane.getChildren()) {
            child.setDisable(true);
        }
        mainVBox.setDisable(false);
        mainVBox.getChildren().get(0).setDisable(false);
        pauseTransitions();
    }

    private void pauseTransitions() {
        timeTransition.pauseTime();
        for (BackgroundTransition transition : BackgroundTransition.backgroundTransitions) {
            transition.pause();
        }
        for (BombMovingTransition transition : BombMovingTransition.bombMovingTransitions) {
            transition.pause();
        }
        for (BossFlyTransition transition : BossFlyTransition.bossFlyTransitions) {
            transition.pause();
        }
        for (BossMovingTransition transition : BossMovingTransition.bossMovingTransitions) {
            transition.pause();
        }
        for (BossReduceHitPoint transition : BossReduceHitPoint.bossReduceHitPoints) {
            transition.pause();
        }
        for (BossShootTransition transition : BossShootTransition.bossShootTransitions) {
            transition.pause();
        }
        for (BulletMovingTransition transition : BulletMovingTransition.bulletMovingTransitions) {
            transition.pause();
        }
        for (BulletShootingTransition transition : BulletShootingTransition.bulletShootingTransitions) {
            transition.pause();
        }
        for (CupheadReduceHitPointTransition transition : CupheadReduceHitPointTransition.cupheadReduceHitPointTransitions) {
            transition.pause();
        }
        for (CupheadToRocketTransition transition : CupheadToRocketTransition.cupheadToRocketTransitions) {
            transition.pause();
        }
        for (CupheadTurnLeftTransition transition : CupheadTurnLeftTransition.cupheadTurnLeftTransitions) {
            transition.pause();
        }
        for (CupheadTurnRightTransition transition : CupheadTurnRightTransition.cupheadTurnRightTransitions) {
            transition.pause();
        }
        for (EggDestroyTransition transition : EggDestroyTransition.eggDestroyTransitions) {
            transition.pause();
        }
        for (EggTransition transition : EggTransition.eggTransitions) {
            transition.pause();
        }
        for (MiniBossDeathTransition transition : MiniBossDeathTransition.miniBossDeathTransitions) {
            transition.pause();
        }
        for (MiniBossFlyTransition transition : MiniBossFlyTransition.miniBossFlyTransitions) {
            transition.pause();
        }
        for (RocketExplodeTransition transition : RocketExplodeTransition.rocketExplodeTransitions) {
            transition.pause();
        }
        for (RocketMoveTransition transition : RocketMoveTransition.rocketMoveTransitions) {
            transition.pause();
        }
    }

    private void setPauseMenu() {
        VBox mainVBox = new VBox();
        Button button = new Button();
        Button muteButton = new Button("Mute");
        VBox vBox = new VBox();
        setButtons(vBox);
        button.setText("Pause");
        button.setFocusTraversable(false);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (vBox.isDisable()) {
                    pause();
                    vBox.setVisible(true);
                    vBox.setDisable(false);
                } else {
                    unpause();
                    vBox.setDisable(true);
                    vBox.setVisible(false);
                }
            }
        });
        mainVBox.getChildren().add(button);
        if(GameModel.music) {
            vBox.getChildren().add(muteButton);
        }
        mainVBox.getChildren().add(vBox);
        this.mainVBox = mainVBox;
        pane.getChildren().add(mainVBox);
        setGuide();
    }

    private void setGuide() {
        guideVBox = new VBox();
        Rectangle rectangle = new Rectangle(250, 320);
        rectangle.setFill(new ImagePattern(new Image("/pics/Game/Guide.png")));
        Button button = new Button();
        button.setText("Back");
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                unpause();
                guideVBox.setVisible(false);
            }
        });
        guideVBox.getChildren().add(rectangle);
        guideVBox.getChildren().add(button);
        guideVBox.setLayoutX(400);
        guideVBox.setLayoutY(200);
        guideVBox.setVisible(false);
        pane.getChildren().add(guideVBox);
    }

    private void showGuide() {
        guideVBox.setDisable(false);
        for (Node child : guideVBox.getChildren()) {
            child.setDisable(false);
            child.setVisible(true);
        }
        guideVBox.setVisible(true);
    }

    private void setButtons(VBox vBox) {
        vBox.setVisible(false);
        vBox.setDisable(true);

        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GameFXMLController.exitGame();
            }
        });
        vBox.getChildren().add(exitButton);

        Button userManualButton = new Button();
        userManualButton.setText("User Manual");
        userManualButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showGuide();
            }
        });
        vBox.getChildren().add(userManualButton);

        Button restartButton = new Button();
        restartButton.setText("Restart");
        restartButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CupheadFXML.restartGame();
            }
        });
        vBox.getChildren().add(restartButton);

        if (User.loggedInUser.isGuest()) {
            return;
        }

        Button saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        vBox.getChildren().add(saveButton);

        for (Node child : vBox.getChildren()) {
            child.setFocusTraversable(false);
        }

    }

    public void victory() {
        User.loggedInUser.gameResult(CupheadModel.score, timeTransition.getGameTimeInSecond(), GameModel.gameLevel);
        pause();
        Rectangle rectangle = new Rectangle(0, 0, 1280, 720);
        pane.getChildren().add(rectangle);
        VictoryTransition victoryTransition = new VictoryTransition(rectangle);
        victoryTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                exitMenu();
            }
        });
        victoryTransition.play();
    }

    private void exitMenu() {
        VBox vBox = new VBox();
        vBox.setLayoutX(600);
        vBox.setLayoutY(200);
        Text text1 = new Text(timeTransition.getTime());
        vBox.getChildren().add(text1);
        Button button1 = new Button("Back to main menu");
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitGame();
            }
        });
        Button button2 = new Button("Restart");
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CupheadFXML.restartGame();
            }
        });
        vBox.getChildren().add(button1);
        vBox.getChildren().add(button2);
        pane.getChildren().add(vBox);
    }

    public void gameOver() {
        User.loggedInUser.gameResult(CupheadModel.score, timeTransition.getGameTimeInSecond(), GameModel.gameLevel);
        pause();
        VBox vBox = new VBox();
        vBox.setLayoutX(500);
        vBox.setLayoutY(200);
        Text text = new Text(timeTransition.getTime() + "\nBoss hit point: " + (boss.getBoss().getHitPoint()/10));
        vBox.getChildren().add(text);
        CupheadReduceHitPointTransition.setZero();
        Random random = new Random();
        int quote = random.nextInt(2) + 1;
        Rectangle rectangle = new Rectangle(280, 320);
        rectangle.setFill(new ImagePattern(new Image("/pics/Game/quote" + quote + ".png")));
        Button button1 = new Button("Back to main menu");
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exitGame();
            }
        });
        Button button2 = new Button("Restart");
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CupheadFXML.restartGame();
            }
        });
        vBox.getChildren().add(rectangle);
        vBox.getChildren().add(button1);
        vBox.getChildren().add(button2);
        pane.getChildren().add(vBox);
    }

    private static void exitGame() {
        Plane.nullInstance();
        SettingMenuFXMLController.defaultLevel();
        CupheadFXML.changeMenu("MainMenuGraphics");
    }

    private void unpause() {
        for (Node child : pane.getChildren()) {
            child.setDisable(false);
        }
        pane.getChildren().get(1).requestFocus();
        mainVBox.getChildren().get(1).setDisable(true);
        mainVBox.getChildren().get(1).setVisible(false);
        unpauseTransitions();
    }

    private void unpauseTransitions() {
        timeTransition.unpauseTime();
        for (BackgroundTransition transition : BackgroundTransition.backgroundTransitions) {
            transition.play();
        }
        for (BombMovingTransition transition : BombMovingTransition.bombMovingTransitions) {
            transition.play();
        }
        for (BossFlyTransition transition : BossFlyTransition.bossFlyTransitions) {
            transition.play();
        }
        for (BossMovingTransition transition : BossMovingTransition.bossMovingTransitions) {
            transition.play();
        }
        for (BossReduceHitPoint transition : BossReduceHitPoint.bossReduceHitPoints) {
            transition.play();
        }
        for (BossShootTransition transition : BossShootTransition.bossShootTransitions) {
            transition.play();
        }
        for (BulletMovingTransition transition : BulletMovingTransition.bulletMovingTransitions) {
            transition.play();
        }
        for (BulletShootingTransition transition : BulletShootingTransition.bulletShootingTransitions) {
            transition.play();
        }
        for (CupheadReduceHitPointTransition transition : CupheadReduceHitPointTransition.cupheadReduceHitPointTransitions) {
            transition.play();
        }
        for (CupheadTurnLeftTransition transition : CupheadTurnLeftTransition.cupheadTurnLeftTransitions) {
            transition.play();
        }
        for (CupheadTurnRightTransition transition : CupheadTurnRightTransition.cupheadTurnRightTransitions) {
            transition.play();
        }
        for (EggDestroyTransition transition : EggDestroyTransition.eggDestroyTransitions) {
            transition.play();
        }
        for (EggTransition transition : EggTransition.eggTransitions) {
            transition.play();
        }
        for (MiniBossDeathTransition transition : MiniBossDeathTransition.miniBossDeathTransitions) {
            transition.play();
        }
        for (MiniBossFlyTransition transition : MiniBossFlyTransition.miniBossFlyTransitions) {
            transition.play();
        }
        for (RocketMoveTransition transition : RocketMoveTransition.rocketMoveTransitions) {
            if(Plane.getInstance().isRocket) {
                transition.play();
            }
        }
    }

    private Plane setCuphead() {
        Plane plane = Plane.getInstance();
        plane.setBackground(0);
        plane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (plane.isRocket) {
                    return;
                }
                String keyName = event.getCode().getName();
                switch (keyName) {
                    case "Left":
                        plane.moveLeft();
                        break;
                    case "Right":
                        plane.moveRight();
                        break;
                    case "Up":
                        plane.moveUp();
                        break;
                    case "Down":
                        plane.moveDown();
                        break;
                    case "Space":
                        if (!bomb) {
                            shootBullet(plane.getX() + plane.getWidth() - 10, plane.getY() + plane.getHeight() / 2);
                        } else {
                            shootBomb(plane.getX() + plane.getWidth() / 2, plane.getY() + plane.getHeight() - 10);
                        }
                        break;
                    case "Tab":
                        changeIcon();
                        break;
                    case "Alt":
                        if (rocketValid) {
                            shootRocket(plane);
                        }
                        break;
                }
            }
        });
        return plane;
    }

    private void shootRocket(Plane plane) {
        plane.moveRight();
        plane.isRocket = true;
        rocketValid = false;
        RocketTransition.rocketTransition.shootRocket();
        CupheadToRocketTransition cupheadToRocketTransition = new CupheadToRocketTransition(plane);
        cupheadToRocketTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                plane.moveRocket(pane);
            }
        });
        cupheadToRocketTransition.play();
    }

    private void changeIcon() {
        if (bomb) {
            bomb = false;
            bombOrBulletIcon.setFill(new ImagePattern(new Image("/pics/Game/Plane/bulletIcon.png")));
        } else {
            bomb = true;
            bombOrBulletIcon.setFill(new ImagePattern(new Image("/pics/Game/Plane/bombIcon.png")));
        }
    }

    private void shootBomb(double x, double y) {
        Bomb bomb = new Bomb(x, y);
        pane.getChildren().add(bomb);
        BombMovingTransition bombMovingTransition = new BombMovingTransition(bomb);
        bombMovingTransition.play();
    }

    private void shootBullet(double x, double y) {
        Bullet bullet = new Bullet(x, y);
        Rectangle rectangle = new Rectangle(x, y - 20, 50, 50);
        pane.getChildren().add(rectangle);
        pane.getChildren().add(bullet);
        BulletShootingTransition bulletShootingTransition = new BulletShootingTransition(rectangle);
        bulletShootingTransition.play();
        bulletShootingTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rectangle.setVisible(false);
            }
        });
        BulletMovingTransition bulletMovingTransition = new BulletMovingTransition(bullet);
        bulletMovingTransition.play();
    }

    private void setBackground() {
        background = new Rectangle(0, 0, 5000, 720);
        BackgroundTransition backgroundTransition = new BackgroundTransition(background);
        backgroundTransition.play();
        pane.getChildren().add(background);
        Image image = new Image("/pics/Game/Background.png");
        ImagePattern backgroundImage = new ImagePattern(image);
        background.setFill(backgroundImage);
    }

    public static Rectangle getBackground() {
        return background;
    }
}
