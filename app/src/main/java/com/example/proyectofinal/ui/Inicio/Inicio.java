package com.example.proyectofinal.ui.Inicio;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyectofinal.Home;
import com.example.proyectofinal.Login;
import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.Revision.AgregarRevision;
import com.example.proyectofinal.ui.Revision.Revision;
import com.example.proyectofinal.ui.Usuarios.AgregarUsuario;

public class Inicio extends Fragment {

    Button btnAgregarUsuario,btnAgregarRevision;

    public static Inicio newInstance() {
        return new Inicio();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.inicio_fragment, container, false);
        btnAgregarRevision= v.findViewById(R.id.btnAgregarRevisionI);

        btnAgregarRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(), AgregarRevision.class);
                startActivity(i);
            }
        });
        btnAgregarUsuario= v.findViewById(R.id.btnAgregarUsuarioI);

        if (!Home._admin){
            btnAgregarUsuario.setEnabled(false);
        }
        btnAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(), AgregarUsuario.class);
                startActivity(i);
            }
        });
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
