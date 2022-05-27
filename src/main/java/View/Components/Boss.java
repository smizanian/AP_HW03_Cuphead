package View.Components;

import Model.BossModel;
import View.Controller.GameFXMLController;
import View.Transitions.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Boss extends Rectangle {

    private BossFlyTransition bossFlyTransition;
    private GameFXMLController gameFXMLController;
    public Rectangle rectangleForVerticalCollision;
    public Rectangle rectangleForHorizontalCollision;
    private Pane pane;
    private BossModel boss;
    private Image eggImage = new Image("/pics/Game/BossAndMiniBoss/egg.png");
    private ImagePattern eggImagePattern = new ImagePattern(eggImage);


    public Boss(GameFXMLController gameFXMLController) {
        super(780, 230, 500, 400);
        this.boss = new Model.BossModel();
        this.rectangleForVerticalCollision = new Rectangle(900, this.getY(), 80, 400);
        this.rectangleForHorizontalCollision = new Rectangle(780, this.getY() + this.getHeight()/2, 500, 80);
        this.gameFXMLController = gameFXMLController;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void fly() {
        bossFlyTransition = new BossFlyTransition(this, this.gameFXMLController);
        bossFlyTransition.play();
        BossMovingTransition bossMovingTransition = new BossMovingTransition(this);
        bossMovingTransition.play();
    }

    public void shoot() {
        bossFlyTransition.pause();
        BossShootTransition bossShootTransition = new BossShootTransition(this);
        bossShootTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                bossFlyTransition.play();
            }
        });
        bossShootTransition.play();
    }

    public void createEgg() {
        Egg egg = new Egg(this.getY() + 200);
        egg.setPane(pane);
        egg.setFill(eggImagePattern);
        EggTransition eggTransition = new EggTransition(egg);
        egg.eggTransition = eggTransition;
        eggTransition.play();
        pane.getChildren().add(egg);
    }

    public void reduceHitPoint(int amount) {
        boss.reduceHitPoint(amount);
    }

    public boolean hasCollision(Rectangle rectangle) {
        return rectangle.getBoundsInParent().intersects(this.getLayoutBounds());
    }

    public void checkCollision() {
        for (Node child : pane.getChildren()) {
            if(child instanceof Bullet) {
                if(((Bullet) child).hasCollision(rectangleForVerticalCollision)
                    || ((Bullet) child).hasCollision(rectangleForHorizontalCollision)) {
                    ((Bullet) child).setX(((Bullet) child).getX() + 30);
                    pane.getChildren().remove(child);
                    boss.reduceHitPoint(1);
                    break;
                }
            } else if(child instanceof Bomb) {
                if(((Bomb) child).hasCollision(rectangleForVerticalCollision)
                     || ((Bomb) child).hasCollision(rectangleForHorizontalCollision)) {
                    ((Bomb) child).setY(((Bomb) child).getY() + 30);
                    pane.getChildren().remove(child);
                    boss.reduceHitPoint(2);
                    break;
                }
            } else if(child instanceof Plane && !((Plane) child).isRocket) {
                if(((Plane) child).hasCollision(rectangleForVerticalCollision)
                    || ((Plane) child).hasCollision(rectangleForHorizontalCollision)){
                    if(!((Plane) child).collisionCheck) {
                        Plane.reduceHitPoint(10);
                        ((Plane) child).collisionCheck = true;
                    }
                } else {
                    ((Plane) child).collisionCheck = false;
                }
            }
        }
    }

    public BossModel getBoss() {
        return boss;
    }

    public void changeY(double v) {
        this.setY(v);
        this.rectangleForHorizontalCollision.setY(this.getY() + this.getHeight()/2);
        this.rectangleForVerticalCollision.setY(this.getY());
    }

    public void createMiniBoss() {
        MiniBoss miniBoss1 = new MiniBoss(1250, 30);
        MiniBoss miniBoss2 = new MiniBoss(1250, 630);
        MiniBoss.setPane(pane);
        MiniBossFlyTransition miniBossFlyTransition1 = new MiniBossFlyTransition(miniBoss1);
        miniBoss1.miniBossFlyTransition = miniBossFlyTransition1;
        MiniBossFlyTransition miniBossFlyTransition2 = new MiniBossFlyTransition(miniBoss2);
        miniBoss2.miniBossFlyTransition = miniBossFlyTransition2;
        miniBossFlyTransition1.play();
        miniBossFlyTransition2.play();
        pane.getChildren().add(miniBoss1);
        pane.getChildren().add(miniBoss2);
    }
}
