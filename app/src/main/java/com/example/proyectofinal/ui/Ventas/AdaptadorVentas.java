package com.example.proyectofinal.ui.Ventas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.Catalogo.Coche;
import com.example.proyectofinal.ui.Catalogo.HolderCoches;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorVentas extends  RecyclerView.Adapter<HolderVentas> {
    List<Coche> coches;
    Context c;

    public AdaptadorVentas(Context cn) {
        this.c = cn;
        coches = new ArrayList<>();
    }

    public void AgregarCoche(Coche ch) {
        coches.add(ch);
        notifyItemInserted(coches.size());
    }

    @NonNull
    @Override
    public HolderVentas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_ventas,parent,false);
        return new HolderVentas(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVentas holder, int position) {
        holder.getTxtId().setText(String.valueOf(coches.get(position).get_id()));
        holder.getTxtMarca().setText(String.valueOf(coches.get(position).get_marca()));
        holder.getTxtModelo().setText(String.valueOf(coches.get(position).get_modelo()));
        holder.getTxtColor().setText(String.valueOf(coches.get(position).get_color()));
        holder.getTxtFecha().setText(String.valueOf(coches.get(position).get_fecha()));
        holder.getTxtPrecio().setText(String.valueOf(coches.get(position).get_precio()));
    }

    @Override
    public int getItemCount() {
        return coches.size();
    }
}
