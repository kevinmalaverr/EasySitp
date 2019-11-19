package com.easysitp.easysitp.ui.publicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.easysitp.easysitp.R;

public class PublicacionesFragment extends Fragment {

    private PublicacionesViewModel publicacionesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        publicacionesViewModel =
                ViewModelProviders.of(this).get(PublicacionesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_publicaciones, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        publicacionesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}