package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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


public class Login extends AppCompatActivity {
    EditText txtUsuario,txtPassword,txtRol;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsuario= findViewById(R.id.editUsernameLogin);
        txtPassword=findViewById(R.id.editPasswordLogin);
        btnLogin=findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAdmin loginAdmin=new loginAdmin();
                loginAdmin.execute(txtUsuario.getText().toString());
                loginUsuario loginUsuario= new loginUsuario();
                loginUsuario.execute(txtUsuario.getText().toString(),txtPassword.getText().toString());

            }
        });
    }
    class loginUsuario extends AsyncTask<Object, Void, String> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="Login";
        public final  String SOAP_ACTION ="http://tempuri.org/Login";
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
            user.setName("nombre");
            user.setValue(objects[0].toString());
            user.setType(String.class);
            solicitud.addProperty(user);

            PropertyInfo pass= new PropertyInfo();
            pass.setName("password");
            pass.setValue(objects[1].toString());
            pass.setType(String.class);
            solicitud.addProperty(pass);

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
                Toast.makeText(getApplicationContext(),"Datos Correctos",Toast.LENGTH_SHORT).show();
                Intent i= new Intent(getApplicationContext(),Home.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Datos Incorrectos",Toast.LENGTH_SHORT).show();
            }
        }
    }
    class loginAdmin extends AsyncTask<Object, Void, String> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="loginAdmin";
        public final  String SOAP_ACTION ="http://tempuri.org/loginAdmin";
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
                Home._admin=true;
            }else{
                Home._admin=false;
            }

        }
    }
}
