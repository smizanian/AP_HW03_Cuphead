package Controller;

import Model.User;

import java.io.IOException;
import java.util.Arrays;

public class ProfileMenuController {

    public boolean isUserGuest(User user) {
        return user.isGuest();
    }

    public boolean isUsernameUnique(String username) {
        for (User user : User.users) {
            if(user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void changeUsername(String username, User loggedInUser) throws IOException {
        loggedInUser.setUsername(username);
        User.writeUsers("users.json");
    }

    public void changePassword(String password, User loggedInUser) throws IOException {
        loggedInUser.setPassword(password);
        User.writeUsers("users.json");
    }

}
