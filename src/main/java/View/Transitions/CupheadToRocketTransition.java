package View.Transitions;

import View.Components.Plane;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;

public class CupheadToRocketTransition extends Transition {

    private Plane cuphead;
    public static ArrayList<CupheadToRocketTransition> cupheadToRocketTransitions = new ArrayList<>();


    public CupheadToRocketTransition(Plane plane) {
        this.cuphead = plane;
        setCycleDuration(Duration.millis(1000));
        cupheadToRocketTransitions.add(this);
    }


    @Override
    protected void interpolate(double v) {
        int frame = (int)(v * 14) + 1;
        cuphead.setFill(new ImagePattern(new Image("/pics/Game/Plane/CupheadToRocketTransition/"+ frame + ".png")));
    }
}
