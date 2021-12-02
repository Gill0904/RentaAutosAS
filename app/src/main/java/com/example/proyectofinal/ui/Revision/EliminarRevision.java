package com.example.proyectofinal.ui.Revision;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinal.Conexion;
import com.example.proyectofinal.Home;
import com.example.proyectofinal.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class EliminarRevision extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eliminarRevision eliminarRevision= new eliminarRevision();
        String valor = getIntent().getStringExtra("id");
        eliminarRevision.execute(Byte.parseByte(valor));
    }
    class eliminarRevision extends AsyncTask<Object, Void, String> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="EliminarRevision";
        public final  String SOAP_ACTION ="http://tempuri.org/EliminarRevision";
        Conexion c= new Conexion();
        public final String DIRECCION ="http://"+c.get_ip()+"/ServiciosTAP/Operaciones.asmx";

        private SoapObject solicitud;
        private HttpTransportSE transporte;
        private SoapSerializationEnvelope serializar;
        private Object respuesta;

        @Override
        protected String doInBackground(Object... objects) {
            solicitud=new SoapObject(NAMESPACE, NOMBRE_METODO);
            PropertyInfo id= new PropertyInfo();
            id.setName("_id");
            id.setValue(objects[0].toString());
            id.setType(String.class);
            solicitud.addProperty(id);

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
                Toast.makeText(getApplicationContext(),"Revision Eliminada",Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getApplicationContext(), Home.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Error al eliminar",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
