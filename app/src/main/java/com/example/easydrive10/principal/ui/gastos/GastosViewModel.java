package com.example.easydrive10.principal.ui.gastos;

import android.content.Context;

import com.example.easydrive10.pojos.Gastos;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GastosViewModel extends ViewModel {
    private Context context;
    private IGastosInterface iGastosInterface;
    private MiServicio miServicio;
    private MutableLiveData<String> correoMutable;
    private MutableLiveData<ArrayList<Gastos>> listaGastosMutable;

    public GastosViewModel(Context context, IGastosInterface iGastosInterface) {
        this.context = context;
        this.iGastosInterface = iGastosInterface;
        miServicio = MiRepositorio.getMiServicio();
        listaGastosMutable = new MutableLiveData<>();
        correoMutable = new MutableLiveData<>();
    }

    public void getGastosCamionero() {
        String correo = getCorreoMutable().getValue();
        miServicio.obtenerGastosCamionero(correo).enqueue(new Callback<ArrayList<Gastos>>() {
            @Override
            public void onResponse(Call<ArrayList<Gastos>> call, Response<ArrayList<Gastos>> response) {
                ArrayList<Gastos> listaGastos = response.body();
                getListaGastosMutable().setValue(listaGastos);
            }

            @Override
            public void onFailure(Call<ArrayList<Gastos>> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<String> getCorreoMutable() {
        return correoMutable;
    }

    public MutableLiveData<ArrayList<Gastos>> getListaGastosMutable() {
        return listaGastosMutable;
    }
}