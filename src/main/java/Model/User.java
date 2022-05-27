package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class User {

    public static ArrayList<User> users = new ArrayList<User>();
    public static User loggedInUser = null;
    public static Random random = new Random();
    public static int numberOfAvatars = 3;

    private String username;
    private String password;
    private int scoreLevel1;
    private int scoreLevel2;
    private int scoreLevel3;
    private int scoreDevilMode;
    private String avatarURL;
    private boolean isGuest;
    private long minTime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.scoreLevel1 = 0;
        this.scoreLevel2 = 0;
        this.scoreLevel3 = 0;
        this.scoreDevilMode = 0;
        isGuest = username.equals("Guest");
        assignAvatar();
        this.minTime = Integer.MAX_VALUE;
    }

    public static void addUser(User newUser) {
        User.users.add(newUser);
    }

    public static User getUserByUsername(String username) {
        for (User user : User.users) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void readUsers(String fileName) throws IOException {
        Gson gson = new Gson();
        Path userPath = Paths.get(fileName);
        if (!userPath.toFile().isFile()) {
            return;
        }
        Reader reader = Files.newBufferedReader(userPath);
        ArrayList<?> jsonString = gson.fromJson(reader, ArrayList.class);
        reader.close();

        for (Object o : jsonString) {
            User tempUser = gson.fromJson(o.toString(), User.class);
            User.users.add(tempUser);
        }
    }

    public static void writeUsers(String fileName) throws IOException {
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        Path userPath = Paths.get(fileName);
        Writer writer = Files.newBufferedWriter(userPath);
        gsonBuilder.toJson(User.users, writer);
        writer.close();
    }

    private void assignAvatar() {
        int numberOfAvatar = random.nextInt(User.numberOfAvatars) + 1;
        avatarURL = convertAvatarURLToJsonForm(User.createAvatarURL(numberOfAvatar));
    }

    public static String createAvatarURL(int numberOfAvatar) {
        return "/pics/Avatars/avatar" + Integer.toString(numberOfAvatar) + ".png";
    }

    public static String convertAvatarURLToJsonForm(String avatarURL) {
        String[] splitURL = avatarURL.split("/");
        String finalURL = "";
        for (String s : splitURL) {
            if(s.equals("file:")) {
                finalURL += "file__";
            } else if(s.matches("[a-zA-Z]:")) {
                finalURL += s.charAt(0) + "__";
            } else {
                finalURL += s + "__";
            }
        }
        return finalURL;
    }

    public static String convertAvatarURLToCorrectForm(String avatarURL) {
        String[] splitURL = avatarURL.split("__");
        String finalURL = "";
        for (String s : splitURL) {
            if(s.equals("file")) {
                finalURL += "file:" + "/";
            } else if(s.matches("[a-zA-Z]")) {
                finalURL += s.charAt(0) + ":" + "/";
            } else {
                finalURL += s + "/";
            }
        }
        return finalURL;
    }

    public static void removeUser(String username) {
        int index = 0;
        for (User user : User.users) {
            if(user.getUsername().equals(username)) {
                User.users.remove(index);
                return;
            }
            index++;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setAvatarURL(String avatarURL) throws IOException {
        this.avatarURL = convertAvatarURLToJsonForm(avatarURL);
        User.writeUsers("users.json");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScoreLevel1() {
        return scoreLevel1;
    }

    public int getScoreLevel2() {
        return scoreLevel2;
    }

    public int getScoreLevel3() {
        return scoreLevel3;
    }

    public int getScoreDevilMode() {
        return scoreDevilMode;
    }

    public String getAvatarURL() {
        return convertAvatarURLToCorrectForm(this.avatarURL);
    }

    public boolean isGuest() {
        return this.isGuest;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAvatarDefault() {
        for (int i = 1; i <= numberOfAvatars ; i++) {
            String URL = User.createAvatarURL(i);
            if(this.avatarURL.equals(URL)) {
                return true;
            }
        }
        return false;
    }

    public void gameResult(int score, long gameTimeInSecond, int gameLevel) {
        switch (gameLevel) {
            case 1:
                scoreLevel1 += score;
                break;
            case 2:
                scoreLevel2 += score;
                break;
            case 3:
                scoreLevel3 += score;
                break;
            default:
                scoreDevilMode += score;
                break;
        }
        if(minTime > gameTimeInSecond) {
            minTime = gameTimeInSecond;
        }
    }

    public long getMinTime() {
        if(minTime == Integer.MAX_VALUE) {
            return 0;
        }
        return this.minTime;
    }
}
