package com.example.soccersocialnetwork.TranDuyHuynh.models;

public class  information_listStadium {
    String hinhSan, idKhu;
    String mTenSan, mLoaiHinhSan, mLoaiSan,mDiaChiSan;

    public information_listStadium(String hinhSan, String idKhu, String mTenSan, String mLoaiHinhSan, String mLoaiSan, String mDiaChiSan) {
        this.hinhSan = hinhSan;
        this.idKhu = idKhu;
        this.mTenSan = mTenSan;
        this.mLoaiHinhSan = mLoaiHinhSan;
        this.mLoaiSan = mLoaiSan;
        this.mDiaChiSan = mDiaChiSan;
    }

    public String getIdKhu() {
        return idKhu;
    }

    public void setIdKhu(String idKhu) {
        this.idKhu = idKhu;
    }

    public String getHinhSan() {
        return hinhSan;
    }

    public void setHinhSan(String hinhSan) {
        this.hinhSan = hinhSan;
    }

    public String getmTenSan() {
        return mTenSan;
    }

    public void setmTenSan(String mTenSan) {
        this.mTenSan = mTenSan;
    }

    public String getmLoaiHinhSan() {
        return mLoaiHinhSan;
    }

    public void setmLoaiHinhSan(String mLoaiHinhSan) {
        this.mLoaiHinhSan = mLoaiHinhSan;
    }

    public String getmLoaiSan() {
        return mLoaiSan;
    }

    public void setmLoaiSan(String mLoaiSan) {
        this.mLoaiSan = mLoaiSan;
    }

    public String getmDiaChiSan() {
        return mDiaChiSan;
    }

    public void setmDiaChiSan(String mDiaChiSan) {
        this.mDiaChiSan = mDiaChiSan;
    }

    public information_listStadium() {
    }

    @Override
    public String toString() {
        return "information_listStadium{" +
                "hinhSan='" + hinhSan + '\'' +
                ", idKhu='" + idKhu + '\'' +
                ", mTenSan='" + mTenSan + '\'' +
                ", mLoaiHinhSan='" + mLoaiHinhSan + '\'' +
                ", mLoaiSan='" + mLoaiSan + '\'' +
                ", mDiaChiSan='" + mDiaChiSan + '\'' +
                '}';
    }
}
