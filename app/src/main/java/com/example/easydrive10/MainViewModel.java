package com.example.easydrive10;

import android.content.Context;
import android.net.wifi.aware.PublishConfig;
import android.util.Log;

import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Presidente>> presidentesMutables;
    private MutableLiveData<Presidente> presidenteMutable;

    private Context context;
    private  IMainInterface iMainInterface;
    private MiServicio miServicio;
//CONSTRUCTOR DEL VIEWMODEL OBLIGATORIO
    public MainViewModel(Context context, IMainInterface iMainInterface) {
        this.context = context;
        this.iMainInterface = iMainInterface;
//        INSTANCIA DEL SINGLETON
        miServicio= MiRepositorio.getMiServicio();
        presidentesMutables = new MutableLiveData<>();
        presidenteMutable = new MutableLiveData<>();
    }
    public void getPresidentes(){
        Log.i("aki", "HOLA AKI TOOOOOOOOOOOOOOOOOY");
        miServicio.getPresidentes().enqueue(new Callback<ArrayList<Presidente>>() {
            @Override
            public void onResponse(Call<ArrayList<Presidente>> call, Response<ArrayList<Presidente>> response) {
//                EL ARRAY QUE ME DEVUELVE LA CONSULTA ESTÁ EN response.body();
                    ArrayList<Presidente> listaPresis = response.body();
                    presidentesMutables.setValue(listaPresis);

            }

            @Override
            public void onFailure(Call<ArrayList<Presidente>> call, Throwable t) {
                Log.i("errorR", "onFailure: "+t);
            }
        });
    }
    public void getPresidente(){
        miServicio.getPresidente().enqueue(new Callback<ArrayList<Presidente>>() {
            @Override
            public void onResponse(Call<ArrayList<Presidente>> call, Response<ArrayList<Presidente>> response) {
                ArrayList<Presidente> presi = response.body();
                presidentesMutables.setValue(presi);
            }

            @Override
            public void onFailure(Call<ArrayList<Presidente>> call, Throwable t) {

            }
        });
    }
//    TENGO QUE TENER UN GETTER DE CADA MUTABLE
    public MutableLiveData<ArrayList<Presidente>> getPresidentesMutables() {
        return presidentesMutables;
    }

    public MutableLiveData<Presidente> getPresidenteMutable() {
        return presidenteMutable;
    }
}
