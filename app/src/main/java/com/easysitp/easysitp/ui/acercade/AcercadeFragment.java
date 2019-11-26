package com.easysitp.easysitp.ui.acercade;

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

public class AcercadeFragment extends Fragment {

    View vista;
    ConstraintLayout botonVolver;


    private AcercadeViewModel acercadeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        acercadeViewModel =
                ViewModelProviders.of(this).get(AcercadeViewModel.class);
        View vista = inflater.inflate(R.layout.fragment_acercade, container, false);
        botonVolver = vista.findViewById(R.id.boton_volver);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_acercade_to_nav_inicio);
            }
        });
        return vista;
    }
}