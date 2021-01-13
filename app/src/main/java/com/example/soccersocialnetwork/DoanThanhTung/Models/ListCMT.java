package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class ListCMT {
    String uid;
    String idCMT;
    String cmt;
    String thoiGian;



    public ListCMT(String uid, String idCMT, String cmt,String thoiGian) {
        this.uid = uid;
        this.idCMT = idCMT;
        this.cmt = cmt;
        this.thoiGian = thoiGian;
    }
    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
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
