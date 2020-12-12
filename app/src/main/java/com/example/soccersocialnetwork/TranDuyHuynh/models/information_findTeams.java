package com.example.soccersocialnetwork.TranDuyHuynh.models;

public class information_findTeams {
    private int mIcon;
    private String mTenDoi;

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }

    public String getmTenDoi() {
        return mTenDoi;
    }

    public void setmTenDoi(String mTenDoi) {
        this.mTenDoi = mTenDoi;
    }

    public String getmDiaDiem() {
        return mDiaDiem;
    }

    public void setmDiaDiem(String mDiaDiem) {
        this.mDiaDiem = mDiaDiem;
    }

    public String getmThoiGian() {
        return mThoiGian;
    }

    public void setmThoiGian(String mThoiGian) {
        this.mThoiGian = mThoiGian;
    }

    public String getmNgay() {
        return mNgay;
    }

    public void setmNgay(String mNgay) {
        this.mNgay = mNgay;
    }

    private String mDiaDiem;
    private String mThoiGian;
    private String mNgay;

    public information_findTeams(int mIcon, String mTenDoi, String mDiaDiem, String mThoiGian, String mNgay) {
        this.mIcon = mIcon;
        this.mTenDoi = mTenDoi;
        this.mDiaDiem = mDiaDiem;
        this.mThoiGian = mThoiGian;
        this.mNgay = mNgay;
    }
}
