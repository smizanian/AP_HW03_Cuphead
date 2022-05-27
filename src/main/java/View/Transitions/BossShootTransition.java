package View.Transitions;

import View.Components.Boss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;

public class BossShootTransition extends Transition {

    private Boss boss;
    private boolean shoot;
    public static ArrayList<BossShootTransition> bossShootTransitions = new ArrayList<>();

    public BossShootTransition(Boss boss) {
        this.boss = boss;
        this.shoot = true;
        this.setCycleDuration(Duration.millis(500));
        bossShootTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int)(v * 11) + 1;
        if(frame == 1) {
            shoot = true;
        }
        if(frame == 4) {
            if(shoot) {
                boss.createEgg();
                shoot = false;
            }
        }
        boss.setFill(new ImagePattern(new Image("/pics/Game/BossAndMiniBoss/BossShoot/" + frame + ".png")));
        collisionCheck();
    }

    private void collisionCheck() {
        boss.checkCollision();
    }
}
