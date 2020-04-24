package com.example.easydrive10.login;

import android.content.Context;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.View;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.pojos.Usuario;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
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
    private MutableLiveData<String> mutableCorreo;
    private MutableLiveData<String> mutableContrasenia;
    private MiServicio miServicio;

    public LoginViewModel(Context context, IloginInterface iloginInterface) {
        this.context = context;
        this.iloginInterface = iloginInterface;
        miServicio= MiRepositorio.getMiServicio();
        listaUsuariosMutable = new MutableLiveData<>();
        mutableCorreo = new MutableLiveData<>();
        mutableContrasenia = new MutableLiveData<>();
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
    public void comprobarUsuario(){
        String correo=mutableCorreo.getValue();
        String contrasenia = mutableContrasenia.getValue();
        miServicio.comprobarUsuario(correo,contrasenia).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuario = response.body();
                    if(usuario!=null){
                        iloginInterface.existeUsuario();
                    }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                if (t instanceof MalformedJsonException) {
                    iloginInterface.noExisteUsuario();
                }
                if( t instanceof SocketTimeoutException){

                }
                if(t instanceof ConnectException){
                    iloginInterface.falloServidor();
                }
                Log.e("errorR", "onFailure: "+t);
            }
        });
    }
    public void mandarPaginaRegistro(){
    iloginInterface.mandarPaginaRegistro();
    }
    public MutableLiveData<ArrayList<Usuario>> getListaUsuariosMutable() {
        return listaUsuariosMutable;
    }

    public MutableLiveData<String> getMutableCorreo() {
        return mutableCorreo;
    }

    public MutableLiveData<String> getMutableContrasenia() {
        return mutableContrasenia;
    }
}
