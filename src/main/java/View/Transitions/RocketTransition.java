package View.Transitions;


import View.Controller.GameFXMLController;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class RocketTransition extends Transition {

    private Rectangle rectangle;
    private final int rocketTime = 30;
    private long timeOfRocketUse;

    public static RocketTransition rocketTransition;

    public RocketTransition(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        timeOfRocketUse = System.currentTimeMillis()/1000 - rocketTime;
        rocketTransition = this;
    }

    @Override
    protected void interpolate(double v) {
        if(rectangle.getWidth() >= 150) {
            GameFXMLController.rocketValid = true;
            return;
        }
        long width = 150 * (System.currentTimeMillis()/1000 - timeOfRocketUse)/rocketTime;
        rectangle.setWidth(width);
    }

    public void unpause(long deltaT) {
        timeOfRocketUse += deltaT;
        this.play();
    }

    public void shootRocket() {
        timeOfRocketUse = System.currentTimeMillis()/1000;
        rectangle.setWidth(0);
    }
}
