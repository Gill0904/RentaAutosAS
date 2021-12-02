package com.example.proyectofinal.ui.Revision;

import java.io.Serializable;

public class Revisiones implements Serializable {
    private byte _id;
    private int _numeroRevision;
    private String _matricula;
    private String _marca;
    private String _modelo;
    private String _cambioAceite;
    private String _cambioFiltro;
    private String _cambioFrenos;
    private String _comentarios;
    private String _estadoRevision;
    private String _fechaRevision;

    public Revisiones(byte _id, int _numeroRevision, String _matricula, String _marca, String _modelo,
                      String _cambioAceite, String _cambioFiltro, String _cambioFrenos, String _comentarios,
                      String _estadoRevision, String _fechaRevision) {
        this._id = _id;
        this._numeroRevision = _numeroRevision;
        this._matricula = _matricula;
        this._marca = _marca;
        this._modelo = _modelo;
        this._cambioAceite = _cambioAceite;
        this._cambioFiltro = _cambioFiltro;
        this._cambioFrenos = _cambioFrenos;
        this._comentarios = _comentarios;
        this._estadoRevision = _estadoRevision;
        this._fechaRevision = _fechaRevision;
    }

    public byte get_id() {
        return _id;
    }

    public void set_id(byte _id) {
        this._id = _id;
    }

    public int get_numeroRevision() {
        return _numeroRevision;
    }

    public void set_numeroRevision(int _numeroRevision) {
        this._numeroRevision = _numeroRevision;
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

    public String get_cambioAceite() {
        return _cambioAceite;
    }

    public void set_cambioAceite(String _cambioAceite) {
        this._cambioAceite = _cambioAceite;
    }

    public String get_cambioFiltro() {
        return _cambioFiltro;
    }

    public void set_cambioFiltro(String _cambioFiltro) {
        this._cambioFiltro = _cambioFiltro;
    }

    public String get_cambioFrenos() {
        return _cambioFrenos;
    }

    public void set_cambioFrenos(String _cambioFrenos) {
        this._cambioFrenos = _cambioFrenos;
    }

    public String get_comentarios() {
        return _comentarios;
    }

    public void set_comentarios(String _comentarios) {
        this._comentarios = _comentarios;
    }

    public String get_estadoRevision() {
        return _estadoRevision;
    }

    public void set_estadoRevision(String _estadoRevision) {
        this._estadoRevision = _estadoRevision;
    }

    public String get_fechaRevision() {
        return _fechaRevision;
    }

    public void set_fechaRevision(String _fechaRevision) {
        this._fechaRevision = _fechaRevision;
    }
}
