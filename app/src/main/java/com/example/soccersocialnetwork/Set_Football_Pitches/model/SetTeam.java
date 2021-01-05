package com.example.soccersocialnetwork.Set_Football_Pitches.model;

public class SetTeam {
    String idDoi, tenDoi;

    public SetTeam() {
    }

    public SetTeam(String idDoi, String tenDoi) {
        this.idDoi = idDoi;
        this.tenDoi = tenDoi;
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

