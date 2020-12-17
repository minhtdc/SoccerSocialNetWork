package com.example.soccersocialnetwork.Set_Football_Pitches.model;

public class SetFootballPitches {
    String idDoiDat, idNguoiDat, idSanDat, gioBatDau, gioKetThuc;
    int tongTien;

    public SetFootballPitches() {
    }

    public SetFootballPitches(String idDoiDat, String idNguoiDat, String idSanDat, String gioBatDau, String gioKetThuc, int tongTien) {
        this.idDoiDat = idDoiDat;
        this.idNguoiDat = idNguoiDat;
        this.idSanDat = idSanDat;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.tongTien = tongTien;
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

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "SetFootballPitches{" +
                "idDoiDat='" + idDoiDat + '\'' +
                ", idNguoiDat='" + idNguoiDat + '\'' +
                ", idSanDat='" + idSanDat + '\'' +
                ", gioBatDau='" + gioBatDau + '\'' +
                ", gioKetThuc='" + gioKetThuc + '\'' +
                ", tongTien=" + tongTien +
                '}';
    }
}
