package com.example.proyectofinal.ui.Revision;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;

public class HolderRevision extends RecyclerView.ViewHolder {
    TextView txtId,txtNumeroRevision,txtMatricula,txtMarca,txtModelo,txtCambioAceite,txtCambioFiltro,txtCambioFrenos,txtComentarios
            ,txtEstadoRevision,txtFechaRevision;
    Button btnEliminar;

    public HolderRevision(@NonNull View itemView) {
        super(itemView);
        this.txtCambioAceite = itemView.findViewById(R.id.txtCambioAceite);
        this.txtCambioFiltro = itemView.findViewById(R.id.txtCambioFiltro);
        this.txtCambioFrenos = itemView.findViewById(R.id.txtCambioFrenos);
        this.txtComentarios = itemView.findViewById(R.id.txtComentarios);
        this.txtEstadoRevision = itemView.findViewById(R.id.txtestadoRevision);
        this.txtFechaRevision = itemView.findViewById(R.id.txtFechaRevision);
        this.txtNumeroRevision = itemView.findViewById(R.id.txtNumeroRevision);
        this.txtId = itemView.findViewById(R.id.txtId);
        this.txtMatricula = itemView.findViewById(R.id.txtMatricula);
        this.txtMarca = itemView.findViewById(R.id.txtMarca);
        this.txtModelo = itemView.findViewById(R.id.txtModelo);
        this.btnEliminar = itemView.findViewById(R.id.btnEliminar);
    }


    public TextView getTxtId() {
        return txtId;
    }

    public void setTxtId(TextView txtId) {
        this.txtId = txtId;
    }

    public TextView getTxtNumeroRevision() {
        return txtNumeroRevision;
    }

    public void setTxtNumeroRevision(TextView txtNumeroRevision) {
        this.txtNumeroRevision = txtNumeroRevision;
    }

    public TextView getTxtMatricula() {
        return txtMatricula;
    }

    public void setTxtMatricula(TextView txtMatricula) {
        this.txtMatricula = txtMatricula;
    }

    public TextView getTxtMarca() {
        return txtMarca;
    }

    public void setTxtMarca(TextView txtMarca) {
        this.txtMarca = txtMarca;
    }

    public TextView getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(TextView txtModelo) {
        this.txtModelo = txtModelo;
    }

    public TextView getTxtCambioAceite() {
        return txtCambioAceite;
    }

    public void setTxtCambioAceite(TextView txtCambioAceite) {
        this.txtCambioAceite = txtCambioAceite;
    }

    public TextView getTxtCambioFiltro() {
        return txtCambioFiltro;
    }

    public void setTxtCambioFiltro(TextView txtCambioFiltro) {
        this.txtCambioFiltro = txtCambioFiltro;
    }

    public TextView getTxtCambioFrenos() {
        return txtCambioFrenos;
    }

    public void setTxtCambioFrenos(TextView txtCambioFrenos) {
        this.txtCambioFrenos = txtCambioFrenos;
    }

    public TextView getTxtComentarios() {
        return txtComentarios;
    }

    public void setTxtComentarios(TextView txtComentarios) {
        this.txtComentarios = txtComentarios;
    }

    public TextView getTxtEstadoRevision() {
        return txtEstadoRevision;
    }

    public void setTxtEstadoRevision(TextView txtEstadoRevision) {
        this.txtEstadoRevision = txtEstadoRevision;
    }

    public TextView getTxtFechaRevision() {
        return txtFechaRevision;
    }

    public void setTxtFechaRevision(TextView txtFechaRevision) {
        this.txtFechaRevision = txtFechaRevision;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button btnEliminar) {
        this.btnEliminar = btnEliminar;
    }
}
