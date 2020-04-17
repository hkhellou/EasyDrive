package com.example.easydrive10.login;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.pojos.Usuario;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private Context context;
    private IloginInterface iloginInterface;
    private MutableLiveData<ArrayList<Usuario>> listaUsuariosMutable;
    private MiServicio miServicio;

    public LoginViewModel(Context context, IloginInterface iloginInterface) {
        this.context = context;
        this.iloginInterface = iloginInterface;
        miServicio= MiRepositorio.getMiServicio();
        listaUsuariosMutable = new MutableLiveData<>();
    }
    public void getUsuarios(){
        miServicio.getUsuarios().enqueue(new Callback<ArrayList<Usuario>>() {
            @Override
            public void onResponse(Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                ArrayList<Usuario> listaUsuarios = response.body();
                listaUsuariosMutable.setValue(listaUsuarios);
            }

            @Override
            public void onFailure(Call<ArrayList<Usuario>> call, Throwable t) {
                Log.i("errorR", "onFailure: "+t);
            }
        });
    }

    public MutableLiveData<ArrayList<Usuario>> getListaUsuariosMutable() {
        return listaUsuariosMutable;
    }
}
