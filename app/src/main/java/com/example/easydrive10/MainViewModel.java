package com.example.easydrive10;

import android.content.Context;
import android.util.Log;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Camionero>> presidentesMutables;
    private MutableLiveData<Camionero> presidenteMutable;

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
        miServicio.getCamioneros().enqueue(new Callback<ArrayList<Camionero>>() {
            @Override
            public void onResponse(Call<ArrayList<Camionero>> call, Response<ArrayList<Camionero>> response) {
//                EL ARRAY QUE ME DEVUELVE LA CONSULTA ESTÁ EN response.body();
                    ArrayList<Camionero> listaPresis = response.body();
                    presidentesMutables.setValue(listaPresis);

            }

            @Override
            public void onFailure(Call<ArrayList<Camionero>> call, Throwable t) {
                Log.i("errorR", "onFailure: "+t);
            }
        });
    }
    public void getPresidente(){
        miServicio.getPresidente().enqueue(new Callback<ArrayList<Camionero>>() {
            @Override
            public void onResponse(Call<ArrayList<Camionero>> call, Response<ArrayList<Camionero>> response) {
                ArrayList<Camionero> presi = response.body();
                presidentesMutables.setValue(presi);
            }

            @Override
            public void onFailure(Call<ArrayList<Camionero>> call, Throwable t) {

            }
        });
    }
//    TENGO QUE TENER UN GETTER DE CADA MUTABLE
    public MutableLiveData<ArrayList<Camionero>> getPresidentesMutables() {
        return presidentesMutables;
    }

    public MutableLiveData<Camionero> getPresidenteMutable() {
        return presidenteMutable;
    }
}
