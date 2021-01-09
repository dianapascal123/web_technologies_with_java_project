package com.example.proiect.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AppointmentUpdate {

    @NotNull
    private int id;

    @NotNull
    private Date date;

    public AppointmentUpdate(@NotNull int id, @NotNull Date date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
