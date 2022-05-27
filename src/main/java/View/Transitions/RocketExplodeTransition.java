package View.Transitions;

import View.Components.Plane;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;

public class RocketExplodeTransition extends Transition {

    private Plane plane;
    public static ArrayList<RocketExplodeTransition> rocketExplodeTransitions = new ArrayList<>();


    public RocketExplodeTransition() {
        plane = Plane.getInstance();
        this.setCycleDuration(Duration.millis(500));
        rocketExplodeTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int)(v * 11) + 1;
        plane.setFill(new ImagePattern(new Image("/pics/Game/Plane/Bomb/HitDust/" + frame +".png")));
    }


}
