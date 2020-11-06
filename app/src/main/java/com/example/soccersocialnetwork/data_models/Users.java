package com.example.soccersocialnetwork.data_models;

public class Users {
    private String userEmail;
    private String userName;
    private String userBirth;
    private String userAria;

    public Users() {
        this.userEmail = "";
        this.userName = "";
        this.userBirth = "";
        this.userAria = "userAria";
    }

    public Users(String userEmail, String userName, String userBirth, String userAria) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userAria = userAria;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserAria() {
        return userAria;
    }

    public void setUserAria(String userAria) {
        this.userAria = userAria;
    }
}
