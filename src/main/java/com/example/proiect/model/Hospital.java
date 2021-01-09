package com.example.proiect.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hospital {
    private int id;
    private String name;
    private String city;
    private String address;

    public Hospital(int id, String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public Hospital() {
    }
}
