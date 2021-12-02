package com.example.proyectofinal.ui.Revision;

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
import com.example.proyectofinal.ui.Catalogo.HolderCoches;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class AdaptadorRevision extends  RecyclerView.Adapter<HolderRevision>{

    List<Revisiones> revisiones;
    Context c;
    public AdaptadorRevision(Context cn){
        this.c=cn;
        revisiones= new ArrayList<>();
    }
    public void AgregarRevisionM(Revisiones rev){
        revisiones.add(rev);
        notifyItemInserted(revisiones.size());
    }
    @NonNull
    @Override
    public HolderRevision onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_revision,parent,false);
        return new HolderRevision(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRevision holder, final int position) {
        holder.getTxtCambioAceite().setText(String.valueOf(revisiones.get(position).get_cambioAceite()));
        holder.getTxtCambioFiltro().setText(String.valueOf(revisiones.get(position).get_cambioFiltro()));
        holder.getTxtMarca().setText(String.valueOf(revisiones.get(position).get_marca()));
        holder.getTxtModelo().setText(String.valueOf(revisiones.get(position).get_modelo()));
        holder.getTxtCambioFrenos().setText(String.valueOf(revisiones.get(position).get_cambioFrenos()));
        holder.getTxtComentarios().setText(String.valueOf(revisiones.get(position).get_comentarios()));
        holder.getTxtEstadoRevision().setText(String.valueOf(revisiones.get(position).get_estadoRevision()));
        holder.getTxtFechaRevision().setText(String.valueOf(revisiones.get(position).get_fechaRevision()));
        holder.getTxtId().setText(String.valueOf(revisiones.get(position).get_id()));
        holder.getTxtMatricula().setText(String.valueOf(revisiones.get(position).get_matricula()));
        holder.getTxtNumeroRevision().setText(String.valueOf(revisiones.get(position).get_numeroRevision()));
        holder.getBtnEliminar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(c, EliminarRevision.class);
                    i.putExtra("id",String.valueOf(revisiones.get(position).get_id()));
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return revisiones.size();
    }
}
