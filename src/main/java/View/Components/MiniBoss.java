package View.Components;

import Model.CupheadModel;
import Model.MiniBossModel;
import View.Transitions.MiniBossDeathTransition;
import View.Transitions.MiniBossFlyTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class MiniBoss extends Rectangle {

    public static Pane pane;
    private MiniBossModel miniBossModel;
    public MiniBossFlyTransition miniBossFlyTransition;

    public MiniBoss(int x, int y) {
        super(x, y, 70, 50);
        this.setFill(new ImagePattern( new Image("/pics/Game/BossAndMiniBoss/MiniBossFly/1.png")));
        this.miniBossModel = new MiniBossModel();
    }

    public void checkCollision() {
        for (Node child : pane.getChildren()) {
            if(child instanceof Bullet) {
                if(((Bullet) child).hasCollision(this)){
                    ((Bullet) child).setX(((Bullet) child).getX() + 30);
                    miniBossModel.reduceHitPoint(1);
                    pane.getChildren().remove(child);
                    if(miniBossModel.getHitPoint() == 0){
                        death();
                    }
                    break;
                }
            } else if(child instanceof Bomb) {
                if(((Bomb) child).hasCollision(this)) {
                    ((Bomb) child).setY(((Bomb) child).getY() + 30);
                    miniBossModel.reduceHitPoint(2);
                    pane.getChildren().remove(child);
                    death();
                    break;
                }
            } else if(child instanceof Plane) {
                if(((Plane) child).hasCollision(this)){
                    Plane.reduceHitPoint(3);
                    death();
                }
            }
        }
    }

    public void death() {
        miniBossFlyTransition.pause();
        CupheadModel.score += 2;
        MiniBossDeathTransition miniBossDeathTransition = new MiniBossDeathTransition(this);
        miniBossDeathTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(MiniBoss.this);
            }
        });
        miniBossDeathTransition.play();
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public static void setPane(Pane pane) {
        MiniBoss.pane = pane;
    }
}
