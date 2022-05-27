package View.Transitions;

import Model.GameModel;
import View.Components.Boss;
import View.Components.Plane;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class CupheadReduceHitPointTransition extends Transition {

    private Rectangle rectangle;
    private Text text;
    public static ArrayList<CupheadReduceHitPointTransition> cupheadReduceHitPointTransitions = new ArrayList<>();


    public CupheadReduceHitPointTransition( Rectangle rectangle, Text text) {
        this.rectangle = rectangle;
        this.text = text;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        cupheadReduceHitPointTransitions.add(this);
    }

    public static void setZero() {
        for (CupheadReduceHitPointTransition transition : cupheadReduceHitPointTransitions) {
            transition.text.setText("Cuphead hit point: 0");
        }
    }

    @Override
    protected void interpolate(double v) {
        int hitPoint = Plane.getCupheadModel().getHitPoint();
        int width = (150 * hitPoint)/GameModel.hitPoint;
        rectangle.setWidth(width);
        text.setText("Cuphead hit point: " + hitPoint * 100 / GameModel.hitPoint);
        if(hitPoint < GameModel.hitPoint / 5) {
            rectangle.setFill(new ImagePattern(new Image("/pics/Game/BossAndMiniBoss/BossHitPoint.jpg")));
        }
    }
}
