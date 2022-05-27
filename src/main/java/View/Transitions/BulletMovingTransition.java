package View.Transitions;

import View.Components.Bullet;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletMovingTransition extends Transition {

    private Bullet bullet;
    public static ArrayList<BulletMovingTransition> bulletMovingTransitions = new ArrayList<>();

    public BulletMovingTransition(Bullet bullet) {
        this.bullet = bullet;
        setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        bulletMovingTransitions.add(this);
    }
    @Override
    protected void interpolate(double v) {
        bullet.setX(bullet.getX() + 10);
    }
}
