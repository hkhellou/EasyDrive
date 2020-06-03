package com.example.easydrive10.principal.ui.viajes.nuevoviaje;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NuevoViajeViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private INuevoViaje iNuevoViaje;

    public NuevoViajeViewModelFactory(NuevoViajeFragment nuevoViajeFragment, INuevoViaje iNuevoViaje) {
        this.context = nuevoViajeFragment.getContext();
        this.iNuevoViaje = iNuevoViaje;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NuevoViajeViewModel(context,iNuevoViaje);
    }
}
