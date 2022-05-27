package View.Transitions;

import View.Components.Boss;
import View.Components.Plane;
import View.Controller.GameFXMLController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class BossFlyTransition extends Transition {

    private Boss boss;
    private GameFXMLController gameFXMLController;
    private long counter;
    public static ArrayList<BossFlyTransition> bossFlyTransitions = new ArrayList<>();

    public BossFlyTransition(Boss boss, GameFXMLController gameFXMLController) {
        this.boss = boss;
        this.counter = 0;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(300));
        bossFlyTransitions.add(this);
        this.gameFXMLController = gameFXMLController;
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) (v * 5);
        boss.setFill(new ImagePattern(TransitionImages.bossFlyImages[frame]));
        winOrLoseCheck();
        calculateToShoot();
        calculateToCreateMiniBoss();
        collisionCheck();
    }


    private void winOrLoseCheck() {
        if(boss.getBoss().getHitPoint() <= 0) {
            gameFXMLController.victory();
        } else if(Plane.getCupheadModel().getHitPoint() <= 0) {
            gameFXMLController.gameOver();
        }
    }

    private void collisionCheck() {
        boss.checkCollision();
    }

    private void calculateToShoot() {
        Random random = new Random();
        int number = random.nextInt(1000);
        if(number < 500 && number > 450) {
            boss.shoot();
        }
    }

    private void calculateToCreateMiniBoss() {
        counter ++;
        if(counter == Integer.MAX_VALUE) {
            counter = 0;
        }
        if(counter % 100 == 0) {
            boss.createMiniBoss();
        }
    }
}
