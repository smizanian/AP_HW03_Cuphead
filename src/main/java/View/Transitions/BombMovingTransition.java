package View.Transitions;

import View.Components.Bomb;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.ArrayList;

public class BombMovingTransition extends Transition {

    private Bomb bomb;
    public static ArrayList<BombMovingTransition> bombMovingTransitions = new ArrayList<>();

    public BombMovingTransition(Bomb bomb) {
        this.bomb = bomb;
        setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        bombMovingTransitions.add(this);
    }
    @Override
    protected void interpolate(double v) {
        bomb.setY(bomb.getY() + 10);
    }
}
