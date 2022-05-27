package View.Transitions;

import View.Components.Plane;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class CupheadTurnLeftTransition extends Transition {

    private Plane plane;
    public static ArrayList<CupheadTurnLeftTransition> cupheadTurnLeftTransitions = new ArrayList<>();


    public CupheadTurnLeftTransition() {
        this.plane = Plane.getInstance();
        setCycleDuration(Duration.millis(1000));
        cupheadTurnLeftTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int) Math.floor(v * 14) + 1;
        plane.setToLeftBackground(frame);
    }
}
