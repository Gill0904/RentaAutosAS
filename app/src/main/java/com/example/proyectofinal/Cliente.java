package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.ui.Catalogo.Coche;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Cliente extends AppCompatActivity {
    EditText txtNombre,txtApepat,txtApemat,txtTelefono,txtDireccion,txtCodigoPostal;
    Button btnComprar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        txtNombre=findViewById(R.id.editNombreCliente);
        txtApepat=findViewById(R.id.editApepatCliente);
        txtApemat= findViewById(R.id.editApematCliente);
        txtTelefono=findViewById(R.id.editTelefonoCliente);
        txtDireccion=findViewById(R.id.editDireccionCliente);
        txtCodigoPostal=findViewById(R.id.editCodigoPostalCliente);
        btnComprar=findViewById(R.id.btnComprar);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarCliente agregarCliente= new AgregarCliente();
                agregarCliente.execute(txtNombre.getText().toString(),txtApepat.getText().toString(),
                        txtApemat.getText().toString(),txtTelefono.getText().toString(),txtDireccion.getText().toString(),
                        txtCodigoPostal.getText().toString());

            }
        });
    }
    class AgregarCliente extends AsyncTask<Object, Void, String> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="AgregarCliente";
        public final  String SOAP_ACTION ="http://tempuri.org/AgregarCliente";
        Conexion c= new Conexion();
        public final String DIRECCION ="http://"+c.get_ip()+"/ServiciosTAP/Operaciones.asmx";

        private SoapObject solicitud;
        private HttpTransportSE transporte;
        private SoapSerializationEnvelope serializar;
        private Object respuesta;




        @Override
        protected String doInBackground(Object... objects) {
            solicitud=new SoapObject(NAMESPACE, NOMBRE_METODO);
            PropertyInfo user= new PropertyInfo();
            user.setName("_nombre");
            user.setValue(objects[0].toString());
            user.setType(String.class);
            solicitud.addProperty(user);

            PropertyInfo apepat= new PropertyInfo();
            apepat.setName("_apepat");
            apepat.setValue(objects[1].toString());
            apepat.setType(String.class);
            solicitud.addProperty(apepat);

            PropertyInfo apemat= new PropertyInfo();
            apemat.setName("_apemat");
            apemat.setValue(objects[2].toString());
            apemat.setType(String.class);
            solicitud.addProperty(apemat);

            PropertyInfo telefono= new PropertyInfo();
            telefono.setName("_telefono");
            telefono.setValue(objects[3].toString());
            telefono.setType(String.class);
            solicitud.addProperty(telefono);

            PropertyInfo direccion= new PropertyInfo();
            direccion.setName("_direccion");
            direccion.setValue(objects[4].toString());
            direccion.setType(String.class);
            solicitud.addProperty(direccion);

            PropertyInfo codigoPostal= new PropertyInfo();
            codigoPostal.setName("_codigoPostal");
            codigoPostal.setValue(objects[5].toString());
            codigoPostal.setType(int.class);
            solicitud.addProperty(codigoPostal);

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
                Toast.makeText(getApplicationContext(),"Agregado Correctamente",Toast.LENGTH_LONG).show();

                    mandarCoche();
            }else{
                Toast.makeText(getApplicationContext(),"Error al agregar el cliente",Toast.LENGTH_LONG).show();
            }
        }
    }
    public void mandarCoche(){

        Bundle cocheEnviado= getIntent().getExtras();
        Coche coche=null;
        if (cocheEnviado!=null){
            coche=(Coche) cocheEnviado.getSerializable("car");
        }
        final Coche finalCoche = coche;
        Bundle bundle= new Bundle();
        bundle.putSerializable("carro", finalCoche);
        Intent i= new Intent(getApplicationContext(), Cotizacion.class);
        i.putExtras(bundle);
        startActivity(i);
    }
}
