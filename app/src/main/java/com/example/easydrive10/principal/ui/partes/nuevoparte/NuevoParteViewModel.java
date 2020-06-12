package com.example.easydrive10.principal.ui.partes.nuevoparte;

import android.content.Context;

import com.example.easydrive10.retrofit.MiRepositorio;
import com.example.easydrive10.retrofit.MiServicio;

import androidx.lifecycle.ViewModel;

public class NuevoParteViewModel extends ViewModel {
    private Context context;
    private INuevoParteInterface iNuevoParteInterface;
    private MiServicio miServicio;

    public NuevoParteViewModel(Context context, INuevoParteInterface iNuevoParteInterface) {
        this.context = context;
        this.iNuevoParteInterface = iNuevoParteInterface;
        miServicio = MiRepositorio.getMiServicio();
    }
}
