package com.example.easydrive10.register;

import android.content.Context;
import android.util.Log;

import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private Context context;
    private IRegisterInterface iRegisterInterface;
    private MutableLiveData<String> mutableCorreoRegister;
    private MutableLiveData<String> mutablePassRegister;
    private MiServicio miServicio;

    public RegisterViewModel(Context context, IRegisterInterface iRegisterInterface) {
        this.context = context;
        this.iRegisterInterface = iRegisterInterface;
        mutableCorreoRegister = new MutableLiveData<>();
        mutablePassRegister = new MutableLiveData<>();
        miServicio = MiRepositorio.getMiServicio();
    }
    public void insertarUsuario(){
        String correo = mutableCorreoRegister.getValue();
        String pass = mutablePassRegister.getValue();
        miServicio.insertarUsuario(correo,pass).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                iRegisterInterface.respuestaInsertUsuario();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("errorR", "onFailure: "+t);
            }
        });
    }
    public MutableLiveData<String> getMutableCorreoRegister() {
        return mutableCorreoRegister;
    }

    public MutableLiveData<String> getMutablePassRegister() {
        return mutablePassRegister;
    }
}
