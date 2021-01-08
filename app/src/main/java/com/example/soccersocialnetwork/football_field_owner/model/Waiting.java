package com.example.soccersocialnetwork.football_field_owner.model;

public class Waiting {
    String tenDoi, san, ngay, gio, idDuyet, anhDoi;

    public Waiting(String tenDoi, String san, String ngay, String gio, String idDuyet, String anhDoi) {
        this.tenDoi = tenDoi;
        this.san = san;
        this.ngay = ngay;
        this.gio = gio;
        this.idDuyet = idDuyet;
        this.anhDoi = anhDoi;
    }

    public Waiting() {
    }

    public String getAnhDoi() {
        return anhDoi;
    }

    public void setAnhDoi(String anhDoi) {
        this.anhDoi = anhDoi;
    }

    public String getIdDuyet() {
        return idDuyet;
    }

    public void setIdDuyet(String idDuyet) {
        this.idDuyet = idDuyet;
    }

    public Waiting() {
    }

    public String getTenDoi() {
        return tenDoi;
    }

    public void setTenDoi(String tenDoi) {
        this.tenDoi = tenDoi;
    }

    public String getSan() {
        return san;
    }

    public void setSan(String san) {
        this.san = san;
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

    @Override
    public String toString() {
        return "Waiting{" +
                "tenDoi='" + tenDoi + '\'' +
                ", san='" + san + '\'' +
                ", ngay='" + ngay + '\'' +
                ", gio='" + gio + '\'' +
                ", idDuyet='" + idDuyet + '\'' +
                ", anhDoi='" + anhDoi + '\'' +
                '}';
    }
}
