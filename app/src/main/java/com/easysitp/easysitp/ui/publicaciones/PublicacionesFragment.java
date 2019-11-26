package com.easysitp.easysitp.ui.publicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.easysitp.easysitp.R;

public class PublicacionesFragment extends Fragment {

    View vista;
    ConstraintLayout botonVolver;

    private PublicacionesViewModel publicacionesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        publicacionesViewModel =
                ViewModelProviders.of(this).get(PublicacionesViewModel.class);
        vista = inflater.inflate(R.layout.fragment_publicaciones, container, false);
        botonVolver = vista.findViewById(R.id.boton_volver);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_publicaciones_to_nav_inicio);
            }
        });

        return vista;
    }
}