package Model;

public class BossModel {

    private int hitPoint;

    public int getHitPoint() {
        return hitPoint;
    }

    public BossModel() {
        this.hitPoint = 1000;
    }

    public void reduceHitPoint(int amount) {
        hitPoint -= (amount * GameModel.injury);
        CupheadModel.score += 5;
    }

}
