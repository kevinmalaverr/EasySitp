package com.easysitp.easysitp.ui.contactenos;

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

public class ContactenosFragment extends Fragment {

    private ContactenosViewModel contactenosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactenosViewModel =
                ViewModelProviders.of(this).get(ContactenosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contactenos, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        contactenosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}