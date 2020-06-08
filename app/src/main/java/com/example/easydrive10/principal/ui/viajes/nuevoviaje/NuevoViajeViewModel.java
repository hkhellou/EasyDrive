package com.example.easydrive10.principal.ui.viajes.nuevoviaje;

import android.content.Context;
import android.util.Log;

import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoViajeViewModel extends ViewModel {
    private Context context;
    private INuevoViaje iNuevoViaje;
    private MiServicio miServicio;
    private MutableLiveData<String> PaisDestinoMutable;
    private MutableLiveData<String> EmpresaDestinoMutable;
    private MutableLiveData<String> DireccionDestinoMutable;
    private MutableLiveData<String> FechaSalidaMutable;
    private MutableLiveData<String> FechaLlegaMutable;
    private MutableLiveData<String> CamioneroMutable;

    public NuevoViajeViewModel(Context context, INuevoViaje iNuevoViaje) {
        this.context = context;
        this.iNuevoViaje = iNuevoViaje;
        miServicio = MiRepositorio.getMiServicio();
        PaisDestinoMutable = new MutableLiveData<>();
        EmpresaDestinoMutable = new MutableLiveData<>();
        DireccionDestinoMutable = new MutableLiveData<>();
        FechaSalidaMutable = new MutableLiveData<>();
        FechaLlegaMutable = new MutableLiveData<>();
        CamioneroMutable = new MutableLiveData<>();
    }

    public void insertarViaje() {
        String pais = PaisDestinoMutable.getValue();
        String empresa = EmpresaDestinoMutable.getValue();
        String direccion = DireccionDestinoMutable.getValue();
        String fechaSalida = FechaSalidaMutable.getValue();
        String fechaLlegada = FechaLlegaMutable.getValue();
        String camionero = CamioneroMutable.getValue();
        miServicio.insertarViajes(pais,empresa,direccion,fechaSalida,fechaLlegada,camionero).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                iNuevoViaje.respuestaInsertUsuario();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("errorInsertarViaje", "onFailure: "+t);
            }
        });
    }


    public MutableLiveData<String> getPaisDestinoMutable() {
        return PaisDestinoMutable;
    }

    public MutableLiveData<String> getEmpresaDestinoMutable() {
        return EmpresaDestinoMutable;
    }

    public MutableLiveData<String> getDireccionDestinoMutable() {
        return DireccionDestinoMutable;
    }

    public MutableLiveData<String> getFechaSalidaMutable() {
        return FechaSalidaMutable;
    }

    public MutableLiveData<String> getFechaLlegaMutable() {
        return FechaLlegaMutable;
    }

    public MutableLiveData<String> getCamioneroMutable() {
        return CamioneroMutable;
    }
}
