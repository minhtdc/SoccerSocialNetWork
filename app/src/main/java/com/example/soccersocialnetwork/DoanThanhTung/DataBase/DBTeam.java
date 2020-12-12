package com.example.soccersocialnetwork.DoanThanhTung.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.soccersocialnetwork.DoanThanhTung.Models.Team;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DBTeam {
    DBHelp dbHelp;
    SQLiteDatabase database;

    public DBTeam(Context conText) {
        dbHelp = new DBHelp(conText);
        try {
            database = dbHelp.getWritableDatabase();
        } catch (SQLException ex) {
            database = dbHelp.getReadableDatabase();
        }
    }

    public void addTeam(Team team) {
        SQLiteDatabase db = dbHelp.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idDoi", team.getIdDoi());
        values.put("tenDoi", team.getTenDoi());
        values.put("email", team.getEmail());
        values.put("sdt", team.getSdt());
        values.put("gioiThieu", team.getGioiThieu());
        values.put("tieuChi", team.getTieuChi());
        values.put("sLogan", team.getsLogan());
        values.put("hinhAnh", team.getHinhAnh());
        db.insert("Team", null, values);
    }

    public ArrayList<Team> readTeam() {
        ArrayList<Team> teams = new ArrayList<>();
        String sql = "select * from Team";
        SQLiteDatabase db = dbHelp.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        do {
            Team team = new Team();
//            team.setTenDoi(cursor.getString(1));
//            team.setEmail(cursor.getString(2));
//            team.setSdt(cursor.getString(3));
//            team.setGioiThieu(cursor.getString(4));
//            team.setTieuChi(cursor.getString(5));
//            team.setsLogan(cursor.getString(6));
//            team.setHinhAnh(cursor.getString(7));
            teams.add(team);
        } while (cursor.moveToNext());
        return teams;
    }

    public void uriToByteArray(String uri)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buf = new byte[1024];
        int n;
        try {
            while (-1 != (n = fis.read(buf)))
                baos.write(buf, 0, n);

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
    }
}
