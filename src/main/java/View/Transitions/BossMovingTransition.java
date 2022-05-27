package View.Transitions;

import Model.GameModel;
import View.Components.Boss;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class BossMovingTransition extends Transition {

    private Boss boss;
    private int speed = -10;
    public static ArrayList<BossMovingTransition> bossMovingTransitions = new ArrayList<>();

    public BossMovingTransition(Boss boss){
        this.boss = boss;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(5000));
        bossMovingTransitions.add(this);
    }

    @Override
    protected void interpolate(double frac) {
        if(GameModel.gameLevel != 4) {
            boss.changeY(150);
            return;
        }
        if(boss.getY() < 70) {
            speed = +10;
        } else if(boss.getY() + boss.getHeight() > 650) {
            speed = -10;
        }
        boss.changeY(boss.getY() + speed);
    }
}
