package com.example.soccersocialnetwork.football_field_owner.model;

public class Zone {
    String tenKhu, anh, thanhPho, quan, diaChi, pushId;
    int gioMo, phutMo, gioDong, phutDong;

    public Zone() {
    }

    public Zone(String tenKhu, String anh, String thanhPho, String quan, String diaChi, int gioMo, int phutMo, int gioDong, int phutDong, String pushId) {
        this.tenKhu = tenKhu;
        this.anh = anh;
        this.thanhPho = thanhPho;
        this.quan = quan;
        this.diaChi = diaChi;
        this.gioMo = gioMo;
        this.phutMo = phutMo;
        this.gioDong = gioDong;
        this.phutDong = phutDong;
        this.pushId = pushId;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getTenKhu() {
        return tenKhu;
    }

    public void setTenKhu(String tenKhu) {
        this.tenKhu = tenKhu;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getGioMo() {
        return gioMo;
    }

    public void setGioMo(int gioMo) {
        this.gioMo = gioMo;
    }

    public int getPhutMo() {
        return phutMo;
    }

    public void setPhutMo(int phutMo) {
        this.phutMo = phutMo;
    }

    public int getGioDong() {
        return gioDong;
    }

    public void setGioDong(int gioDong) {
        this.gioDong = gioDong;
    }

    public int getPhutDong() {
        return phutDong;
    }

    public void setPhutDong(int phutDong) {
        this.phutDong = phutDong;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "tenKhu='" + tenKhu + '\'' +
                ", anh='" + anh + '\'' +
                ", thanhPho='" + thanhPho + '\'' +
                ", quan='" + quan + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", gioMo='" + gioMo + '\'' +
                ", phutMo='" + phutMo + '\'' +
                ", gioDong='" + gioDong + '\'' +
                ", phutDong='" + phutDong + '\'' +
                ", pushId='" + pushId + '\'' +
                '}';
    }
}
