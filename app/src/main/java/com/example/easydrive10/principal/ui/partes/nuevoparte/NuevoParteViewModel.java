package com.example.easydrive10.principal.ui.partes.nuevoparte;

import android.content.Context;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoParteViewModel extends ViewModel {
    private Context context;
    private INuevoParteInterface iNuevoParteInterface;
    private MiServicio miServicio;
    private MutableLiveData<String> mutableCorreo;
    private MutableLiveData<String> mutableCamion;
    private MutableLiveData<String> mutableLocalizacion;
    private MutableLiveData<String> mutableDescripcion;
    private MutableLiveData<String> mutableNombre;

    public NuevoParteViewModel(Context context, INuevoParteInterface iNuevoParteInterface) {
        this.context = context;
        this.iNuevoParteInterface = iNuevoParteInterface;
        miServicio = MiRepositorio.getMiServicio();
        mutableCorreo = new MutableLiveData<>();
        mutableCamion = new MutableLiveData<>();
        mutableLocalizacion = new MutableLiveData<>();
        mutableDescripcion = new MutableLiveData<>();
        mutableNombre = new MutableLiveData<>();
    }
    public void getCamionero(){
        String correo = mutableCorreo.getValue();
        miServicio.getCamionerosPorCorreo(correo).enqueue(new Callback<Camionero>() {
            @Override
            public void onResponse(Call<Camionero> call, Response<Camionero> response) {
                String camion = response.body().getCamion().toString();
                mutableCamion.setValue(camion);

            }

            @Override
            public void onFailure(Call<Camionero> call, Throwable t) {

            }
        });
    }

    public void insertarParte(){
        String Localizacion = mutableLocalizacion.getValue();
        String Descripcion = mutableDescripcion.getValue();
        String Camion = mutableCamion.getValue();
        String Camionero = mutableCorreo.getValue();
        String Nombre = mutableNombre.getValue();
        miServicio.insertarParte(Localizacion,Descripcion,Camion,Camionero,Nombre).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                iNuevoParteInterface.respuestaInsertarParte();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<String> getMutableCorreo() {
        return mutableCorreo;
    }

    public MutableLiveData<String> getMutableCamion() {
        return mutableCamion;
    }

    public MutableLiveData<String> getMutableLocalizacion() {
        return mutableLocalizacion;
    }

    public MutableLiveData<String> getMutableDescripcion() {
        return mutableDescripcion;
    }

    public MutableLiveData<String> getMutableNombre() {
        return mutableNombre;
    }
}
