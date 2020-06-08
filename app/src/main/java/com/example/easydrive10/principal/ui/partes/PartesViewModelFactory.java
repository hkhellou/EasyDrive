package com.example.easydrive10.principal.ui.partes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PartesViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private IPartesInterface iPartesInterface;

    public PartesViewModelFactory(PartesFragment partesFragment, IPartesInterface iPartesInterface) {
        this.context = partesFragment.getContext();
        this.iPartesInterface = iPartesInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PartesViewModel(context,iPartesInterface);
    }
}
