package com.easysitp.easysitp.ui.rutaactual;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.easysitp.easysitp.R;
import com.easysitp.easysitp.Route;
import com.easysitp.easysitp.ui.publicaciones.PublicacionesFragment;
import com.easysitp.easysitp.utils.JSONParser;
import com.easysitp.easysitp.viaje.Bus;
import com.easysitp.easysitp.viaje.Parada;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RutaActualFragment extends Fragment implements
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMarkerClickListener {

    View vista;
    Bitmap smallMarker;
    BitmapDescriptor smallMarkerIcon;
    Bitmap smallMarkerBus;
    BitmapDescriptor smallMarkerIconBus;
    TextView numeroRuta;
    TextView nombreRuta;
    TextView hora;
    TextView tiempo;
    Marker markerParada;
    Marker markerBus;
    ConstraintLayout botonChat;
    private GoogleMap mMap;

    @Override
    public boolean onMarkerClick(final Marker marker) {

        return false;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ruta_actual, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        numeroRuta = vista.findViewById(R.id.numero_ruta);
        nombreRuta = vista.findViewById(R.id.nombre_ruta);
        hora = vista.findViewById(R.id.hora_llegada);
        tiempo = vista.findViewById(R.id.minutos_restantes);
        botonChat = vista.findViewById(R.id.botonChat);


        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            final String nRuta = datosRecuperados.getString("nRuta");
            Parada parada = (Parada) datosRecuperados.getSerializable("parada");

            botonChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle datos = new Bundle();
                    datos.putString("RUTA", nRuta);
                    Fragment fragmento = new PublicacionesFragment();
                    fragmento.setArguments(datos);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragmento);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();
                }
            });

        }

        return vista;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMarkerClickListener(this);

        LatLng latLng = new LatLng(4.634450, -74.082563);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));

        int height = 120;
        int width = 100;
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.marcador_parada);
        smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);

        Bitmap mbus = BitmapFactory.decodeResource(getResources(), R.drawable.bus);
        smallMarkerBus = Bitmap.createScaledBitmap(mbus, 80, 120, false);
        smallMarkerIconBus = BitmapDescriptorFactory.fromBitmap(smallMarkerBus);

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            String nRuta = datosRecuperados.getString("nRuta");
            Parada parada = (Parada) datosRecuperados.getSerializable("parada");
            String noRuta = datosRecuperados.getString("noRuta");

            nombreRuta.setText(noRuta);
            numeroRuta.setText(nRuta);

            markerParada = mMap.addMarker(new MarkerOptions()
                    .position(parada.getCoordenadas())
                    .title(parada.getNombre())
                    .icon(smallMarkerIcon));

            markerBus = mMap.addMarker(new MarkerOptions()
                    .position(parada.getCoordenadas())
                    .icon(smallMarkerIconBus));


            getdatos(nRuta, parada, tiempo, hora);
        }
    }

    public void getdatos(String numeroRuta, final Parada parada, final TextView texv, final TextView hora) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("ubicacion_buses").child(numeroRuta);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Bus bus = snapshot.getValue(Bus.class);
                    Route route = new Route();
                    LatLng busCoor = new LatLng(bus.latitud, bus.longitud);
                    route.drawRoute(mMap, getContext(), busCoor, parada.getCoordenadas(), "es");
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

                    mMap.clear();
                    markerParada = mMap.addMarker(new MarkerOptions()
                            .position(parada.getCoordenadas())
                            .title(parada.getNombre())
                            .icon(smallMarkerIcon));

                    markerBus = mMap.addMarker(new MarkerOptions()
                            .position(busCoor)
                            .icon(smallMarkerIconBus));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        };
        databaseReference.addValueEventListener(valueEventListener);
    }

}
