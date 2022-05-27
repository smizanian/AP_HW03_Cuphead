package View.Transitions;

import View.Components.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBossFlyTransition extends Transition {

    private MiniBoss miniBoss;
    public static ArrayList<MiniBossFlyTransition> miniBossFlyTransitions = new ArrayList<>();


    public MiniBossFlyTransition(MiniBoss miniBoss){
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        miniBossFlyTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int)(v * 15);
        miniBoss.setFill(new ImagePattern(TransitionImages.miniBossFlyTransition[frame]));
        miniBoss.setX(miniBoss.getX() - 10);
        miniBoss.checkCollision();
    }
}
