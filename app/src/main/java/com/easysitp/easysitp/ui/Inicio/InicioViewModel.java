package com.easysitp.easysitp.ui.Inicio;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.easysitp.easysitp.R;

public class InicioViewModel extends ViewModel {


    private MutableLiveData<String> mText;

    public InicioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Inicio fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}