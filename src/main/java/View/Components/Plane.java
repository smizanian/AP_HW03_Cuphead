package View.Components;

import Model.CupheadModel;
import View.Transitions.CupheadTurnLeftTransition;
import View.Transitions.CupheadTurnRightTransition;
import View.Transitions.RocketExplodeTransition;
import View.Transitions.RocketMoveTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle {

    private static Plane instance;
    private static CupheadModel cupheadModel;
    private boolean toRight;
    public boolean isRocket;
    public boolean collisionCheck;
    public RocketMoveTransition rocketMoveTransition;

    public static Plane getInstance() {
        if(instance == null) {
            instance = new Plane();
            cupheadModel = new CupheadModel();
        }
        return instance;
    }


    private Plane() {
        super(200, 320, 100, 70);
        toRight = true;
        isRocket = false;
        collisionCheck = false;
    }

    public static void nullInstance() {
        instance = null;
    }

    public void setBackground(int frame) {
        String address = "/pics/Game/Plane/" + frame + ".png";
        Image image = new Image(address);
        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
    }

    public void setToLeftBackground(int frame) {
        String address = "/pics/Game/Plane/TurnLeftTransition/" + frame + ".png";
        Image image = new Image(address);
        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
    }

    public void setToRightBackground(int frame) {
        String address = "/pics/Game/Plane/TurnRightTransition/" + frame + ".png";
        Image image = new Image(address);
        ImagePattern imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
    }

    public void moveRight() {
        if (!hitRightWall()) {
            this.setX(this.getX() + 10);
            if(!toRight) {
                toRightAnimation();
            }
            toRight = true;
        }
    }

    public void moveLeft() {
        if (!hitLeftWall()) {
            this.setX(this.getX() - 10);
            if(toRight) {
                toLeftAnimation();
            }
            toRight = false;
        }
    }

    private void toLeftAnimation() {
        CupheadTurnLeftTransition cupheadTurnLeftTransition = new CupheadTurnLeftTransition();
        cupheadTurnLeftTransition.play();
        setBackground(1);
    }

    private void toRightAnimation() {
        CupheadTurnRightTransition cupheadTurnRightTransition = new CupheadTurnRightTransition();
        cupheadTurnRightTransition.play();
        setBackground(0);
    }

    public void moveUp() {
        if (!hitTop()) {
            this.setY(this.getY() - 10);
        }
    }

    public void moveDown() {
        if (!hitFloor()) {
            this.setY(this.getY() + 10);
        }
    }

    public boolean hitRightWall() {
        return this.getX() + this.getWidth() >= 1280;
    }

    public boolean hitLeftWall() {
        return this.getX() <= 0;
    }

    public boolean hitTop() {
        return this.getY() <= 0;
    }

    public boolean hitFloor() {
        return this.getY() + this.getHeight() >= 720;
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public static CupheadModel getCupheadModel() {
        return cupheadModel;
    }

    public static void reduceHitPoint(int amount) {
        cupheadModel.reduceHitPoint(amount);
    }

    public void explodeRocket() {
        Plane.this.rocketMoveTransition.stop();
        RocketExplodeTransition rocketExplodeTransition = new RocketExplodeTransition();
        rocketExplodeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Plane.this.setFill(new ImagePattern(new Image("/pics/Game/Plane/0.png")));
                Plane.this.isRocket = false;
                Plane.this.setX(200);
                Plane.this.setY(320);
            }
        });
        rocketExplodeTransition.play();
    }

    public void moveRocket(Pane pane) {
        rocketMoveTransition = new RocketMoveTransition(pane);
        rocketMoveTransition.play();
    }

}
