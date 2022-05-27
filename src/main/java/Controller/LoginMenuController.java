package Controller;

import Cuphead.CupheadFXML;
import Model.User;

public class LoginMenuController {

    public String getGuestUsername() {
        return "Guest";
    }

    public String getGuestPassword() {
        return "123456";
    }

    public User getGuestAccount() {
        for (User user : User.users) {
            if(user.isGuest()) {
                return user;
            }
        }
        return null;
    }

    public boolean isUserLoggedInWithGuestAccount(String username, String password) {
        return username.equals(getGuestUsername()) && password.equals(getGuestPassword());
    }

    public boolean userExists(String username, String password) {
        for (User user : User.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void login(String username, String password) {
        for (User user : User.users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                User.loggedInUser = user;
                CupheadFXML.changeMenu("MainMenuGraphics");
                return;
            }
        }
    }

    public boolean isUsernameUnique(String username) {
        for (User user : User.users) {
            if(user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public void register(String username, String password) {
        User newUser = new User(username, password);
        User.addUser(newUser);
    }

    public void loginWithGuestAccount() {
        User.loggedInUser = getGuestAccount();
        CupheadFXML.changeMenu("MainMenuGraphics");
    }
}
