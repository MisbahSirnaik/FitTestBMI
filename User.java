package com.example.fittestbmi;

public class User {

    String userId;
    String userDate;
    String userBmi;
    String userCat;

    public User()
    {

    }

    public User(String userId, String userDate, String userBmi, String userCat) {
        this.userId = userId;
        this.userDate = userDate;
        this.userBmi = userBmi;
        this.userCat = userCat;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserDate() {
        return userDate;
    }

    public String getUserBmi() {
        return userBmi;
    }

    public String getUserCat() {
        return userCat;
    }
}
