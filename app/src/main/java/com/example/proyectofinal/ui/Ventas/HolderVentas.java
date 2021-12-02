package com.example.proyectofinal.ui.Ventas;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;

public class HolderVentas extends RecyclerView.ViewHolder   {
    public TextView getTxtMarca() {
        return txtMarca;
    }

    public void setTxtMarca(TextView txtMarca) {
        this.txtMarca = txtMarca;
    }

    public TextView getTxtColor() {
        return txtColor;
    }

    public void setTxtColor(TextView txtColor) {
        this.txtColor = txtColor;
    }

    public TextView getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(TextView txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public TextView getTxtFecha() {
        return txtFecha;
    }

    public void setTxtFecha(TextView txtFecha) {
        this.txtFecha = txtFecha;
    }

    public ImageView getImagenCoche() {
        return imagenCoche;
    }

    public void setImagenCoche(ImageView imagenCoche) {
        this.imagenCoche = imagenCoche;
    }

    public TextView getTxtModelo() {
        return txtModelo;
    }

    public void setTxtModelo(TextView txtModelo) {
        this.txtModelo = txtModelo;
    }

    public TextView getTxtId() {
        return txtId;
    }

    public void setTxtId(TextView txtId) {
        this.txtId = txtId;
    }

    TextView txtModelo,txtId,txtMarca,txtColor,txtPrecio,txtFecha;
    ImageView imagenCoche;
    public HolderVentas(@NonNull View itemView) {
        super(itemView);
        this.txtMarca = itemView.findViewById(R.id.txtMarcaV);
        this.txtModelo = itemView.findViewById(R.id.txtModeloV);
        this.txtColor = itemView.findViewById(R.id.txtColorV);
        this.txtFecha = itemView.findViewById(R.id.txtFechaV);
        this.txtPrecio = itemView.findViewById(R.id.txtPrecioV);
        this.txtId = itemView.findViewById(R.id.txtIdV);
    }
}
