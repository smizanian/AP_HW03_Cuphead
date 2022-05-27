package View.Transitions;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class VictoryTransition extends Transition {

    private Rectangle rectangle;

    public VictoryTransition(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.setCycleDuration(Duration.millis(2500));
    }

    @Override
    protected void interpolate(double v) {
        int frame = (int)(v * 26) + 1;
        rectangle.setFill(new ImagePattern(new Image("/pics/Game/Victory/" + frame +".png")));
    }
}
