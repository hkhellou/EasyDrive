package com.example.easydrive10.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private IloginInterface iloginInterface;

    public LoginViewModelFactory(LoginActivity loginActivity, IloginInterface iloginInterface) {
        this.context = loginActivity;
        this.iloginInterface = iloginInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(context,iloginInterface);
    }
}
