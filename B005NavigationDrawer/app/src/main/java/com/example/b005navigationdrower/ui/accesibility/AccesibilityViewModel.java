package com.example.b005navigationdrower.ui.accesibility;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccesibilityViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AccesibilityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is accesibility fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
