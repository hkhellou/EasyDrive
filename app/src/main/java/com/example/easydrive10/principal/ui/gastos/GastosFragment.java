package com.example.easydrive10.principal.ui.gastos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.easydrive10.DeslizarParaBorrarRV;
import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentGastosBinding;
import com.example.easydrive10.pojos.Gastos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class GastosFragment extends Fragment implements IGastosInterface {

    private GastosViewModel gastosViewModel;
    private FragmentGastosBinding binding;
    private AdaptadorRecicledViewGastos adaptadorRecicledViewGastos;
    private FloatingActionButton btnFlotanteGastos;
    private CoordinatorLayout coordinatorLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gastosViewModel = new ViewModelProvider(this, new GastosViewModelFactory(this, this)).get(GastosViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gastos, container, false);
        View view = binding.getRoot();
        coordinatorLayout = getActivity().findViewById(R.id.coordinator);
        oculatarBotonFlotante();
        recibirCorreoPreferencias();
        btnFlotanteGastos = binding.btnFlotAnyadirGasto;
        btnFlotanteGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nuevoGastoFragment);
            }
        });
        gastosViewModel.getListaGastosMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Gastos>>() {
            @Override
            public void onChanged(ArrayList<Gastos> gastos) {
                adaptadorRecicledViewGastos = new AdaptadorRecicledViewGastos(gastos);
                //CREO UN LAYOUT LINEAL PARA QUE SE VEA LA LISTA EN VERTICAL Y LAS TARJETAS UNA DEBAJO DE LA OTRA
                binding.rvListaGastos.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvListaGastos.setAdapter(adaptadorRecicledViewGastos);
                activarDeslizarParaBorrar();
            }
        });
        //LLAMAR AL METODO DEL VIEWMODEL QUE CARGA EL ARRAYLIST CON LOS DATOS DE LA BASE DE DATOS
        gastosViewModel.getGastosCamionero();
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo", "prueba@gmail.com");
        gastosViewModel.getCorreoMutable().setValue(correoUsuario);
    }

    @Override
    public void oculatarBotonFlotante() {
        binding.rvListaGastos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //            ESTE METODO ES PARA OCULTAR O MOSTRAR EL BOTON FLOTANTE CUANDO SE HAGA SCROLL EN LA LISTA
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    // Puedes ocultarlo simplemente
                    //fab.hide();
                    // o añadir la animación deseada
                    btnFlotanteGastos.animate().translationY(btnFlotanteGastos.getHeight() +
                            getResources().getDimension(R.dimen.fab_margin))
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(500); // Cambiar al tiempo deseado
                else if (dy < 0)
                    //fab.show();
                    btnFlotanteGastos.animate().translationY(0)
                            .setInterpolator(new LinearInterpolator())
                            .setDuration(500); // Cambiar al tiempo deseado
            }
        });
    }

    @Override
    public void listaVacia() {
        binding.txtNoGasttos.setText("Ups!! no tienes ningún gasto realizado");
    }

    private void activarDeslizarParaBorrar() {
        DeslizarParaBorrarRV deslizarParaBorrarRV = new DeslizarParaBorrarRV(this.getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
//                PASAR EL ID DEL GASTO PARA PODER BORRARLO DE LA BASE DE DATOS
                gastosViewModel.getMutableIdGastos().setValue(adaptadorRecicledViewGastos.getListaGastos().get(position).getIdGastos());
                gastosViewModel.borrarGastos();
                adaptadorRecicledViewGastos.removeItem(position);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, R.string.ElementoBorrado, Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(deslizarParaBorrarRV);
        itemTouchhelper.attachToRecyclerView(binding.rvListaGastos);
    }
}
