package com.example.soccersocialnetwork.TranDuyHuynh.models;

import com.example.soccersocialnetwork.data_models.Users;

import java.io.Serializable;
import java.util.ArrayList;

public class thongTinTranDau implements Serializable {
    private String diaDiem;
    private String thoiGian;
    private String Ngay;
    private String San;
    private String thongTinThem;
    ArrayList<Users> thanhVienThamGia;

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

    private String anhDoi;
    private String tenDoi;

    public String getIdDoiDangTin() {
        return idDoiDangTin;
    }

    public void setIdDoiDangTin(String idDoiDangTin) {
        this.idDoiDangTin = idDoiDangTin;
    }

    private String idDoiDangTin;

    public String getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(String idTranDau) {
        this.idTranDau = idTranDau;
    }

    private String idTranDau;

    public thongTinTranDau(String diaDiem, String thoiGian, String ngay, String san, String thongTinThem,String idTranDau, ArrayList<Users> thanhVienThamGia,String idDoiDangTin,String tenDoi,String anhDoi) {
        this.diaDiem = diaDiem;
        this.thoiGian = thoiGian;
        Ngay = ngay;
        San = san;
        this.thongTinThem = thongTinThem;
        this.idTranDau = idTranDau;
        this.thanhVienThamGia = thanhVienThamGia;
        this.idDoiDangTin = idDoiDangTin;
        this.tenDoi = tenDoi;
        this.anhDoi = anhDoi;
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

    public ArrayList<Users> getThanhVienThamGia() {
        return thanhVienThamGia;
    }

    public void setThanhVienThamGia(ArrayList<Users> thanhVienThamGia) {
        this.thanhVienThamGia = thanhVienThamGia;
    }
}