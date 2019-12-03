package com.easysitp.easysitp.ui.publicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.easysitp.easysitp.AdapterMensajes;
import com.easysitp.easysitp.MainActivity;
import com.easysitp.easysitp.MensajeEnviar;
import com.easysitp.easysitp.MensajeRecibir;
import com.easysitp.easysitp.R;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class PublicacionesFragment extends Fragment {

    View vista;
    ConstraintLayout botonVolver;
    private TextView nombre;
    private RecyclerView rvMensaje;
    private EditText txtMensaje;
    private Button btnEnviar;
    private TextView tituloNumeroRuta;

    private AdapterMensajes adapter;
    private GoogleSignInClient mSignInClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    private PublicacionesViewModel publicacionesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        publicacionesViewModel =
                ViewModelProviders.of(this).get(PublicacionesViewModel.class);
        vista = inflater.inflate(R.layout.fragment_publicaciones, container, false);
        botonVolver = vista.findViewById(R.id.boton_volver);

        Bundle datosRecuperados = getArguments();
        String ruta = datosRecuperados.getString("RUTA");

        nombre = vista.findViewById(R.id.nombre);
        rvMensaje = vista.findViewById(R.id.rvMensaje);
        txtMensaje = vista.findViewById(R.id.txtMensaje);
        btnEnviar = vista.findViewById(R.id.btnEnviar);
        tituloNumeroRuta = vista.findViewById(R.id.titulo_numero_ruta);
        tituloNumeroRuta.setText(ruta);
        database= FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Chat/" + ruta);

        MainActivity activity = (MainActivity) getActivity();
        String recibeDato = activity.getDataFragment();

        nombre.setText(recibeDato);


        adapter = new AdapterMensajes(getContext());
        LinearLayoutManager l = new LinearLayoutManager(getContext());
        rvMensaje.setLayoutManager(l);
        rvMensaje.setAdapter(adapter);


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new MensajeEnviar(txtMensaje.getText().toString(), nombre.getText().toString(), ServerValue.TIMESTAMP));
                txtMensaje.setText("");

            }
        });

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MensajeRecibir m= dataSnapshot.getValue(MensajeRecibir.class);
                adapter.addMensaje(m);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollBar();
            }
        });



        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return vista;
    }
    private void setScrollBar(){
        rvMensaje.scrollToPosition(adapter.getItemCount()-1);
    }
}
