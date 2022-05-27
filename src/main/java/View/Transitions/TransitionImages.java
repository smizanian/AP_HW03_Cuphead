package View.Transitions;

import javafx.scene.image.Image;

public class TransitionImages {

    public static Image[] bossFlyImages = new Image[6];
    public static Image[] miniBossFlyTransition = new Image[16];

    public static void start() {
        for (int i = 0; i < 6; i++) {
            bossFlyImages[i] = new Image("/pics/Game/BossAndMiniBoss/BossFly/" + (i + 1) + ".png");
        }
        for (int i = 0; i < 16; i++) {
            miniBossFlyTransition[i] = new Image("/pics/Game/BossAndMiniBoss/MiniBossFly/" + (i + 1) +".png");
        }
    }
}
