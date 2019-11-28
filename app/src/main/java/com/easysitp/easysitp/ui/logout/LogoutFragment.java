package com.easysitp.easysitp.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.easysitp.easysitp.LoginActivity;
import com.easysitp.easysitp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    View vista;
    ConstraintLayout botonVolver;

    private LogoutViewModel logoutViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        logoutViewModel =
                ViewModelProviders.of(this).get(LogoutViewModel.class);
        vista = inflater.inflate(R.layout.fragment_logout, container, false);

        FirebaseAuth.getInstance().signOut();
        goLogInScreen();

        return vista;
    }

    private void goLogInScreen() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}