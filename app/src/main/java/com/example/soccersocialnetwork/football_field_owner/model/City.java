package com.example.soccersocialnetwork.football_field_owner.model;

public class City {
    String id;
    String name, type;

    @Override
    public String toString() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public City(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public City() {
    }
}
