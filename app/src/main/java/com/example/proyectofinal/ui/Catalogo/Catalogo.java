package com.example.proyectofinal.ui.Catalogo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.Conexion;
import com.example.proyectofinal.R;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Catalogo extends Fragment {

    private CatalogoViewModel mViewModel;

    public static Catalogo newInstance() {
        return new Catalogo();
    }

    AdaptadorCoches adaptador;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.catalogo_fragment,container,false);
        recyclerView=v.findViewById(R.id.recyclerView);
        adaptador= new AdaptadorCoches(getContext());
        LinearLayoutManager linearLayoutManagerl= new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(linearLayoutManagerl);
        consultarCoches consultarcoches= new consultarCoches();
        consultarcoches.execute();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    class consultarCoches extends AsyncTask<Object, Void, Object> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="ConsultarCoches";
        public final  String SOAP_ACTION ="http://tempuri.org/ConsultarCoches";
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
                SoapObject listaCoches = (SoapObject) s;
                for (int i = 0; i < listaCoches.getPropertyCount(); i++) {

                    SoapObject coche = (SoapObject) listaCoches.getProperty(i);
                    String fecha = coche.getProperty(0).toString();
                    byte id = Byte.parseByte(coche.getProperty(1).toString());
                    String matricula = coche.getProperty(2).toString();
                    String marca = coche.getProperty(3).toString();
                    String modelo = coche.getProperty(4).toString();
                    String color = coche.getProperty(5).toString();
                    Double precio = Double.parseDouble(coche.getProperty(6).toString());
                    String plazo = coche.getProperty(7).toString();
                    String imagen = coche.getProperty(8).toString();
                    Coche coche1 = new Coche(id, matricula, marca, modelo, color, precio, plazo, imagen, fecha);
                    adaptador.AgregarCoche(coche1);
                }
            }catch (Exception ex){
                Toast.makeText(getContext(),"Error: "+ex.getMessage()+"\n Vuelva a intentarlo",Toast.LENGTH_LONG).show();
            }
        }
    }
}
