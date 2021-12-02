package com.example.proyectofinal.ui.Usuarios;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Cliente;
import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.Catalogo.Coche;
import com.example.proyectofinal.ui.Catalogo.HolderCoches;
import com.example.proyectofinal.ui.Revision.EliminarRevision;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorUsuarios extends RecyclerView.Adapter<HolderUsuarios> {
    List<Usuario> usuarios;
    Context c;
    public AdaptadorUsuarios(Context cn){
        this.c=cn;
        usuarios= new ArrayList<>();
    }
    public void AgregarUsuario(Usuario us){
        usuarios.add(us);
        notifyItemInserted(usuarios.size());
    }

    @NonNull
    @Override
    public HolderUsuarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_usuario,parent,false);
        return new HolderUsuarios(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUsuarios holder, final int position) {
        holder.getTxtNombre().setText(String.valueOf(usuarios.get(position).get_nombre()));
        holder.getTxtContrasena().setText(String.valueOf(usuarios.get(position).get_contrasenia()));
        holder.getTxtRol().setText(String.valueOf(usuarios.get(position).get_rol()));
        holder.getBtnEliminar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(c, EliminarUsuario.class);
                i.putExtra("ide",String.valueOf(usuarios.get(position).get_id()));
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }
}
