package View.Transitions;

import View.Components.Boss;
import View.Components.Egg;
import View.Components.MiniBoss;
import View.Components.Plane;
import View.Controller.GameFXMLController;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class RocketMoveTransition extends Transition {

    private Plane plane;
    private Pane pane;
    public static ArrayList<RocketMoveTransition> rocketMoveTransitions = new ArrayList<>();


    public RocketMoveTransition(Pane pane) {
        plane = Plane.getInstance();
        this.pane = pane;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        rocketMoveTransitions.add(this);
    }

    @Override
    protected void interpolate(double frac) {
        plane.setX(plane.getX() + 10);
        collisionCheck();
    }

    private void collisionCheck() {
        for (Node child : pane.getChildren()) {
            if(child instanceof Egg) {
                if(((Egg) child).hasCollision(plane)) {
                    plane.explodeRocket();
                    ((Egg) child).destroy();
                    break;
                }
            } else if(child instanceof MiniBoss) {
                if(((MiniBoss) child).hasCollision(plane)) {
                    ((MiniBoss) child).death();
                    plane.explodeRocket();
                    break;
                }
            } else if(child instanceof Boss) {
                if(((Boss) child).hasCollision(plane)) {
                    ((Boss) child).reduceHitPoint(4);
                    plane.explodeRocket();
                    break;
                }
//            } else if(plane.hasCollision(GameFXMLController.getBackground())) {
//                plane.explodeRocket();
//                break;
            }
        }
    }


}
