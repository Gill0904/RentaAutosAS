package com.example.proyectofinal.ui.Usuarios;

import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.proyectofinal.Conexion;
import com.example.proyectofinal.Home;
import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.Catalogo.AdaptadorCoches;
import com.example.proyectofinal.ui.Catalogo.Catalogo;
import com.example.proyectofinal.ui.Catalogo.Coche;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Usuarios extends Fragment {


    public static Usuarios newInstance() {
        return new Usuarios();
    }
    AdaptadorUsuarios adaptador;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.usuarios_fragment,container,false);
        recyclerView=v.findViewById(R.id.recyclerViewUsuarios);
        adaptador= new AdaptadorUsuarios(getContext());
        LinearLayoutManager linearLayoutManagerl= new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(linearLayoutManagerl);
        if(Home._admin) {
            consultarUsuarios consultUs = new consultarUsuarios();
            consultUs.execute();
        }else{
            Toast.makeText(getContext(),"Error: No cuenta con los privilegios necesarios",Toast.LENGTH_LONG).show();
        }
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    class consultarUsuarios extends AsyncTask<Object, Void, Object> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="ConsultarUsuario";
        public final  String SOAP_ACTION ="http://tempuri.org/ConsultarUsuario";
        Conexion c= new Conexion();
        public final String DIRECCION ="http://"+c.get_ip()+"/ServiciosTAP/Operaciones.asmx";

        private SoapObject solicitud;
        private HttpTransportSE transporte;
        private SoapSerializationEnvelope serializar;
        private Object respuesta;

        @Override
        protected Object doInBackground(Object... objects) {
            solicitud=new SoapObject(NAMESPACE, NOMBRE_METODO);

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
            return  respuesta;
        }

        @Override
        protected void onPostExecute(Object s) {
            super.onPostExecute(s);
            try {
                SoapObject listaUsuarios = (SoapObject) s;
                for (int i = 0; i < listaUsuarios.getPropertyCount(); i++) {

                    SoapObject user = (SoapObject) listaUsuarios.getProperty(i);
                    byte id = Byte.parseByte(user.getProperty(0).toString());
                    String nombre = user.getProperty(1).toString();
                    String contrasena = user.getProperty(2).toString();
                    String rol = user.getProperty(3).toString();
                    Usuario usuario = new Usuario(id,nombre,contrasena,rol);
                    adaptador.AgregarUsuario(usuario);
                }
            }catch (Exception ex){
                Toast.makeText(getContext(),"Error: "+ex.getMessage()+"\n Vuelva a intentarlo",Toast.LENGTH_LONG).show();
            }
        }
    }

}
