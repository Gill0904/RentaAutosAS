package com.example.proyectofinal.ui.Catalogo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Cliente;
import com.example.proyectofinal.Home;
import com.example.proyectofinal.Informacion;
import com.example.proyectofinal.R;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorCoches extends RecyclerView.Adapter<HolderCoches> {

    List<Coche> coches;
    Context c;
    public AdaptadorCoches(Context cn){
        this.c=cn;
        coches= new ArrayList<>();
    }
    public void AgregarCoche(Coche ch){
        coches.add(ch);
        notifyItemInserted(coches.size());
    }
    @NonNull
    @Override
    public HolderCoches onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_coche,parent,false);
        return new HolderCoches(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCoches holder, final int position) {
        holder.getTxtMarca().setText(String.valueOf(coches.get(position).get_marca()));
        holder.getTxtModelo().setText(String.valueOf(coches.get(position).get_modelo()));
        holder.getBtnComprar().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle= new Bundle();
                bundle.putSerializable("car",coches.get(position));
                Intent i= new Intent(c, Cliente.class);
                i.putExtras(bundle);
                c.startActivity(i);
            }
        });
        holder.getBtnInformacion().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle= new Bundle();
                bundle.putSerializable("car",coches.get(position));
                Intent i= new Intent(c, Informacion.class);
                i.putExtras(bundle);
                c.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coches.size();
    }
}
