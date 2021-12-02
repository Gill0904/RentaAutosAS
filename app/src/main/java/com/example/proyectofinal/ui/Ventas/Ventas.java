package com.example.proyectofinal.ui.Ventas;

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
import com.example.proyectofinal.R;
import com.example.proyectofinal.ui.Catalogo.AdaptadorCoches;
import com.example.proyectofinal.ui.Catalogo.Catalogo;
import com.example.proyectofinal.ui.Catalogo.Coche;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Ventas extends Fragment {

    private VentasViewModel mViewModel;

    public static Ventas newInstance() {
        return new Ventas();
    }
    AdaptadorVentas adaptador;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.ventas_fragment,container,false);
        recyclerView=v.findViewById(R.id.recyclerViewVentas);
        adaptador= new AdaptadorVentas(getContext());
        LinearLayoutManager linearLayoutManagerl= new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(linearLayoutManagerl);
        consultarVentas consV= new consultarVentas();
        consV.execute();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    class consultarVentas extends AsyncTask<Object, Void, Object> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="ConsultarCochesVendidos";
        public final  String SOAP_ACTION ="http://tempuri.org/ConsultarCochesVendidos";
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
                SoapObject listaVentas = (SoapObject) s;
                for (int i = 0; i < listaVentas.getPropertyCount(); i++) {

                    SoapObject coche = (SoapObject) listaVentas.getProperty(i);
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
