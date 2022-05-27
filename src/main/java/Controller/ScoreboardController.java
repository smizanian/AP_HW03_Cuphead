package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreboardController {

    public void sortUsersInTotal(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(o -> o.getScoreLevel1() + o.getScoreLevel2() + o.getScoreLevel3() + o.getScoreDevilMode()));
        Collections.reverse(users);
    }

    public void sortUsersInLevel1(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(User::getScoreLevel1));
        Collections.reverse(users);
    }

    public void sortUsersInLevel2(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(User::getScoreLevel2));
        Collections.reverse(users);
    }

    public void sortUsersInLevel3(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(User::getScoreLevel3));
        Collections.reverse(users);
    }

    public void sortUsersInDevilMode(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(User::getScoreDevilMode));
        Collections.reverse(users);
    }

}
