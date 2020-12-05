package com.example.soccersocialnetwork.DoanThanhTung.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelp extends SQLiteOpenHelper {
    public DBHelp(Context context) {
        super(context, "SoccerSocialNetWork", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table Team(idDoi int ,tenDoi text,email text,sdt text,gioiThieu text,tieuChi text,sLogan text,hinhAnh text)";

//        String sql="create table SinhVien(hoten text, gioitinh Text , khoa text )";

        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int data;
    }
}