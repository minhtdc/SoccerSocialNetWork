package com.example.soccersocialnetwork.TranDuyHuynh.models;

public class information_listStadium {
    int hinhSan;
    String mTenSan, mLoaiHinhSan, mLoaiSan,mDiaChiSan;

    public int getHinhSan() {
        return hinhSan;
    }

    public void setHinhSan(int hinhSan) {
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

    public information_listStadium(int hinhSan, String mTenSan, String mLoaiHinhSan, String mLoaiSan, String mDiaChiSan) {
        this.hinhSan = hinhSan;
        this.mTenSan = mTenSan;
        this.mLoaiHinhSan = mLoaiHinhSan;
        this.mLoaiSan = mLoaiSan;
        this.mDiaChiSan = mDiaChiSan;
    }
}
