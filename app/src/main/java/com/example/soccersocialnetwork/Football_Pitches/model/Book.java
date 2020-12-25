package com.example.soccersocialnetwork.Football_Pitches.model;

public class Book {
    String tenDoi, gioBatDau,gioKetThuc;

    public Book(String tenDoi, String gioBatDau, String gioKetThuc) {
        this.tenDoi = tenDoi;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    public String getTenDoi() {
        return tenDoi;
    }

    public void setTenDoi(String tenDoi) {
        this.tenDoi = tenDoi;
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
        return "Book{" +
                "tenDoi='" + tenDoi + '\'' +
                ", gioBatDau='" + gioBatDau + '\'' +
                ", gioKetThuc='" + gioKetThuc + '\'' +
                '}';
    }
}
