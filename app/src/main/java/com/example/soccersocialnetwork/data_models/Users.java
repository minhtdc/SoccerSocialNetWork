package com.example.soccersocialnetwork.data_models;

public class Users {
    private String userID;

    @Override
    public String toString() {
        return "Users{" +
                "userID='" + userID + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userAria='" + userAria + '\'' +
                '}';
    }

    private String userEmail;
    private String userName;
    private String userBirth;
    private String userAria;
    private String userSologan;
    private String userQueQuan;
    private String userViTri;
    private String userCanNang;
    private String userChieuCao;
    private String userImage;

    public Users() {
        this.userEmail = "";
        this.userName = "";
        this.userBirth = "";
        this.userAria = "Khu Vuc";
        this.userSologan = "";
        this.userQueQuan = "";
        this.userViTri = "";
        this.userCanNang = "";
        this.userChieuCao = "";
        this.userImage = "";
    }

    public Users(String userID, String userEmail, String userName, String userBirth, String userAria, String userSologan, String userQueQuan, String userViTri, String userCanNang, String userChieuCao, String userImage) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userAria = userAria;
        this.userSologan = userSologan;
        this.userQueQuan = userQueQuan;
        this.userViTri = userViTri;
        this.userCanNang = userCanNang;
        this.userChieuCao = userChieuCao;
        this.userImage = userImage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getUserSologan() {
        return userSologan;
    }

    public void setUserSologan(String userSologan) {
        this.userSologan = userSologan;
    }

    public String getUserQueQuan() {
        return userQueQuan;
    }

    public void setUserQueQuan(String userQueQuan) {
        this.userQueQuan = userQueQuan;
    }

    public String getUserViTri() {
        return userViTri;
    }

    public void setUserViTri(String userViTri) {
        this.userViTri = userViTri;
    }

    public String getUserCanNang() {
        return userCanNang;
    }

    public void setUserCanNang(String userCanNang) {
        this.userCanNang = userCanNang;
    }

    public String getUserChieuCao() {
        return userChieuCao;
    }

    public void setUserChieuCao(String userChieuCao) {
        this.userChieuCao = userChieuCao;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
