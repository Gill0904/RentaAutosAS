package com.example.proyectofinal.ui.Catalogo;

import java.io.Serializable;

public class Coche implements Serializable {
    private byte _id;
    private String _matricula;
    private String _marca;
    private String _modelo;
    private String _color;
    private Double _precio;
    private String _plazo;
    private String _imagen;
    private String _fecha;

    public Coche(byte _id, String _matricula, String _marca, String _modelo, String _color, Double _precio, String _plazo, String _imagen,String _fecha) {
        this._id = _id;
        this._matricula = _matricula;
        this._marca = _marca;
        this._modelo = _modelo;
        this._color = _color;
        this._precio = _precio;
        this._plazo = _plazo;
        this._imagen = _imagen;
        this._fecha=_fecha;
    }

    public String get_fecha() {
        return _fecha;
    }

    public void set_fecha(String _fecha) {
        this._fecha = _fecha;
    }

    public byte get_id() {
        return _id;
    }

    public void set_id(byte _id) {
        this._id = _id;
    }

    public String get_matricula() {
        return _matricula;
    }

    public void set_matricula(String _matricula) {
        this._matricula = _matricula;
    }

    public String get_marca() {
        return _marca;
    }

    public void set_marca(String _marca) {
        this._marca = _marca;
    }

    public String get_modelo() {
        return _modelo;
    }

    public void set_modelo(String _modelo) {
        this._modelo = _modelo;
    }

    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public Double get_precio() {
        return _precio;
    }

    public void set_precio(Double _precio) {
        this._precio = _precio;
    }

    public String get_plazo() {
        return _plazo;
    }

    public void set_plazo(String _plazo) {
        this._plazo = _plazo;
    }

    public String get_imagen() {
        return _imagen;
    }

    public void set_imagen(String _imagen) {
        this._imagen = _imagen;
    }
}
