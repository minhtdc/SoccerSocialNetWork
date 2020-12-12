package com.example.soccersocialnetwork.TranDuyHuynh.models;

public class information_notifications {
    int mImage;
    String mthongBao;

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmthongBao() {
        return mthongBao;
    }

    public void setmthongBao(String mthongBao) {
        this.mthongBao = mthongBao;
    }

    public information_notifications(int mImage, String mthongBao) {
        this.mImage = mImage;
        this.mthongBao = mthongBao;
    }
}
