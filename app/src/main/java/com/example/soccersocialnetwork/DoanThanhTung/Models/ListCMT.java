package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class ListCMT {
    String uid;
    String idCMT;
    String cmt;

    public ListCMT(String uid, String idCMT, String cmt) {
        this.uid = uid;
        this.idCMT = idCMT;
        this.cmt = cmt;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdCMT() {
        return idCMT;
    }

    public void setIdCMT(String idCMT) {
        this.idCMT = idCMT;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public ListCMT() {

    }
}
