package View.Transitions;

import View.Components.Boss;
import javafx.animation.Transition;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class BossReduceHitPoint extends Transition {

    private Boss boss;
    private Rectangle rectangle;
    private Text text;
    public static ArrayList<BossReduceHitPoint> bossReduceHitPoints = new ArrayList<>();

    public BossReduceHitPoint(Boss boss, Rectangle rectangle, Text text) {
        this.boss = boss;
        this.rectangle = rectangle;
        this.text = text;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        bossReduceHitPoints.add(this);
    }

    @Override
    protected void interpolate(double v) {
        int hitPoint = boss.getBoss().getHitPoint();
        int width = (150 * hitPoint)/1000;
        rectangle.setWidth(width);
        text.setText("Boss hit point: " + hitPoint/10);

    }
}
