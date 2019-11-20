package com.easysitp.easysitp.ui.contactenos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.easysitp.easysitp.R;

public class ContactenosFragment extends Fragment {

    View vista;
    Button botonVolver;

    private ContactenosViewModel contactenosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactenosViewModel =
                ViewModelProviders.of(this).get(ContactenosViewModel.class);
        vista = inflater.inflate(R.layout.fragment_contactenos, container, false);
        botonVolver = vista.findViewById(R.id.boton_volver);

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_contactenos_to_nav_inicio);
            }
        });
        return vista;
    }
}