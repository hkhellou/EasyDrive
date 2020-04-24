package com.example.easydrive10.register;

import android.content.Context;

import com.example.easydrive10.login.IloginInterface;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RegisterViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;
    private IRegisterInterface iRegisterInterface;

    public RegisterViewModelFactory(RegisterActivity registerActivity, IRegisterInterface iRegisterInterface) {
        this.context = registerActivity;
        this.iRegisterInterface = iRegisterInterface;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RegisterViewModel(context,iRegisterInterface);
    }
}
