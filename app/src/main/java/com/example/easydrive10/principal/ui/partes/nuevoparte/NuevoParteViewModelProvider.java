package com.example.easydrive10.principal.ui.partes.nuevoparte;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class NuevoParteViewModelProvider extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private INuevoParteInterface iNuevoParteInterface;

    public NuevoParteViewModelProvider(NuevoParteFragment nuevoParteFragment, INuevoParteInterface iNuevoParteInterface) {
        this.context = nuevoParteFragment.getContext();
        this.iNuevoParteInterface = iNuevoParteInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new NuevoParteViewModel(context,iNuevoParteInterface);
    }
}
