package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.ui.Catalogo.Coche;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Cotizacion extends AppCompatActivity {
    TextView txtId,txtMarca,txtModelo,txtExtra,txtTotal;
    ImageView imagenCoche,imagenAcce;
    Spinner sColor,sPlazo;
    CheckBox cbSonido,cbRines,cbAsientos,cbLed;
    Button btnComprar;
    Byte id=0;
    String matricula="";
    String marca="";
    String modelo="";
    String color="";
    String plazo =  "" ;
    double precio=0;
    Coche coche=null;

    double total=0;
    double extra=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cotizacion);
        txtId= findViewById(R.id.txtId);
        txtMarca=findViewById(R.id.txtMarca);
        txtModelo=findViewById(R.id.txtModelo);
        txtExtra= findViewById(R.id.txtExtra);
        txtTotal=findViewById(R.id.txtTotal);
        imagenCoche=findViewById(R.id.imagenCoche);
        imagenAcce=findViewById(R.id.imagenAcce);
        sColor=findViewById(R.id.sColor);
        sPlazo=findViewById(R.id.sPlazo);
        cbAsientos=findViewById(R.id.cbasientos);
        cbLed=findViewById(R.id.cbLed);
        cbRines=findViewById(R.id.cbRines);
        cbSonido=findViewById(R.id.cbSonido);
        btnComprar=findViewById(R.id.btnComprarCarro);

        Bundle cocheEnviado= getIntent().getExtras();

        if (cocheEnviado!=null){
            coche=(Coche) cocheEnviado.getSerializable("carro");
            matricula=coche.get_matricula();
            marca=coche.get_marca();
            modelo=coche.get_modelo();
            color=coche.get_color();
            plazo =coche.get_plazo();
            precio=Double.parseDouble(coche.get_precio().toString());
            id= Byte.parseByte(String.valueOf(coche.get_id()));

            txtModelo.setText(modelo);
            txtMarca.setText(marca);
            txtId.setText(String.valueOf(id));
        }

        final String finalMatricula = matricula;
        final String finalMarca = marca;
        final String finalModelo = modelo;
        final String finalColor = color;
        final String finalPlazo = plazo;
        final double finalPrecio = precio;
        plazo="CONTADO";
        total=precio;
        cbSonido.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbSonido.isChecked())
                {
                    total += 300;
                    extra+=300;
                }
                else
                {
                    total -= 300;
                    extra -= 300;
                }
                calcular();
            }
        });
        cbLed.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbLed.isChecked()) {
                    total += 200;
                    extra += 200;
                } else {
                    total -= 200;
                    extra -= 200;
                }
                calcular();
            }
        });
        cbRines.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbRines.isChecked())
                {
                    total += 100;
                    extra +=100;
                }
                else
                {
                    total -= 100;
                    extra -=100;
                }
                calcular();
            }
        });
        cbAsientos.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbAsientos.isChecked())
                {
                    total += 600;
                    extra+=600;
                }
                else
                {
                    total -= 600;
                    extra-=600;
                }
                calcular();
            }
        });

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_color,
                android.R.layout.simple_spinner_item);
        sColor.setAdapter(adapter);
        sColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calcular();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        ArrayAdapter<CharSequence> adapter2=ArrayAdapter.createFromResource(this,R.array.combo_plazo,
                android.R.layout.simple_spinner_item);
        sPlazo.setAdapter(adapter2);
        sPlazo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                plazo=adapterView.getItemAtPosition(i).toString();
                calcular();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
                AgregarCocheVendido agregarCocheVendido = new AgregarCocheVendido();
                agregarCocheVendido.execute(finalMatricula, finalMarca, finalModelo, finalColor, plazo, finalPrecio);
            }
        });
    }
    class AgregarCocheVendido extends AsyncTask<Object, Void, String> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="AgregarCocheVendido";
        public final  String SOAP_ACTION ="http://tempuri.org/AgregarCocheVendido";
        Conexion c= new Conexion();
        public final String DIRECCION ="http://"+c.get_ip()+"/ServiciosTAP/Operaciones.asmx";

        private SoapObject solicitud;
        private HttpTransportSE transporte;
        private SoapSerializationEnvelope serializar;
        private Object respuesta;

        @Override
        protected String doInBackground(Object... objects) {
            solicitud=new SoapObject(NAMESPACE, NOMBRE_METODO);
            PropertyInfo matricula= new PropertyInfo();
            matricula.setName("_matricula");
            matricula.setValue(objects[0].toString());
            matricula.setType(String.class);
            solicitud.addProperty(matricula);

            PropertyInfo marca= new PropertyInfo();
            marca.setName("_marca");
            marca.setValue(objects[1].toString());
            marca.setType(String.class);
            solicitud.addProperty(marca);

            PropertyInfo nodelo= new PropertyInfo();
            nodelo.setName("_modelo");
            nodelo.setValue(objects[2].toString());
            nodelo.setType(String.class);
            solicitud.addProperty(nodelo);

            PropertyInfo color= new PropertyInfo();
            color.setName("_color");
            color.setValue(objects[3].toString());
            color.setType(String.class);
            solicitud.addProperty(color);

            PropertyInfo plazo= new PropertyInfo();
            plazo.setName("_plazo");
            plazo.setValue(objects[4].toString());
            plazo.setType(String.class);
            solicitud.addProperty(plazo);

            PropertyInfo precio= new PropertyInfo();
            precio.setName("_precio");
            precio.setValue(objects[5].toString());
            precio.setType(double.class);
            solicitud.addProperty(precio);

            serializar= new SoapSerializationEnvelope(SoapEnvelope.VER11);
            serializar.dotNet=true;
            serializar.setOutputSoapObject(solicitud);

            transporte= new HttpTransportSE(DIRECCION);

            try {
                transporte.call(SOAP_ACTION, serializar);
                respuesta=serializar.getResponse();
            }catch (Exception ex){
                respuesta= ex.getMessage();
            }
            return  respuesta.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("true")) {
                Toast.makeText(getApplicationContext(),"Venta EXITOSA!!",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Error al registrar la venta",Toast.LENGTH_LONG).show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void calcular(){
        txtExtra.setText ("" + extra);
        txtTotal.setText ( "" + total);
    }
}
