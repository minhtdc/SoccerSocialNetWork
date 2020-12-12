package com.example.soccersocialnetwork.football_field_owner.model;

public class RushHour {
    String gioBD;
    String phutBD;
    String gioKT;
    String phutKT;
    String id;

    public RushHour() {
    }

    public RushHour(String gioBD, String phutBD, String gioKT, String phutKT, String id) {
        this.gioBD = gioBD;
        this.phutBD = phutBD;
        this.gioKT = gioKT;
        this.phutKT = phutKT;
        this.id = id;
    }

    public String getGioBD() {
        return gioBD;
    }

    public void setGioBD(String gioBD) {
        this.gioBD = gioBD;
    }

    public String getPhutBD() {
        return phutBD;
    }

    public void setPhutBD(String phutBD) {
        this.phutBD = phutBD;
    }

    public String getGioKT() {
        return gioKT;
    }

    public void setGioKT(String gioKT) {
        this.gioKT = gioKT;
    }

    public String getPhutKT() {
        return phutKT;
    }

    public void setPhutKT(String phutKT) {
        this.phutKT = phutKT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return gioBD + ':' + phutBD + " - " +
                gioKT + ':' + phutKT;
    }
}
