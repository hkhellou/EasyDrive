package com.example.easydrive10.principal.ui.viajes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentViajesBinding;
import com.example.easydrive10.pojos.Viajes;
import com.example.easydrive10.principal.PrincipalActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViajesFragment extends Fragment implements IViajesInterface {
    private  PrincipalActivity principalActivity = new PrincipalActivity();
    private FragmentViajesBinding binding;
    AdaptadorRecicledViewViajes adaptadorRecicledViewViajes;
    private ViajesViewModel viewModel;
    private  FloatingActionButton btnFlotanteViajes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, new ViajesViewModelFactory(this, this)).get(ViajesViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viajes, container, false);
        View view = binding.getRoot();
        //View root = inflater.inflate(R.layout.fragment_viajes, container, false);
        recibirCorreoPreferencias();
        viewModel.getListaViajesCamioneroMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Viajes>>() {
            @Override
            public void onChanged(ArrayList<Viajes> viajes) {
                adaptadorRecicledViewViajes = new AdaptadorRecicledViewViajes(viajes);
                //CREO UN LAYOUT LINEAL PARA QUE SE VEA LA LISTA EN VERTICAL Y LAS TARJETAS UNA DEBAJO DE LA OTRA
                binding.rvListaViajes.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvListaViajes.setAdapter(adaptadorRecicledViewViajes);
            }
        });
        //LLAMAR AL METODO DEL VIEWMODEL QUE CARGA EL ARRAYLIST CON LOS DATOS DE LA BASE DE DATOS
        viewModel.getViajesCamionero();
        //BOTON FLOTANTE PARA ANYADIR NUEVOS VIAJES
        btnFlotanteViajes = view.findViewById(R.id.btn_flot_añadirViaje);
        btnFlotanteViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NuevoViajeFragment nuevoViajeFragment = new NuevoViajeFragment();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.drawer_layout,nuevoViajeFragment,nuevoViajeFragment.getTag())
                        .commit();
            }
        });
        //binding.setViajesViewModel(viewModel);
        binding.rvListaViajes.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            ESTE METODO ES PARA OCULTAR O MOSTRAR EL BOTON FLOTANTE CUANDO SE HAGA SCROLL EN LA LISTA
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    // Puedes ocultarlo simplemente
                    //fab.hide();
                    // o añadir la animación deseada
                    btnFlotanteViajes.animate().translationY(btnFlotanteViajes.getHeight() +
                            getResources().getDimension(R.dimen.fab_margin))
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(500); // Cambiar al tiempo deseado
                else if (dy < 0)
                    //fab.show();
                    btnFlotanteViajes.animate().translationY(0)
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(500); // Cambiar al tiempo deseado
            }
        });
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo","prueba@gmail.com");
        viewModel.getCorreoUsuarioPreferencias().setValue(correoUsuario);
        //txt.setText(viewModel.getCorreoUsuarioPreferencias().getValue());
        principalActivity.getCamionero().setCorreo(correoUsuario);
        //txt.setText(principalActivity.getCamionero().getCorreo());
    }
}
