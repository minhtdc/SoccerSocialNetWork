package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class ThongBao {
    String noiDung;
    String idSan;
    String idDoi;
    String idBinhLuan;
    String idThongBao;
    String img;
    String uid;

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getIdSan() {
        return idSan;
    }

    public void setIdSan(String idSan) {
        this.idSan = idSan;
    }

    public String getIdDoi() {
        return idDoi;
    }

    public void setIdDoi(String idDoi) {
        this.idDoi = idDoi;
    }

    public String getIdBinhLuan() {
        return idBinhLuan;
    }

    public void setIdBinhLuan(String idBinhLuan) {
        this.idBinhLuan = idBinhLuan;
    }

    public String getIdThongBao() {
        return idThongBao;
    }

    public void setIdThongBao(String idThongBao) {
        this.idThongBao = idThongBao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public ThongBao(String noiDung, String idSan, String idDoi, String idBinhLuan, String idThongBao, String img, String uid) {
        this.noiDung = noiDung;
        this.idSan = idSan;
        this.idDoi = idDoi;
        this.idBinhLuan = idBinhLuan;
        this.idThongBao = idThongBao;
        this.img = img;
        this.uid = uid;
    }

    public ThongBao() {

    }
}
