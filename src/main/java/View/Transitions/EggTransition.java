package View.Transitions;

import View.Components.Egg;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class EggTransition extends Transition {

    private Egg egg;
    public static ArrayList<EggTransition> eggTransitions = new ArrayList<>();


    public EggTransition(Egg egg){
        this.egg = egg;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        eggTransitions.add(this);
    }

    @Override
    protected void interpolate(double v) {
        egg.setX(egg.getX() - 10);
        collisionCheck();
    }

    private void collisionCheck() {
        egg.checkCollision();
    }
}
