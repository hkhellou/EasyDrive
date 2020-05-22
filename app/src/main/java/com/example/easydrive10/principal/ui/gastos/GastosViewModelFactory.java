package com.example.easydrive10.principal.ui.gastos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GastosViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private IGastosInterface iGastosInterface;

    public GastosViewModelFactory(GastosFragment gastosFragment, IGastosInterface iGastosInterface) {
        this.context = gastosFragment.getContext();
        this.iGastosInterface = iGastosInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GastosViewModel(context,iGastosInterface);
    }
}
