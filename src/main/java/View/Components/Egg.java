package View.Components;

import View.Transitions.EggDestroyTransition;
import View.Transitions.EggTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Egg extends Rectangle {

    private Pane pane;
    private boolean collision;
    public EggTransition eggTransition;

    public Egg(double y) {
        super(780, y, 50, 50);
    }

    public void setPane(Pane pane) {
        this.pane = pane;
        collision = true;
    }

    public void checkCollision() {
        for (Node child : pane.getChildren()) {
            if(child instanceof Plane) {
                if(((Plane) child).hasCollision(this)) {
                    if(collision) {
                        Plane.reduceHitPoint(1);
                        collision = false;
                    }
                    destroy();
                    break;
                }
            }
        }
    }

    public void destroy() {
        eggTransition.pause();
        EggDestroyTransition eggDestroyTransition = new EggDestroyTransition(this);
        eggDestroyTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().remove(Egg.this);
            }
        });
        eggDestroyTransition.play();
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(this.getLayoutBounds());
    }


}
