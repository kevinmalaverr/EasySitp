package com.easysitp.easysitp.ui.Inicio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.easysitp.easysitp.PermissionUtils;
import com.easysitp.easysitp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class InicioFragment extends Fragment implements
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnMarkerClickListener {

    private InicioViewModel inicioViewModel;

    // maps
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    Button botonGps;
    ConstraintLayout barraInferior; //barra inferior pequeña
    // interfaz
    View vista;  // permite accceder al fragment
    private boolean mPermissionDenied = false;
    private GoogleMap mMap;


    // markers
    private static final LatLng EST_CONCHA = new LatLng(4.638546, -74.087559);
    private static final LatLng EST_CYT = new LatLng(4.638306, -74.084881);
    private static final LatLng EST_CADE = new LatLng(4.637884, -74.081530);
    private static final LatLng EST_SINDU = new LatLng(4.635797, -74.080880);
    private static final LatLng EST_ENFERMERIA = new LatLng(4.635088, -74.085218);

    private Marker estConcha;
    private Marker estCyt;
    private Marker estCade;
    private Marker estSindu;
    private Marker estEnfermeria;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inicioViewModel = ViewModelProviders.of(this).get(InicioViewModel.class);

        vista = inflater.inflate(R.layout.fragment_inicio, container, false);

        barraInferior = vista.findViewById(R.id.barra_inferior);
        botonGps = vista.findViewById(R.id.boton_gps);

        barraInferior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_inicio_to_nav_listarutas);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return vista;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setOnMarkerClickListener(this);
        enableMyLocation();


        int height = 100;
        int width = 80;
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.marcador_parada);
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        BitmapDescriptor smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker);


        estConcha = mMap.addMarker(new MarkerOptions()
                .position(EST_CONCHA)
                .title("Concha Acustica")
                .icon(smallMarkerIcon));
        estConcha.setTag(0);

        estCyt = mMap.addMarker(new MarkerOptions()
                .position(EST_CYT)
                .title("CyT")
                .icon(smallMarkerIcon));
        estCyt.setTag(0);

        estCade = mMap.addMarker(new MarkerOptions()
                .position(EST_CADE)
                .title("CADE")
                .icon(smallMarkerIcon));
        estCade.setTag(0);


        estSindu = mMap.addMarker(new MarkerOptions()
                .position(EST_SINDU)
                .title("Sindu")
                .icon(smallMarkerIcon));
        estSindu.setTag(0);

        estEnfermeria = mMap.addMarker(new MarkerOptions()
                .position(EST_ENFERMERIA)
                .title("Facultad de Enfermieria")
                .icon(smallMarkerIcon));
        estEnfermeria.setTag(0);


    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission((AppCompatActivity) getActivity(), LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(getActivity(), "MyLocation button clicked", Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(getActivity(), "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    protected void onResumeFragments() {
        this.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true).show(getChildFragmentManager(), "dialog");
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        barraInferior.setVisibility(View.VISIBLE);


        return false;
    }
}
