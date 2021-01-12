package com.example.soccersocialnetwork.Set_Football_Pitches.model;

public class SetTeam {
    String idDoi, tenDoi, anhDoi;

    public SetTeam() {
    }

    public SetTeam(String idDoi, String tenDoi, String anhDoi) {
        this.idDoi = idDoi;
        this.tenDoi = tenDoi;
        this.anhDoi = anhDoi;
    }

    public String getAnhDoi() {
        return anhDoi;
    }

    public void setAnhDoi(String anhDoi) {
        this.anhDoi = anhDoi;
    }

    public String getIdDoi() {
        return idDoi;
    }

    public void setIdDoi(String idDoi) {
        this.idDoi = idDoi;
    }

    public String getTenDoi() {
        return tenDoi;
    }

    public void setTenDoi(String tenDoi) {
        this.tenDoi = tenDoi;
    }

    @Override
    public String toString() {
        return tenDoi;
    }
}

