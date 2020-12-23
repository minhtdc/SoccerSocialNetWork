package com.example.soccersocialnetwork.football_field_owner.model;

public class Zone {
    String tenKhu, anh, thanhPho, quan, diaChi, pushId, idUser;
    int gioMo, phutMo, gioDong, phutDong;
    boolean coTuNhien, coNhanTao, namNguoi, bayNguoi, chinNguoi;

    public Zone(String tenKhu, String anh, String thanhPho, String quan,
                String diaChi, String pushId, String idUser, int gioMo,
                int phutMo, int gioDong, int phutDong, boolean coTuNhien, boolean coNhanTao,
                boolean namNguoi, boolean bayNguoi, boolean chinNguoi) {
        this.tenKhu = tenKhu;
        this.anh = anh;
        this.thanhPho = thanhPho;
        this.quan = quan;
        this.diaChi = diaChi;
        this.pushId = pushId;
        this.idUser = idUser;
        this.gioMo = gioMo;
        this.phutMo = phutMo;
        this.gioDong = gioDong;
        this.phutDong = phutDong;
        this.coTuNhien = coTuNhien;
        this.coNhanTao = coNhanTao;
        this.namNguoi = namNguoi;
        this.bayNguoi = bayNguoi;
        this.chinNguoi = chinNguoi;
    }

    public Zone() {
    }

    public Zone(String tenKhu, String anh, String thanhPho, String quan, String diaChi, String pushId, String idUser, int gioMo, int phutMo, int gioDong, int phutDong) {
        this.tenKhu = tenKhu;
        this.anh = anh;
        this.thanhPho = thanhPho;
        this.quan = quan;
        this.diaChi = diaChi;
        this.pushId = pushId;
        this.idUser = idUser;
        this.gioMo = gioMo;
        this.phutMo = phutMo;
        this.gioDong = gioDong;
        this.phutDong = phutDong;
    }

    public boolean isCoTuNhien() {
        return coTuNhien;
    }

    public void setCoTuNhien(boolean coTuNhien) {
        this.coTuNhien = coTuNhien;
    }

    public boolean isCoNhanTao() {
        return coNhanTao;
    }

    public void setCoNhanTao(boolean coNhanTao) {
        this.coNhanTao = coNhanTao;
    }

    public boolean isNamNguoi() {
        return namNguoi;
    }

    public void setNamNguoi(boolean namNguoi) {
        this.namNguoi = namNguoi;
    }

    public boolean isBayNguoi() {
        return bayNguoi;
    }

    public void setBayNguoi(boolean bayNguoi) {
        this.bayNguoi = bayNguoi;
    }

    public boolean isChinNguoi() {
        return chinNguoi;
    }

    public void setChinNguoi(boolean chinNguoi) {
        this.chinNguoi = chinNguoi;
    }

    @Override
    public String toString() {
        return "Zone{" +
                "tenKhu='" + tenKhu + '\'' +
                ", anh='" + anh + '\'' +
                ", thanhPho='" + thanhPho + '\'' +
                ", quan='" + quan + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", pushId='" + pushId + '\'' +
                ", idUser='" + idUser + '\'' +
                ", gioMo=" + gioMo +
                ", phutMo=" + phutMo +
                ", gioDong=" + gioDong +
                ", phutDong=" + phutDong +
                '}';
    }


    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

}
