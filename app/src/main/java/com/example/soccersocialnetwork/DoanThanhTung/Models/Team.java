package com.example.soccersocialnetwork.DoanThanhTung.Models;

import com.google.firebase.database.Exclude;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Team {
    public int idDoi;
    public String tenDoi;
    public String email;
    public String sdt;
    public String gioiThieu;
    public String tieuChi;
    public String sLogan;
    public String hinhAnh;
    public String khuVuc;
    public String idDoiTruong;
    public String getIdDoiTruong() {
        return idDoiTruong;
    }

    public void setIdDoiTruong(String idDoiTruong) {
        this.idDoiTruong = idDoiTruong;
    }




    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }



    public int getIdDoi() {
        return idDoi;
    }

    public void setIdDoi(int idDoi) {
        this.idDoi = idDoi;
    }

    public String getTenDoi() {
        return tenDoi;
    }

    public void setTenDoi(String tenDoi) {
        this.tenDoi = tenDoi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public String getTieuChi() {
        return tieuChi;
    }

    public void setTieuChi(String tieuChi) {
        this.tieuChi = tieuChi;
    }

    public String getsLogan() {
        return sLogan;
    }

    public void setsLogan(String sLogan) {
        this.sLogan = sLogan;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Team(int idDoi, String tenDoi, String email, String sdt, String gioiThieu, String tieuChi, String sLogan, String hinhAnh, String khuVuc,String idDoiTruong) {
        this.idDoi = idDoi;
        this.tenDoi = tenDoi;
        this.email = email;
        this.sdt = sdt;
        this.gioiThieu = gioiThieu;
        this.tieuChi = tieuChi;
        this.sLogan = sLogan;
        this.hinhAnh = hinhAnh;
        this.khuVuc = khuVuc;
        this.idDoiTruong = idDoiTruong;
    }

    @Override
    public String toString() {
        return "Team{" +
                "idDoi=" + idDoi +
                ", tenDoi='" + tenDoi + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                ", gioiThieu='" + gioiThieu + '\'' +
                ", tieuChi='" + tieuChi + '\'' +
                ", sLogan='" + sLogan + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", khuVuc='" + khuVuc + '\'' +
                '}';
    }

    public Team() {

    }
}
