package com.example.soccersocialnetwork.Football_Pitches.model;

public class Book {
    String tenDoi, gioBatDau, gioKetThuc, anhDoi, idDaDat;

    public Book(String tenDoi, String gioBatDau, String gioKetThuc, String anhDoi, String idDaDat) {
        this.tenDoi = tenDoi;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.anhDoi = anhDoi;
        this.idDaDat = idDaDat;
    }

    public Book() {
    }

    public String getIdDaDat() {
        return idDaDat;
    }

    public void setIdDaDat(String idDaDat) {
        this.idDaDat = idDaDat;
    }

    public String getAnhDoi() {
        return anhDoi;
    }

    public void setAnhDoi(String anhDoi) {
        this.anhDoi = anhDoi;
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
                ", anhDoi='" + anhDoi + '\'' +
                ", idDaDat='" + idDaDat + '\'' +
                '}';
    }
}
