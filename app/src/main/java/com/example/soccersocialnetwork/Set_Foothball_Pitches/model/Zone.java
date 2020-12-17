package com.example.soccersocialnetwork.Set_Foothball_Pitches.model;

public class Zone {
    String tenKhu, anh, thanhPho, quan, diaChi, gioMo, phutMo, gioDong, phutDong, pushId;;

    public Zone() {
    }

    public Zone(String tenKhu, String anh, String thanhPho, String quan, String diaChi, String gioMo, String phutMo, String gioDong, String phutDong, String pushId) {
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

    public String getGioMo() {
        return gioMo;
    }

    public void setGioMo(String gioMo) {
        this.gioMo = gioMo;
    }

    public String getPhutMo() {
        return phutMo;
    }

    public void setPhutMo(String phutMo) {
        this.phutMo = phutMo;
    }

    public String getGioDong() {
        return gioDong;
    }

    public void setGioDong(String gioDong) {
        this.gioDong = gioDong;
    }

    public String getPhutDong() {
        return phutDong;
    }

    public void setPhutDong(String phutDong) {
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
