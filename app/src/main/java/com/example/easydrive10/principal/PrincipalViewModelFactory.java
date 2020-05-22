package com.example.easydrive10.principal;

import android.content.Context;

import com.example.easydrive10.register.RegisterViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PrincipalViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private Context context;
        private IPrincipalInteface iPrincipalInteface;

    public PrincipalViewModelFactory(PrincipalActivity principalActivity, IPrincipalInteface iPrincipalInteface) {
        this.context = principalActivity;
        this.iPrincipalInteface = iPrincipalInteface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PrincipalViewModel(context,iPrincipalInteface);
    }
}
