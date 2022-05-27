package View.Transitions;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

public class BulletShootingTransition extends Transition {

    private Rectangle bulletShoot;
    public static ArrayList<BulletShootingTransition> bulletShootingTransitions = new ArrayList<>();


    public BulletShootingTransition(Rectangle rectangle) {
        this.bulletShoot = rectangle;
        this.setCycleDuration(Duration.millis(200));
        bulletShootingTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) (v * 3) + 1;
        bulletShoot.setFill(new ImagePattern( new Image("/pics/Game/Plane/CupheadShootTransition/" + frame +".png")));
    }
}
