package com.example.laboratorio3.entity.DTO;

import android.hardware.Sensor;

import java.io.Serializable;

public class UsuarioDto implements Serializable {
    private String username;
    private String password;

    public UsuarioDto() {
    }

    public UsuarioDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
