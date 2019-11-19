package com.easysitp.easysitp.ui.publicaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PublicacionesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PublicacionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is publicaciones fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}