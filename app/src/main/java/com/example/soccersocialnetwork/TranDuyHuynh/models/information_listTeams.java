package com.example.soccersocialnetwork.TranDuyHuynh.models;

public class information_listTeams {
    private int mIcon;
    private String mTenDoi, mKhuVuc, mSl_CauThu,mGioiThieu;

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

    public String getmKhuVuc() {
        return mKhuVuc;
    }

    public void setmKhuVuc(String mKhuVuc) {
        this.mKhuVuc = mKhuVuc;
    }

    public String getmSl_CauThu() {
        return mSl_CauThu;
    }

    public void setmSl_CauThu(String mSl_CauThu) {
        this.mSl_CauThu = mSl_CauThu;
    }

    public String getmGioiThieu() {
        return mGioiThieu;
    }

    public void setmGioiThieu(String mGioiThieu) {
        this.mGioiThieu = mGioiThieu;
    }

    public information_listTeams(int mIcon, String mTenDoi, String mKhuVuc, String mSl_CauThu, String mGioiThieu) {
        this.mIcon = mIcon;
        this.mTenDoi = mTenDoi;
        this.mKhuVuc = mKhuVuc;
        this.mSl_CauThu = mSl_CauThu;
        this.mGioiThieu = mGioiThieu;
    }
}
