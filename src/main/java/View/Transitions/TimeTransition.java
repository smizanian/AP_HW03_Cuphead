package View.Transitions;

import Cuphead.CupheadFXML;
import Model.CupheadModel;
import View.Components.Plane;
import javafx.animation.Transition;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TimeTransition extends Transition {

    private Text text;
    private long time;
    private long firstTime;
    private long timeTemp;

    public TimeTransition(Text text) {
        this.text = text;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
        this.firstTime = System.currentTimeMillis();
    }

    @Override
    protected void interpolate(double frac) {
        time = System.currentTimeMillis() - firstTime;
        long second = time/1000 % 60;
        long minute = (time/1000 - second) / 60;
        String timeText = correctTime(second, minute);
        text.setText(timeText + "\nScore: " + CupheadModel.score);
    }

    private String correctTime(long second, long minute) {
        String secondString = Long.toString(second);
        if(second < 10) {
            secondString = "0" + secondString;
        }
        String minuteString = Long.toString(minute);
        if(minute < 10) {
            minuteString = "0" + minuteString;
        }
        return minuteString + ":" + secondString;
    }

    public long getGameTimeInSecond() {
        return (System.currentTimeMillis() - firstTime)/1000;
    }

    public void pauseTime() {
        timeTemp = System.currentTimeMillis();
        this.pause();
        RocketTransition.rocketTransition.pause();
    }

    public void unpauseTime() {
        long deltaT = System.currentTimeMillis() - timeTemp;
        firstTime += deltaT;
        this.play();
        RocketTransition.rocketTransition.unpause(deltaT/1000);
    }

    public String getTime() {
        return text.getText();
    }
}
