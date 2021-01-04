package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class Feed {
    String id;
    String STT;
    String nameUser;
    String imgUser;
    String uid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSTT() {
        return STT;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getImgUser() {
        return imgUser;
    }

    public void setImgUser(String imgUser) {
        this.imgUser = imgUser;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Feed(String id, String STT, String nameUser, String imgUser, String uid) {
        this.id = id;
        this.STT = STT;
        this.nameUser = nameUser;
        this.imgUser = imgUser;
        this.uid = uid;
    }
    public Feed() {

    }
}
