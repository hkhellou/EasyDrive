package com.example.easydrive10.principal.ui.gastos.nuevogasto;

import android.content.Context;

import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoGastoViewModel extends ViewModel {
    private Context context;
    private INuevoGastoInterface iNuevoGastoInterface;
    private MiServicio miServicio;
    private MutableLiveData<String> nombreMutable;
    private MutableLiveData<String> combustibleMutable;
    private MutableLiveData<String> peajesMutable;
    private MutableLiveData<String> comidaMutable;
    private MutableLiveData<String> otros_gastosMutable;
    private MutableLiveData<String> total_gastosMutable;
    private MutableLiveData<String> camioneroMutable;

    public NuevoGastoViewModel(Context context, INuevoGastoInterface iNuevoGastoInterface) {
        this.context = context;
        this.iNuevoGastoInterface = iNuevoGastoInterface;
        miServicio = MiRepositorio.getMiServicio();
        nombreMutable = new MutableLiveData<>();
        combustibleMutable = new MutableLiveData<>();
        peajesMutable = new MutableLiveData<>();
        comidaMutable = new MutableLiveData<>();
        otros_gastosMutable = new MutableLiveData<>();
        total_gastosMutable = new MutableLiveData<>();
        camioneroMutable = new MutableLiveData<>();
    }

    public void insertarGastos() {
        String nombre = nombreMutable.getValue();
        double combustible = Double.parseDouble(combustibleMutable.getValue());
        double peajes = Double.parseDouble(peajesMutable.getValue());
        double comida = Double.parseDouble(comidaMutable.getValue());
        double otros_gastos = Double.parseDouble(otros_gastosMutable.getValue());
        String camionero = camioneroMutable.getValue();
        double total_gastos = Double.parseDouble(total_gastosMutable.getValue());
        miServicio.insertarGastos(nombre, combustible, peajes, comida, otros_gastos, camionero, total_gastos).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                iNuevoGastoInterface.respuestaInsertarViaje();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<String> getNombreMutable() {
        return nombreMutable;
    }

    public MutableLiveData<String> getCombustibleMutable() {
        return combustibleMutable;
    }

    public MutableLiveData<String> getPeajesMutable() {
        return peajesMutable;
    }

    public MutableLiveData<String> getComidaMutable() {
        return comidaMutable;
    }

    public MutableLiveData<String> getOtros_gastosMutable() {
        return otros_gastosMutable;
    }

    public MutableLiveData<String> getTotal_gastosMutable() {
        return total_gastosMutable;
    }

    public MutableLiveData<String> getCamioneroMutable() {
        return camioneroMutable;
    }
}
