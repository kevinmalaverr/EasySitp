package com.easysitp.easysitp.ui.Inicio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.easysitp.easysitp.R;

public class InicioFragment extends Fragment {

    private InicioViewModel inicioViewModel;

    View vista;  // permite accceder al fragment
    Button botonGps;
    ConstraintLayout barraInferior; //barra inferior pequeña


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inicioViewModel = ViewModelProviders.of(this).get(InicioViewModel.class);

        vista = inflater.inflate(R.layout.fragment_inicio, container, false);
        botonGps = vista.findViewById(R.id.boton_gps);
        barraInferior = vista.findViewById(R.id.barra_inferior);


        botonGps.setOnClickListener(new View.OnClickListener() { // evento click de botonGps
            @Override
            public void onClick(View v) {
                barraInferior.setVisibility(View.VISIBLE);
                botonGps.setVisibility(View.INVISIBLE);
                // aca iria el metodo para obtener la ubicacion gps y ubicarla en el mapa
            }
        });

        barraInferior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_inicio_to_nav_listarutas);
            }
        });


        return vista;

    }




}