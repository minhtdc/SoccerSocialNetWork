package com.example.soccersocialnetwork.TranDuyHuynh.models;

import java.util.ArrayList;

public class thongTinTranDau {
    private String diaDiem;
    private String thoiGian;
    private String Ngay;
    private String San;
    private String thongTinThem;

    public String getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(String idTranDau) {
        this.idTranDau = idTranDau;
    }

    private String idTranDau;
    private ArrayList<String> thanhVienThamGia;

    public thongTinTranDau(String diaDiem, String thoiGian, String ngay, String san, String thongTinThem,String idTranDau, ArrayList<String> thanhVienThamGia) {
        this.diaDiem = diaDiem;
        this.thoiGian = thoiGian;
        Ngay = ngay;
        San = san;
        this.thongTinThem = thongTinThem;
        this.idTranDau = idTranDau;
        this.thanhVienThamGia = thanhVienThamGia;
    }
    public thongTinTranDau(){

    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getSan() {
        return San;
    }

    public void setSan(String san) {
        San = san;
    }

    public String getThongTinThem() {
        return thongTinThem;
    }

    public void setThongTinThem(String thongTinThem) {
        this.thongTinThem = thongTinThem;
    }

    public ArrayList<String> getThanhVienThamGia() {
        return thanhVienThamGia;
    }

    public void setThanhVienThamGia(ArrayList<String> thanhVienThamGia) {
        this.thanhVienThamGia = thanhVienThamGia;
    }
}
