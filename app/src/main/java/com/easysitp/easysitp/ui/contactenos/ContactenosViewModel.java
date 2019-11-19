package com.easysitp.easysitp.ui.contactenos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContactenosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ContactenosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is contactenos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}