package com.example.easydrive10.principal.ui.viajes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViajesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private IViajesInterface iViajesInterface;

    public ViajesViewModelFactory(ViajesFragment viajesFragment, IViajesInterface iViajesInterface) {
        this.context = viajesFragment.getContext();
        this.iViajesInterface = iViajesInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ViajesViewModel(context,iViajesInterface);
    }
}
