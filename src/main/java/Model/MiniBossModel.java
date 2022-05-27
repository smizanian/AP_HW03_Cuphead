package Model;

public class MiniBossModel {

    private int hitPoint;

    public MiniBossModel() {
        this.hitPoint = 2;
    }

    public void reduceHitPoint(int amount){
        this.hitPoint -= amount;
    }

    public int getHitPoint() {
        return hitPoint;
    }
}
