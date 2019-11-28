package com.easysitp.easysitp.ui.listarutas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.easysitp.easysitp.R;

public class ListaRutasFragment extends Fragment {

    View vista;
    View caja;
    Button botonVolver;
    ViewGroup contenedor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_listarutas, container, false);
        botonVolver = vista.findViewById(R.id.boton_volver);
        contenedor = vista.findViewById(R.id.contenedor);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_listarutas_to_nav_inicio);
            }
        });

        addLayout();
        addLayout();
        addLayout();

        return vista;
    }

    private void addLayout() {
        caja = LayoutInflater.from(getContext()).inflate(R.layout.caja_ruta, contenedor, false);

        contenedor.addView(caja);
    }


}