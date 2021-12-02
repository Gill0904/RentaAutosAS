package com.example.proyectofinal.ui.Usuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.Conexion;
import com.example.proyectofinal.Home;
import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.Revision.AgregarRevision;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AgregarUsuario extends AppCompatActivity {
    EditText txtnombre,txtContrasena,txtConfContrasena,txtRol;
    Button btnAgregarUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        txtnombre = findViewById(R.id.txtNombreUS);
        txtRol = findViewById(R.id.txtRolUs);
        txtContrasena = findViewById(R.id.txtContrasena);
        txtConfContrasena = findViewById(R.id.txtConfContrasena);
        btnAgregarUser = findViewById(R.id.btnAgregarUser);
        btnAgregarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarUs agregarUs= new AgregarUs();
                if (txtConfContrasena.getText().toString().equals("")||txtContrasena.getText().toString().equals("")
                        ||txtnombre.getText().toString().equals("")||txtRol.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Datos Faltantes",Toast.LENGTH_SHORT).show();
                }else{
                    if(txtContrasena.getText().toString().equals(txtConfContrasena.getText().toString())) {
                        agregarUs.execute(txtnombre.getText().toString(),txtContrasena.getText().toString(),txtRol.getText().toString());
                    }else{
                        Toast.makeText(getApplicationContext(),"Las credenciales no coinciden",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
class AgregarUs extends AsyncTask<Object, Void, String> {
    public final String NAMESPACE="http://tempuri.org/";
    public final String NOMBRE_METODO="Agregar";
    public final  String SOAP_ACTION ="http://tempuri.org/Agregar";
    Conexion c= new Conexion();
    public final String DIRECCION ="http://"+c.get_ip()+"/ServiciosTAP/Operaciones.asmx";

    private SoapObject solicitud;
    private HttpTransportSE transporte;
    private SoapSerializationEnvelope serializar;
    private Object respuesta;




    @Override
    protected String doInBackground(Object... objects) {
        solicitud=new SoapObject(NAMESPACE, NOMBRE_METODO);
        PropertyInfo nombre= new PropertyInfo();
        nombre.setName("_nombre");
        nombre.setValue(objects[0].toString());
        nombre.setType(String.class);
        solicitud.addProperty(nombre);

        PropertyInfo contrasena= new PropertyInfo();
        contrasena.setName("_contrasena");
        contrasena.setValue(objects[1].toString());
        contrasena.setType(String.class);
        solicitud.addProperty(contrasena);

        PropertyInfo rol= new PropertyInfo();
        rol.setName("_rol");
        rol.setValue(objects[2].toString());
        rol.setType(String.class);
        solicitud.addProperty(rol);

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
            Toast.makeText(getApplicationContext(),"Usuario Agregado Correctamente",Toast.LENGTH_LONG).show();
            Intent i= new Intent(getApplicationContext(), Home.class);
            startActivity(i);
        }else{
            Toast.makeText(getApplicationContext(),"Error al agregar el usuario",Toast.LENGTH_LONG).show();
        }
    }
}
}
