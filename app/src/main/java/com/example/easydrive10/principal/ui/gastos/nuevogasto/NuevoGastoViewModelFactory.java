package com.example.easydrive10.principal.ui.gastos.nuevogasto;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NuevoGastoViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private INuevoGastoInterface iNuevoGastoInterface;

    public NuevoGastoViewModelFactory(NuevoGastoFragment nuevoGastoFragment, INuevoGastoInterface iNuevoGastoInterface) {
        this.context = nuevoGastoFragment.getContext();
        this.iNuevoGastoInterface = iNuevoGastoInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NuevoGastoViewModel(context,iNuevoGastoInterface);
    }
}
