package com.example.proyectofinal.ui.Revision;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.Conexion;
import com.example.proyectofinal.Home;
import com.example.proyectofinal.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AgregarRevision extends AppCompatActivity {
    EditText txtNumeroRevision,txtMatricula,txtMarca,txtModelo,txtCambioAceite,txtCambioFiltro,txtCambioFrenos,txtComentarios
            ,txtEstadoRevision,txtFechaRevision;
    Button btnAgregarRevision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_revision);
        txtNumeroRevision = findViewById(R.id.txtNumeroRevisionA);
        txtMatricula = findViewById(R.id.txtMatriculaA);
        txtMarca = findViewById(R.id.txtMarcaA);
        txtModelo = findViewById(R.id.txtModeloA);
        txtCambioAceite = findViewById(R.id.txtCambioAceiteA);
        txtCambioFiltro = findViewById(R.id.txtCambioFiltroA);
        txtCambioFrenos = findViewById(R.id.txtCambioFrenosA);
        txtComentarios = findViewById(R.id.txtComentariosA);
        txtEstadoRevision = findViewById(R.id.txtEstadoRevisionA);
        btnAgregarRevision = findViewById(R.id.btnAgregarRevisionF);
        btnAgregarRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarRevisiones agregarRevisiones= new AgregarRevisiones();
                if (txtNumeroRevision.getText().toString().equals("")||txtMatricula.getText().toString().equals("")||
                        txtMarca.getText().toString().equals("")||txtModelo.getText().toString().equals("")||
                        txtCambioAceite.getText().toString().equals("")||txtCambioFiltro.getText().toString().equals("")||
                        txtCambioFrenos.getText().toString().equals("")||txtComentarios.getText().toString().equals("")
                ||txtEstadoRevision.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Datos Faltantes",Toast.LENGTH_SHORT).show();
                }else{
                    agregarRevisiones.execute(txtNumeroRevision.getText().toString(), txtMatricula.getText().toString(), txtMarca.getText().toString(),
                            txtModelo.getText().toString(), txtCambioAceite.getText().toString(), txtCambioFiltro.getText().toString(),
                            txtCambioFrenos.getText().toString(), txtComentarios.getText().toString(), txtEstadoRevision.getText().toString());
                }

            }
        });
    }
    class AgregarRevisiones extends AsyncTask<Object, Void, String> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="AgregarRevision";
        public final  String SOAP_ACTION ="http://tempuri.org/AgregarRevision";
        Conexion c= new Conexion();
        public final String DIRECCION ="http://"+ c.get_ip() +"/ServiciosTAP/Operaciones.asmx";

        private SoapObject solicitud;
        private HttpTransportSE transporte;
        private SoapSerializationEnvelope serializar;
        private Object respuesta;




        @Override
        protected String doInBackground(Object... objects) {
            solicitud=new SoapObject(NAMESPACE, NOMBRE_METODO);
            PropertyInfo numeroRevision= new PropertyInfo();
            numeroRevision.setName("_numeroRevision");
            numeroRevision.setValue(objects[0].toString());
            numeroRevision.setType(int.class);
            solicitud.addProperty(numeroRevision);

            PropertyInfo matricula= new PropertyInfo();
            matricula.setName("_matricula");
            matricula.setValue(objects[1].toString());
            matricula.setType(String.class);
            solicitud.addProperty(matricula);

            PropertyInfo marca= new PropertyInfo();
            marca.setName("_marca");
            marca.setValue(objects[2].toString());
            marca.setType(String.class);
            solicitud.addProperty(marca);

            PropertyInfo modelo= new PropertyInfo();
            modelo.setName("_modelo");
            modelo.setValue(objects[3].toString());
            modelo.setType(String.class);
            solicitud.addProperty(modelo);

            PropertyInfo cambioAceite= new PropertyInfo();
            cambioAceite.setName("_cambioAceite");
            cambioAceite.setValue(objects[4].toString());
            cambioAceite.setType(String.class);
            solicitud.addProperty(cambioAceite);

            PropertyInfo cambioFiltro= new PropertyInfo();
            cambioFiltro.setName("_cambioFiltro");
            cambioFiltro.setValue(objects[5].toString());
            cambioFiltro.setType(String.class);
            solicitud.addProperty(cambioFiltro);

            PropertyInfo cambioFrenos= new PropertyInfo();
            cambioFrenos.setName("_cambioFrenos");
            cambioFrenos.setValue(objects[6].toString());
            cambioFrenos.setType(String.class);
            solicitud.addProperty(cambioFrenos);

            PropertyInfo comentarios= new PropertyInfo();
            comentarios.setName("_comentarios");
            comentarios.setValue(objects[7].toString());
            comentarios.setType(String.class);
            solicitud.addProperty(comentarios);

            PropertyInfo estadoRevision= new PropertyInfo();
            estadoRevision.setName("_estadoRevision");
            estadoRevision.setValue(objects[8].toString());
            estadoRevision.setType(String.class);
            solicitud.addProperty(estadoRevision);

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
                Toast.makeText(getApplicationContext(),"Agregada Correctamente",Toast.LENGTH_LONG).show();
                Intent i= new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Error al agregar la revision",Toast.LENGTH_LONG).show();
            }
        }
    }
}
