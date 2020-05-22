package com.example.easydrive10.principal;

import android.content.Context;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrincipalViewModel extends ViewModel {
    private Context context;
    private IPrincipalInteface iPrincipalInteface;
    private MiServicio miServicio;
    private MutableLiveData<String> mutableCorreoPrincipal;
    private MutableLiveData<Camionero> camioneroMutable;

    public PrincipalViewModel(Context context, IPrincipalInteface iPrincipalInteface) {
        this.context = context;
        this.iPrincipalInteface = iPrincipalInteface;
        miServicio = MiRepositorio.getMiServicio();
        mutableCorreoPrincipal = new MutableLiveData<>();
        camioneroMutable = new MutableLiveData<>();
    }
    public void getCamionero(){
        String correo = getMutableCorreoPrincipal().getValue();
        miServicio.getCamionerosPorCorreo(correo).enqueue(new Callback<Camionero>() {
            @Override
            public void onResponse(Call<Camionero> call, Response<Camionero> response) {
                camioneroMutable.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Camionero> call, Throwable t) {

            }
        });
    }
    public MutableLiveData<String> getMutableCorreoPrincipal() {
        return mutableCorreoPrincipal;
    }

    public MutableLiveData<Camionero> getCamioneroMutable() {
        return camioneroMutable;
    }
}
