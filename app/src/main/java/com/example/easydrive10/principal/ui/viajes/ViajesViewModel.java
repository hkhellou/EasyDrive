package com.example.easydrive10.principal.ui.viajes;

import android.content.Context;
import android.util.Log;

import com.example.easydrive10.pojos.Viajes;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViajesViewModel extends ViewModel {
    private Context context;
    private IViajesInterface iViajesInterface;
    private MiServicio miServicio;
    private MutableLiveData<String> correoUsuarioPreferencias;
    private MutableLiveData<ArrayList<Viajes>> listaViajesCamioneroMutable;
    private MutableLiveData<String> mutableIdViajes;

    public ViajesViewModel(Context context, IViajesInterface iViajesInterface) {
        this.context = context;
        this.iViajesInterface = iViajesInterface;
        correoUsuarioPreferencias = new MutableLiveData<>();
        listaViajesCamioneroMutable = new MutableLiveData<>();
        mutableIdViajes = new MutableLiveData<>();
        miServicio = MiRepositorio.getMiServicio();
    }
    public void getViajesCamionero(){
        String Correo = correoUsuarioPreferencias.getValue();
        miServicio.mostrartViajesCamionero(Correo).enqueue(new Callback<ArrayList<Viajes>>() {
            @Override
            public void onResponse(Call<ArrayList<Viajes>> call, Response<ArrayList<Viajes>> response) {
                ArrayList listaViajes = response.body();
                listaViajesCamioneroMutable.setValue(listaViajes);
                if(listaViajes.size()==0){
                    iViajesInterface.listaVacia();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Viajes>> call, Throwable t) {
                Log.i("errorViajesCamioneros", "onFailure: "+t);
            }
        });

    }

    public void borrarViaje(){
        String idViaje = mutableIdViajes.getValue();
        miServicio.borrarViaje(idViaje).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<String> getCorreoUsuarioPreferencias() {
        return correoUsuarioPreferencias;
    }

    public MutableLiveData<ArrayList<Viajes>> getListaViajesCamioneroMutable() {
        return listaViajesCamioneroMutable;
    }

    public MutableLiveData<String> getMutableIdViajes() {
        return mutableIdViajes;
    }
}