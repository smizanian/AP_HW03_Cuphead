package Model;

public class CupheadModel {

    private int hitPoint;
    public static int score;

    public CupheadModel() {
        this.hitPoint = GameModel.hitPoint;
        score = 0;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void reduceHitPoint(int amount) {
        this.hitPoint -= (amount * GameModel.vulnerability);
    }
}
