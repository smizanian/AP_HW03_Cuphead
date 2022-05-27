package View.Transitions;

import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;


public class BackgroundTransition extends Transition {

    private Rectangle background;
    public static ArrayList<BackgroundTransition> backgroundTransitions = new ArrayList<>();

    public BackgroundTransition(Rectangle background) {
        this.background = background;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        backgroundTransitions.add(this);
    }

    @Override
    protected void interpolate(double frac) {
        int min = 1280 - 5000;
        if(background.getX() > min) {
            background.setX(background.getX() - 30);
        } else {
            background.setX(0);
        }
    }
}
