package com.example.soccersocialnetwork.football_field_owner.model;

public class FootballPitches {
    String tenSan, gia, loaiHinhSan, LoaiSan;

    public FootballPitches(String tenSan, String gia, String loaiHinhSan, String loaiSan) {
        this.tenSan = tenSan;
        this.gia = gia;
        this.loaiHinhSan = loaiHinhSan;
        LoaiSan = loaiSan;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getLoaiHinhSan() {
        return loaiHinhSan;
    }

    public void setLoaiHinhSan(String loaiHinhSan) {
        this.loaiHinhSan = loaiHinhSan;
    }

    public String getLoaiSan() {
        return LoaiSan;
    }

    public void setLoaiSan(String loaiSan) {
        LoaiSan = loaiSan;
    }

    @Override
    public String toString() {
        return "FootballPitches{" +
                "tenSan='" + tenSan + '\'' +
                ", gia='" + gia + '\'' +
                ", loaiHinhSan='" + loaiHinhSan + '\'' +
                ", LoaiSan='" + LoaiSan + '\'' +
                '}';
    }
}
