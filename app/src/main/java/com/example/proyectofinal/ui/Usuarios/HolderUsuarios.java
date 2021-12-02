package com.example.proyectofinal.ui.Usuarios;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;

public class HolderUsuarios extends RecyclerView.ViewHolder  {
    TextView txtContrasena;
    TextView txtNombre;
    TextView txtRol;
    Button btnEliminar;
    ImageView imagenCoche;

    public TextView getTxtRol() {
        return txtRol;
    }

    public void setTxtRol(TextView txtRol) {
        this.txtRol = txtRol;
    }


    public TextView getTxtContrasena() {
        return txtContrasena;
    }

    public void setTxtContrasena(TextView txtContrasena) {
        this.txtContrasena = txtContrasena;
    }

    public TextView getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(TextView txtNombre) {
        this.txtNombre = txtNombre;
    }

    public Button getBtnEliminar() {
        return btnEliminar;
    }

    public void setBtnEliminar(Button btnEliminar) {
        this.btnEliminar = btnEliminar;
    }

    public ImageView getImagenCoche() {
        return imagenCoche;
    }

    public void setImagenCoche(ImageView imagenCoche) {
        this.imagenCoche = imagenCoche;
    }

    public HolderUsuarios(@NonNull View itemView) {
        super(itemView);
        this.txtContrasena = itemView.findViewById(R.id.txtPass);
        this.txtNombre = itemView.findViewById(R.id.txtUsername);
        this.txtRol = itemView.findViewById(R.id.txtRol);
        this.btnEliminar = itemView.findViewById(R.id.btnEliminarUS);
    }
}
