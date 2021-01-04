package com.example.soccersocialnetwork.DoanThanhTung.Models;

public class ListTeamUser {
    private String idDoiTruong;
    private String idThanhVien;
    public ListTeamUser(String idDoiTruong, String idThanhVien) {
        this.idDoiTruong = idDoiTruong;
        this.idThanhVien = idThanhVien;
    }
    public ListTeamUser() {}

    public String getIdThanhVien() {
        return idThanhVien;
    }

    public void setIdThanhVien(String idThanhVien) {
        this.idThanhVien = idThanhVien;
    }



    public String getIdDoiTruong() {
        return idDoiTruong;
    }

    public void setIdDoiTruong(String idDoiTruong) {
        this.idDoiTruong = idDoiTruong;
    }
}
