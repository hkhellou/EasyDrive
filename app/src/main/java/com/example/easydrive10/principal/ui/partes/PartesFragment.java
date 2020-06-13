package com.example.easydrive10.principal.ui.partes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.easydrive10.DeslizarParaBorrarRV;
import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentPartesBinding;
import com.example.easydrive10.pojos.Partes;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PartesFragment extends Fragment implements IPartesInterface {

    private PartesViewModel partesViewModel;
    private AdaptadorRecilerViewPartes adaptadorRecilerViewPartes;
    private FragmentPartesBinding binding;
    private CoordinatorLayout coordinatorLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        partesViewModel = new ViewModelProvider(this, new PartesViewModelFactory(this, this)).get(PartesViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_partes, container, false);
        View view = binding.getRoot();
        coordinatorLayout = getActivity().findViewById(R.id.coordinator);
        recibirCorreoPreferencias();
        partesViewModel.getListaMutablePartes().observe(getViewLifecycleOwner(), new Observer<ArrayList<Partes>>() {
            @Override
            public void onChanged(ArrayList<Partes> partes) {
                adaptadorRecilerViewPartes = new AdaptadorRecilerViewPartes(partes);
                //CREO UN LAYOUT LINEAL PARA QUE SE VEA LA LISTA EN VERTICAL Y LAS TARJETAS UNA DEBAJO DE LA OTRA
                binding.rvListaPartes.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvListaPartes.setAdapter(adaptadorRecilerViewPartes);
                activarDeslizarParaBorrar();

            }
        });
        partesViewModel.getPartes();
        binding.btnFlotAnyadirParte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nuevoParteFragment);
            }
        });
        binding.rvListaPartes.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //            ESTE METODO ES PARA OCULTAR O MOSTRAR EL BOTON FLOTANTE CUANDO SE HAGA SCROLL EN LA LISTA
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    // Puedes ocultarlo simplemente
                    //fab.hide();
                    // o añadir la animación deseada
                    binding.btnFlotAnyadirParte.animate().translationY(binding.btnFlotAnyadirParte.getHeight() +
                            getResources().getDimension(R.dimen.fab_margin))
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(400); // Cambiar al tiempo deseado
                else if (dy < 0)
                    //fab.show();
                    binding.btnFlotAnyadirParte.animate().translationY(0)
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(300); // Cambiar al tiempo deseado
            }
        });
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo", "prueba@gmail.com");
        partesViewModel.getMutableCorreo().setValue(correoUsuario);
    }

    @Override
    public void listaVacia() {
        binding.txtNoPartes.setText(R.string.MensajeNoPartes);

    }

    private void activarDeslizarParaBorrar() {
        DeslizarParaBorrarRV deslizarParaBorrarRV = new DeslizarParaBorrarRV(this.getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
//                final String item = String.valueOf(adaptadorRecilerViewPartes.getListaPartes().get(position));
                partesViewModel.getMutableIdParte().setValue(adaptadorRecilerViewPartes.getListaPartes().get(position).getIdPartes());
                partesViewModel.borrarParte();
                adaptadorRecilerViewPartes.removeItem(position);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, R.string.ElementoBorrado, Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(deslizarParaBorrarRV);
        itemTouchhelper.attachToRecyclerView(binding.rvListaPartes);
    }
}
