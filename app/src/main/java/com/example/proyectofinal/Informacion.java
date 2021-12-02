package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal.ui.Catalogo.Coche;

public class Informacion extends AppCompatActivity {

    TextView txtId,txtMarca,txtModelo,txtColor,txtPrecio;
    ImageView imagenCoche;
    Byte id=0;
    String matricula="";
    String marca="";
    String modelo="";
    String color="";
    String plazo =  "" ;
    String fecha="";
    double precio=0;
    Coche coche=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        txtId= findViewById(R.id.txtId);
        txtMarca=findViewById(R.id.txtMarca);
        txtModelo=findViewById(R.id.txtModelo);
        txtColor=findViewById(R.id.txtColor);
        txtPrecio=findViewById(R.id.txtPrecio);
        imagenCoche=findViewById(R.id.imagenCoche);

        Bundle cocheEnviado= getIntent().getExtras();

        if (cocheEnviado!=null){
            coche=(Coche) cocheEnviado.getSerializable("car");
            matricula=coche.get_matricula();
            marca=coche.get_marca();
            modelo=coche.get_modelo();
            color=coche.get_color();
            plazo =coche.get_plazo();
            precio=Double.parseDouble(coche.get_precio().toString());
            id= Byte.parseByte(String.valueOf(coche.get_id()));
            fecha=coche.get_fecha();

            txtModelo.setText(modelo);
            txtMarca.setText(marca);
            txtColor.setText(color);
            txtPrecio.setText(String.valueOf(precio));
            txtId.setText(String.valueOf(id));
        }
    }
}
