package com.example.proyectofinal.ui.Catalogo;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;

public class HolderCoches extends RecyclerView.ViewHolder {

    TextView txtMarca;
    TextView txtModelo;
    Button btnInformacion;
    Button btnComprar;
    ImageView imagenCoche;

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

    public Button getBtnInformacion() {
        return btnInformacion;
    }

    public void setBtnInformacion(Button btnInformacion) {
        this.btnInformacion = btnInformacion;
    }

    public Button getBtnComprar() {
        return btnComprar;
    }

    public void setBtnComprar(Button btnComprar) {
        this.btnComprar = btnComprar;
    }

    public ImageView getImagenCoche() {
        return imagenCoche;
    }

    public void setImagenCoche(ImageView imagenCoche) {
        this.imagenCoche = imagenCoche;
    }

    public HolderCoches(@NonNull View itemView) {
        super(itemView);
        this.txtMarca = itemView.findViewById(R.id.txtMarca);
        this.txtModelo = itemView.findViewById(R.id.txtModelo);
        this.btnInformacion = itemView.findViewById(R.id.btnInformacion);
        this.btnComprar = itemView.findViewById(R.id.btnComprar);
        this.imagenCoche = itemView.findViewById(R.id.imagenAcce);
    }
}
