package com.easysitp.easysitp.ui.listarutas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.easysitp.easysitp.Parada;
import com.easysitp.easysitp.R;
import com.easysitp.easysitp.Ruta;

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

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados != null) {
            Parada parada = (Parada) datosRecuperados.getSerializable("PARADA");
            addcaja(parada);

        }

        return vista;
    }

    private void addcaja(Parada parada) {
        for (int i = 0; i < parada.getRutas().size(); i++) {
            Ruta ruta = parada.getRutas().get(i);
            caja = LayoutInflater.from(getContext()).inflate(R.layout.caja_ruta, contenedor, false);
            TextView nombreRuta = caja.findViewById(R.id.nombre_ruta);
            TextView numeroRuta = caja.findViewById(R.id.numero_ruta);
            TextView horaLlegada = caja.findViewById(R.id.hora_llegada);
            TextView minutosRestantes = caja.findViewById(R.id.minutos_restantes);

            nombreRuta.setText(ruta.getNombreRuta());
            numeroRuta.setText(ruta.getNumeroRuta());

            contenedor.addView(caja);
        }
    }


}