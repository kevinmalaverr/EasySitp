package com.easysitp.easysitp.ui.contactenos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.easysitp.easysitp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ContactenosFragment extends Fragment {

    View vista;
    ConstraintLayout botonVolver;
    Button botonEnviar;
    DatabaseReference mRootReference;
    EditText sugerencia_enviar;

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
        botonEnviar = vista.findViewById(R.id.button3);
        mRootReference = FirebaseDatabase.getInstance().getReference();
        sugerencia_enviar = vista.findViewById(R.id.sugerencia);
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoBasico();
            }
        });

        return vista;
    }

    private void mostrarDialogoBasico() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enviar");
        builder.setMessage("¿Quieres enviar el mensaje?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mensaje = sugerencia_enviar.getText().toString();


                        Map<String, Object> envio_mensaje = new HashMap<>();
                        envio_mensaje.put("Sugerencia", mensaje);
                        mRootReference.child("Mensaje de sugerencia").push().setValue(envio_mensaje);
                        sugerencia_enviar.setText("");


                        Toast.makeText(getActivity(), "Mensaje enviado", Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }

}