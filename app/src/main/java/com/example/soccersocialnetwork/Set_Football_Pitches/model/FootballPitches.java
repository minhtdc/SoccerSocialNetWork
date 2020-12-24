package com.example.soccersocialnetwork.Set_Football_Pitches.model;

public class FootballPitches {
    String tenSan, loaiHinhSan, LoaiSan, giaBT, giaCD, id;

    @Override
    public String toString() {
        return "FootballPitches{" +
                "tenSan='" + tenSan + '\'' +
                ", loaiHinhSan='" + loaiHinhSan + '\'' +
                ", LoaiSan='" + LoaiSan + '\'' +
                ", giaBT='" + giaBT + '\'' +
                ", giaCD='" + giaCD + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
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

    public String getGiaBT() {
        return giaBT;
    }

    public void setGiaBT(String giaBT) {
        this.giaBT = giaBT;
    }

    public String getGiaCD() {
        return giaCD;
    }

    public void setGiaCD(String giaCD) {
        this.giaCD = giaCD;
    }

    public FootballPitches() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FootballPitches(String tenSan, String loaiHinhSan, String loaiSan, String giaBT, String giaCD, String id) {
        this.tenSan = tenSan;
        this.loaiHinhSan = loaiHinhSan;
        LoaiSan = loaiSan;
        this.giaBT = giaBT;
        this.giaCD = giaCD;
        this.id = id;
    }
}
