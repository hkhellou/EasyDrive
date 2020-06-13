package com.example.easydrive10.principal.ui.partes;

import android.content.Context;

import com.example.easydrive10.pojos.Partes;
import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartesViewModel extends ViewModel {
    private Context context;
    private IPartesInterface iPartesInterface;
    private MiServicio miServicio;
    private MutableLiveData<String> mutableCorreo;
    private MutableLiveData<ArrayList<Partes>> listaMutablePartes;
    private MutableLiveData<String> mutableIdParte;

    public PartesViewModel(Context context, IPartesInterface iPartesInterface) {
        this.context = context;
        this.iPartesInterface = iPartesInterface;
        miServicio = MiRepositorio.getMiServicio();
        mutableCorreo = new MutableLiveData<>();
        mutableIdParte = new MutableLiveData<>();
        listaMutablePartes = new MutableLiveData<>();
    }

    public void getPartes() {
        String correo = mutableCorreo.getValue();
        miServicio.mostrarPartes(correo).enqueue(new Callback<ArrayList<Partes>>() {
            @Override
            public void onResponse(Call<ArrayList<Partes>> call, Response<ArrayList<Partes>> response) {
                ArrayList listaPartes = response.body();
                listaMutablePartes.setValue(listaPartes);
                if(listaPartes.size()==0){
                    iPartesInterface.listaVacia();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Partes>> call, Throwable t) {

            }
        });
    }

    public void borrarParte(){
        String idParte = mutableIdParte.getValue();
        miServicio.borrarParte(idParte).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<String> getMutableCorreo() {
        return mutableCorreo;
    }

    public MutableLiveData<ArrayList<Partes>> getListaMutablePartes() {
        return listaMutablePartes;
    }

    public MutableLiveData<String> getMutableIdParte() {
        return mutableIdParte;
    }
}