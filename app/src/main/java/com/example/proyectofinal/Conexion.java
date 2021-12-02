package com.example.proyectofinal;

public class Conexion {
    public Conexion() {
        this._ip = "192.168.0.106";
    }

    String _ip;

    public String get_ip() {
        return _ip;
    }

    public void set_ip(String _ip) {
        this._ip = _ip;
    }
}
