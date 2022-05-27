package View.Transitions;

import View.Components.Egg;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;

import java.util.ArrayList;

public class EggDestroyTransition extends Transition {

    private Egg egg;
    public static ArrayList<EggDestroyTransition> eggDestroyTransitions = new ArrayList<>();


    public EggDestroyTransition(Egg egg) {
        this.egg = egg;
        this.setCycleDuration(Duration.millis(500));
        eggDestroyTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int)(v * 5) + 1;
        egg.setFill(new ImagePattern(new Image("/pics/Game/BossAndMiniBoss/EggDestroy/" + frame + ".png")));
    }
}
