package com.example.soccersocialnetwork.Set_Football_Pitches.model;

public class SetFootballPitches {
    String idDoiDat, idNguoiDat, idSanDat;
    int gioBatDau, gioKetThuc, phutBatDau, phutKetThuc;
    String tongTien, ngayDat;

    public SetFootballPitches() {
    }

    public SetFootballPitches(String idDoiDat, String idNguoiDat, String idSanDat, int gioBatDau, int gioKetThuc, int phutBatDau, int phutKetThuc, String tongTien, String ngayDat) {
        this.idDoiDat = idDoiDat;
        this.idNguoiDat = idNguoiDat;
        this.idSanDat = idSanDat;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.phutBatDau = phutBatDau;
        this.phutKetThuc = phutKetThuc;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getIdDoiDat() {
        return idDoiDat;
    }

    public void setIdDoiDat(String idDoiDat) {
        this.idDoiDat = idDoiDat;
    }

    public String getIdNguoiDat() {
        return idNguoiDat;
    }

    public void setIdNguoiDat(String idNguoiDat) {
        this.idNguoiDat = idNguoiDat;
    }

    public String getIdSanDat() {
        return idSanDat;
    }

    public void setIdSanDat(String idSanDat) {
        this.idSanDat = idSanDat;
    }

    public int getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(int gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public int getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(int gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public int getPhutBatDau() {
        return phutBatDau;
    }

    public void setPhutBatDau(int phutBatDau) {
        this.phutBatDau = phutBatDau;
    }

    public int getPhutKetThuc() {
        return phutKetThuc;
    }

    public void setPhutKetThuc(int phutKetThuc) {
        this.phutKetThuc = phutKetThuc;
    }

    public String getTongTien() {
        return tongTien;
    }

    public void setTongTien(String tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "SetFootballPitches{" +
                "idDoiDat='" + idDoiDat + '\'' +
                ", idNguoiDat='" + idNguoiDat + '\'' +
                ", idSanDat='" + idSanDat + '\'' +
                ", gioBatDau=" + gioBatDau +
                ", gioKetThuc=" + gioKetThuc +
                ", phutBatDau=" + phutBatDau +
                ", phutKetThuc=" + phutKetThuc +
                ", tongTien='" + tongTien + '\'' +
                ", ngayDat='" + ngayDat + '\'' +
                '}';
    }
}
