package com.example.easydrive10;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private IMainInterface iMainInterface;

    public MainViewModelFactory(MainActivity mainActivity, IMainInterface iMainInterface) {
        this.context = mainActivity;
        this.iMainInterface = iMainInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(context,iMainInterface);
    }
}
