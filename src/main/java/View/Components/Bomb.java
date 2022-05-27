package View.Components;

import Model.GameModel;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bomb extends Rectangle {

    public Bomb (double x, double y) {
        super(x, y, 20, 50);
        Image image = new Image("/pics/Game/Plane/bomb.png");
        this.setFill(new ImagePattern(image));
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(this.getLayoutBounds());
    }
}
