package com.example.easydrive10.principal.ui.viajes.nuevoviaje;

import android.content.Context;

import androidx.lifecycle.ViewModel;

public class NuevoViajeViewModel extends ViewModel {
    private Context context;
    private INuevoViaje iNuevoViaje;

    public NuevoViajeViewModel(Context context, INuevoViaje iNuevoViaje) {
        this.context = context;
        this.iNuevoViaje = iNuevoViaje;
    }
}
