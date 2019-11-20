package com.easysitp.easysitp.ui.listarutas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.easysitp.easysitp.R;

public class ListaRutasFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_listarutas, container, false);
        final TextView textView = root.findViewById(R.id.text_share);

        return root;
    }
}