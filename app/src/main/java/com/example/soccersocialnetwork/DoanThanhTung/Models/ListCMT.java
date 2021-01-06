package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class ListCMT {
    String uid;
    String cmt;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public ListCMT(String uid, String cmt) {
        this.uid = uid;
        this.cmt = cmt;
    }
    public ListCMT() {

    }
}
