package com.example.soccersocialnetwork.football_field_owner.model;

import java.util.ArrayList;

public class FootballPitches {
    String tenSan, loaiHinhSan, LoaiSan, giaBT, giaCD, id, idKhu;

    public String getIdKhu() {
        return idKhu;
    }

    public void setIdKhu(String idKhu) {
        this.idKhu = idKhu;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FootballPitches(String tenSan, String loaiHinhSan, String loaiSan, String giaBT, String giaCD, String id, String idKhu) {
        this.tenSan = tenSan;
        this.loaiHinhSan = loaiHinhSan;
        LoaiSan = loaiSan;
        this.giaBT = giaBT;
        this.giaCD = giaCD;
        this.id = id;
        this.idKhu = idKhu;
    }

    public FootballPitches() {
    }

    @Override
    public String toString() {
        return "FootballPitches{" +
                "tenSan='" + tenSan + '\'' +
                ", loaiHinhSan='" + loaiHinhSan + '\'' +
                ", LoaiSan='" + LoaiSan + '\'' +
                ", giaBT='" + giaBT + '\'' +
                ", giaCD='" + giaCD + '\'' +
                ", id='" + id + '\'' +
                ", idKhu='" + idKhu + '\'' +
                '}';
    }
}
