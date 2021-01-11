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
    private String anhDoi;
    private String tenDoi;
    private String idTranDau;
    private String idDoiDangTin;
    private String idNguoiDangTin;
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


    public String getIdDoiDangTin() {
        return idDoiDangTin;
    }

    public void setIdDoiDangTin(String idDoiDangTin) {
        this.idDoiDangTin = idDoiDangTin;
    }


    public String getIdTranDau() {
        return idTranDau;
    }

    public void setIdTranDau(String idTranDau) {
        this.idTranDau = idTranDau;
    }


    public thongTinTranDau(String diaDiem, String thoiGian, String ngay, String san, String thongTinThem, String anhDoi, String tenDoi, String idTranDau, String idDoiDangTin, String idNguoiDangTin, ArrayList<Users> thanhVienThamGia) {
        this.diaDiem = diaDiem;
        this.thoiGian = thoiGian;
        Ngay = ngay;
        San = san;
        this.thongTinThem = thongTinThem;
        this.anhDoi = anhDoi;
        this.tenDoi = tenDoi;
        this.idTranDau = idTranDau;
        this.idDoiDangTin = idDoiDangTin;
        this.idNguoiDangTin = idNguoiDangTin;
        this.thanhVienThamGia = thanhVienThamGia;
    }

    public thongTinTranDau() {

    }

    public String getIdNguoiDangTin() {
        return idNguoiDangTin;
    }

    public void setIdNguoiDangTin(String idNguoiDangTin) {
        this.idNguoiDangTin = idNguoiDangTin;
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