package com.example.proyectofinal.ui.Usuarios;

public class Usuario {
    private byte _id;
    private String _nombre;
    private String _contrasenia;
    private String _rol;

    public Usuario(byte _id, String _nombre, String _contrasenia, String _rol) {
        this._id = _id;
        this._nombre = _nombre;
        this._contrasenia = _contrasenia;
        this._rol = _rol;
    }

    public byte get_id() {
        return _id;
    }

    public void set_id(byte _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_contrasenia() {
        return _contrasenia;
    }

    public void set_contrasenia(String _contrasenia) {
        this._contrasenia = _contrasenia;
    }

    public String get_rol() {
        return _rol;
    }

    public void set_rol(String _rol) {
        this._rol = _rol;
    }
}
