package com.example.soccersocialnetwork.football_field_owner.model;

public class RushHour {
    int gioBD, phutBD, gioKT, phutKT;
    String id;

    public RushHour() {
    }

    public RushHour(int gioBD, int phutBD, int gioKT, int phutKT, String id) {
        this.gioBD = gioBD;
        this.phutBD = phutBD;
        this.gioKT = gioKT;
        this.phutKT = phutKT;
        this.id = id;
    }

    public int getGioBD() {
        return gioBD;
    }

    public void setGioBD(int gioBD) {
        this.gioBD = gioBD;
    }

    public int getPhutBD() {
        return phutBD;
    }

    public void setPhutBD(int phutBD) {
        this.phutBD = phutBD;
    }

    public int getGioKT() {
        return gioKT;
    }

    public void setGioKT(int gioKT) {
        this.gioKT = gioKT;
    }

    public int getPhutKT() {
        return phutKT;
    }

    public void setPhutKT(int phutKT) {
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
        return gioBD + ":" + phutBD + " - " +
                gioKT + ":" + phutKT;
    }
}
