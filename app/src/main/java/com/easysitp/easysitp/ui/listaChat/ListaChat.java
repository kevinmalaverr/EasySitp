package com.easysitp.easysitp.ui.listaChat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.easysitp.easysitp.Paraderos;
import com.easysitp.easysitp.R;
import com.easysitp.easysitp.Ruta;
import com.easysitp.easysitp.ui.publicaciones.PublicacionesFragment;

import java.util.ArrayList;

public class ListaChat extends Fragment {

    View vista;
    View caja;
    Button botonVolver;
    ViewGroup contenedor;
    TextView texto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment}
        vista = inflater.inflate(R.layout.fragment_lista_chat, container, false);
        contenedor = vista.findViewById(R.id.contenedor);

        addChats(Paraderos.getRutas());
        return vista;

    }

    public void addChats(ArrayList<Ruta> lista) {
        for (int i = 0; i < lista.size(); i++) {
            final Ruta ruta = lista.get(i);
            caja = LayoutInflater.from(getContext()).inflate(R.layout.caja_lista_chat, contenedor, false);
            final TextView nombreRuta = caja.findViewById(R.id.nombre_ruta);
            TextView numeroRuta = caja.findViewById(R.id.numero_ruta);

            nombreRuta.setText(ruta.NombreRuta);
            numeroRuta.setText(ruta.numeroRuta);

            caja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle datos = new Bundle();
                    datos.putString("RUTA", ruta.numeroRuta);
                    Fragment fragmento = new PublicacionesFragment();
                    fragmento.setArguments(datos);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.nav_host_fragment, fragmento);
                    fragmentTransaction.addToBackStack(null);

                    fragmentTransaction.commit();

                }
            });

            contenedor.addView(caja);
        }
    }

}
