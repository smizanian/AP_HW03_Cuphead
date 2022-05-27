package View.Components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {

    public Bullet(double x, double y) {
        super(x, y, 50, 20);
        this.setFill(new ImagePattern( new Image("/pics/Game/Plane/bullet.png")));
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
