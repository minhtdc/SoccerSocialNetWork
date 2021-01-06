package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class Feed {
    String id;
    String STT;

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


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Feed(String id, String STT, String uid) {
        this.id = id;
        this.STT = STT;

        this.uid = uid;
    }
    public Feed() {

    }
}
