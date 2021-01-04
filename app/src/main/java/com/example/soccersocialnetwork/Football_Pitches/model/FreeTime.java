package com.example.soccersocialnetwork.Football_Pitches.model;

public class FreeTime {
    String gioBatDau, gioKetThuc;

    public FreeTime(String gioBatDau, String gioKetThuc) {
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    @Override
    public String toString() {
        return "FreeTime{" +
                "gioBatDau='" + gioBatDau + '\'' +
                ", gioKetThuc='" + gioKetThuc + '\'' +
                '}';
    }
}
