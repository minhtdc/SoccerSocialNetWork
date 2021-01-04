package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class Feeds {
    int hanGio;
    String thanhPho;
    String quan;
    String ngay;
    String gio;
    String thongBao;

    public int getHanGio() {
        return hanGio;
    }

    public void setHanGio(int thoiGian) {
        this.hanGio = thoiGian;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public String getThongBao() {
        return thongBao;
    }

    public void setThongBao(String thongBao) {
        this.thongBao = thongBao;
    }

    public Feeds(int hanGio, String thanhPho, String quan, String ngay, String gio, String thongBao) {
        this.hanGio = hanGio;
        this.thanhPho = thanhPho;
        this.quan = quan;
        this.ngay = ngay;
        this.gio = gio;
        this.thongBao = thongBao;
    }
    public Feeds() { }
    @Override
    public String toString() {
        return "Feeds{" +
                "hanGio=" + hanGio +
                ", thanhPho='" + thanhPho + '\'' +
                ", quan='" + quan + '\'' +
                ", ngay='" + ngay + '\'' +
                ", gio='" + gio + '\'' +
                ", thongBao='" + thongBao + '\'' +
                '}';
    }
}


