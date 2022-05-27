package View.Transitions;

import View.Components.Plane;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class CupheadTurnRightTransition extends Transition {

    private Plane plane;
    public static ArrayList<CupheadTurnRightTransition> cupheadTurnRightTransitions = new ArrayList<>();

    public CupheadTurnRightTransition() {
        this.plane = Plane.getInstance();
        setCycleDuration(Duration.millis(1000));
        cupheadTurnRightTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 12) + 1;
        plane.setToRightBackground(frame);
    }
}
