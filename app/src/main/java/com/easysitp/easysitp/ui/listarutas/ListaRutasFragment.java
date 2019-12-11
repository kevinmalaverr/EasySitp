package com.easysitp.easysitp.ui.listarutas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.easysitp.easysitp.R;
import com.easysitp.easysitp.Route;
import com.easysitp.easysitp.ui.rutaactual.RutaActualFragment;
import com.easysitp.easysitp.utils.JSONParser;
import com.easysitp.easysitp.viaje.Bus;
import com.easysitp.easysitp.viaje.Parada;
import com.easysitp.easysitp.viaje.Ruta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListaRutasFragment extends Fragment {

    View vista;
    View caja;
    Button botonVolver;
    ViewGroup contenedor;
    public String vel = "0";
    public String str;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_listarutas, container, false);
        botonVolver = vista.findViewById(R.id.boton_volver);
        contenedor = vista.findViewById(R.id.contenedor);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            Parada parada = (Parada) datosRecuperados.getSerializable("PARADA");
            addcaja(parada);

        }


        return vista;
    }

    private void addcaja(final Parada parada) {
        for (int i = 0; i < parada.getRutas().size(); i++) {
            final Ruta ruta = parada.getRutas().get(i);

            caja = LayoutInflater.from(getContext()).inflate(R.layout.caja_ruta, contenedor, false);
            TextView nombreRuta = caja.findViewById(R.id.nombre_ruta);
            TextView numeroRuta = caja.findViewById(R.id.numero_ruta);
            TextView horaLlegada = caja.findViewById(R.id.hora_llegada);
            TextView minutosRestantes = caja.findViewById(R.id.minutos_restantes);

            nombreRuta.setText(ruta.getNombreRuta());
            numeroRuta.setText(ruta.getNumeroRuta());
            getdatos(ruta.getNumeroRuta(), parada, minutosRestantes, horaLlegada);
            caja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle datosAEnviar = new Bundle();
                    datosAEnviar.putString("nRuta", ruta.getNumeroRuta());
                    datosAEnviar.putString("noRuta", ruta.getNombreRuta());
                    datosAEnviar.putSerializable("parada", parada);
                    Fragment fragmento = new RutaActualFragment();
                    fragmento.setArguments(datosAEnviar);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.abajo, R.anim.salir);
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragmento);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            });


            contenedor.addView(caja);
        }
    }

    public void getdatos(String numeroRuta, final Parada parada, final TextView texv, final TextView hora) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("ubicacion_buses").child(numeroRuta);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Bus> arrayListBus = new ArrayList<>();
                String nombreBus = null;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Bus busA = snapshot.getValue(Bus.class);
                    nombreBus = snapshot.getKey();
                    String ult = busA.ultimaParada;
                    if (ult != parada.getNombre()) {
                        arrayListBus.add(busA);
                        break;
                    }
                }

                final String nBus = nombreBus;

                if (arrayListBus.size() != 0) {

                    final Bus bus = arrayListBus.get(0);
                    Route route = new Route();

                    final double velocidad = bus.velocidad;
                    final String url = route.makeURL(bus.latitud, bus.longitud, parada.getCoordenadas().latitude, parada.getCoordenadas().longitude, null);

                    new Thread(new Runnable() {
                        public void run() {
                            JSONParser jParser = new JSONParser();
                            String json = jParser.getJSONFromUrl(url);
                            String d = Route.getDistancia(json);
                            double distancia = Double.parseDouble(d);
                            double tiempo = distancia / velocidad;
                            Date a = new Date();
                            a.setTime(System.currentTimeMillis() + (((long) tiempo) * 60 * 1000));
                            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

                            texv.setText(String.valueOf((int) tiempo));
                            hora.setText(sdf.format(a));
                        }
                    }).start();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        databaseReference.addValueEventListener(valueEventListener);
    }
}