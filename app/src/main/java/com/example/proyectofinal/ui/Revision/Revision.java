package com.example.proyectofinal.ui.Revision;

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
import com.example.proyectofinal.ui.Catalogo.Coche;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class Revision extends Fragment {

    private RevisionViewModel mViewModel;

    public static Revision newInstance() {
        return new Revision();
    }
    AdaptadorRevision adaptadorRevision;
    RecyclerView recyclerViewR;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.revision_fragment,container,false);
        recyclerViewR=v.findViewById(R.id.recyclerViewRevision);
        adaptadorRevision= new AdaptadorRevision(getContext());
        LinearLayoutManager linearLayoutManagerl= new LinearLayoutManager(getContext());
        recyclerViewR.setAdapter(adaptadorRevision);
        recyclerViewR.setLayoutManager(linearLayoutManagerl);
        consultarRevisiones consultarRevisiones= new consultarRevisiones();
        consultarRevisiones.execute();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    class consultarRevisiones extends AsyncTask<Object, Void, Object> {
        public final String NAMESPACE="http://tempuri.org/";
        public final String NOMBRE_METODO="ConsultarRevisiones";
        public final  String SOAP_ACTION ="http://tempuri.org/ConsultarRevisiones";
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
                SoapObject listaRevisiones = (SoapObject) s;
                for (int i = 0; i < listaRevisiones.getPropertyCount(); i++) {
                    SoapObject revision = (SoapObject) listaRevisiones.getProperty(i);
                    byte id = Byte.parseByte(revision.getProperty(0).toString());
                    int numeroRevision = Integer.parseInt(revision.getProperty(1).toString());
                    String matricula = revision.getProperty(2).toString();
                    String marca = revision.getProperty(3).toString();
                    String modelo = revision.getProperty(4).toString();
                    String cambioAceite = revision.getProperty(5).toString();
                    String cambioFiltro = revision.getProperty(6).toString();
                    String cambioFrenos = revision.getProperty(7).toString();
                    String comentarios = revision.getProperty(8).toString();
                    String estadoRevision = revision.getProperty(9).toString();
                    String FechaRevision = revision.getProperty(10).toString();
                    Revisiones revisiones = new Revisiones(id, numeroRevision, matricula, marca, modelo, cambioAceite, cambioFiltro, cambioFrenos
                            , comentarios, estadoRevision, FechaRevision);
                    adaptadorRevision.AgregarRevisionM(revisiones);

                }
            }catch (Exception ex){
                Toast.makeText(getContext(),"Error: "+ex.getMessage()+"\n Vuelva a intentarlo",Toast.LENGTH_LONG).show();

            }
        }
    }
}
