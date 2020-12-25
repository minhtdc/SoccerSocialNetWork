package com.example.soccersocialnetwork.Set_Football_Pitches.model;

public class FreeTime {
    int gioBD, phutBD, gioKT, phutKT;

    public FreeTime() {
    }

    public FreeTime(int gioBD, int phutBD, int gioKT, int phutKT) {
        this.gioBD = gioBD;
        this.phutBD = phutBD;
        this.gioKT = gioKT;
        this.phutKT = phutKT;
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

    @Override
    public String toString() {
        return gioBD + ":" + phutBD + " - " +
                gioKT + ":" + phutKT;
    }


}
