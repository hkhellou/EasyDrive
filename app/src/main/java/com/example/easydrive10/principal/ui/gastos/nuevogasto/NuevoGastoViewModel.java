package com.example.easydrive10.principal.ui.gastos.nuevogasto;

import android.content.Context;

import androidx.lifecycle.ViewModel;

public class NuevoGastoViewModel extends ViewModel {
    private Context context;
    private INuevoGastoInterface iNuevoGastoInterface;

    public NuevoGastoViewModel(Context context, INuevoGastoInterface iNuevoGastoInterface) {
        this.context = context;
        this.iNuevoGastoInterface = iNuevoGastoInterface;
    }
}
