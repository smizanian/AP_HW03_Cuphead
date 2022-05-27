package View.Transitions;

import View.Components.MiniBoss;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;

public class MiniBossDeathTransition extends Transition {

    private MiniBoss miniBoss;
    public static ArrayList<MiniBossDeathTransition> miniBossDeathTransitions = new ArrayList<>();


    public MiniBossDeathTransition(MiniBoss miniBoss) {
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(500));
        miniBossDeathTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int)( v * 10) + 1;
        this.miniBoss.setFill(new ImagePattern( new Image("/pics/Game/BossAndMiniBoss/MiniBossDeath/" + frame + ".png")));
    }
}
