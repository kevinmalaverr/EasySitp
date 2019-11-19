package com.easysitp.easysitp.ui.acercade;

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

public class AcercadeFragment extends Fragment {

    private AcercadeViewModel acercadeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        acercadeViewModel =
                ViewModelProviders.of(this).get(AcercadeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_acercade, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        acercadeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}