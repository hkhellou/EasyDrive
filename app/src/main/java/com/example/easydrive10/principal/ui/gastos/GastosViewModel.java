package com.example.easydrive10.principal.ui.gastos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GastosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GastosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}