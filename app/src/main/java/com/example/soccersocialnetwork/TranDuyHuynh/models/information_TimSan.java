package com.example.soccersocialnetwork.TranDuyHuynh.models;

public class information_TimSan {
    String tenKhu, tenSan, ngayDat, gioDat, idDat, anh;

    public information_TimSan(String tenKhu, String tenSan, String ngayDat, String gioDat, String idDat, String anh) {
        this.tenKhu = tenKhu;
        this.tenSan = tenSan;
        this.ngayDat = ngayDat;
        this.gioDat = gioDat;
        this.idDat = idDat;
        this.anh = anh;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public information_TimSan() {
    }

    public String getTenKhu() {
        return tenKhu;
    }

    public void setTenKhu(String tenKhu) {
        this.tenKhu = tenKhu;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public String getGioDat() {
        return gioDat;
    }

    public void setGioDat(String gioDat) {
        this.gioDat = gioDat;
    }

    public String getIdDat() {
        return idDat;
    }

    public void setIdDat(String idDat) {
        this.idDat = idDat;
    }

    @Override
    public String toString() {
        return "information_TimSan{" +
                "tenKhu='" + tenKhu + '\'' +
                ", tenSan='" + tenSan + '\'' +
                ", ngayDat='" + ngayDat + '\'' +
                ", gioDat='" + gioDat + '\'' +
                ", idDat='" + idDat + '\'' +
                ", anh='" + anh + '\'' +
                '}';
    }
}

