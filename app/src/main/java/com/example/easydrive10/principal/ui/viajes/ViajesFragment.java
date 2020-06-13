package com.example.easydrive10.principal.ui.viajes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.easydrive10.DeslizarParaBorrarRV;
import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentViajesBinding;
import com.example.easydrive10.pojos.Viajes;
import com.example.easydrive10.principal.PrincipalActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViajesFragment extends Fragment implements IViajesInterface {
    private PrincipalActivity principalActivity = new PrincipalActivity();
    private FragmentViajesBinding binding;
    AdaptadorRecicledViewViajes adaptadorRecicledViewViajes;
    private ViajesViewModel viewModel;
    private FloatingActionButton btnFlotanteViajes;
    private CoordinatorLayout coordinatorLayout;
    private AlertDialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, new ViajesViewModelFactory(this, this)).get(ViajesViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viajes, container, false);
        View view = binding.getRoot();
        coordinatorLayout = getActivity().findViewById(R.id.coordinator);
        //View root = inflater.inflate(R.layout.fragment_viajes, container, false);
        recibirCorreoPreferencias();
        viewModel.getListaViajesCamioneroMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Viajes>>() {
            @Override
            public void onChanged(ArrayList<Viajes> viajes) {
                adaptadorRecicledViewViajes = new AdaptadorRecicledViewViajes(viajes);
                //CREO UN LAYOUT LINEAL PARA QUE SE VEA LA LISTA EN VERTICAL Y LAS TARJETAS UNA DEBAJO DE LA OTRA
                binding.rvListaViajes.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvListaViajes.setAdapter(adaptadorRecicledViewViajes);
                activarDeslizarParaBorrar();
            }
        });
        //LLAMAR AL METODO DEL VIEWMODEL QUE CARGA EL ARRAYLIST CON LOS DATOS DE LA BASE DE DATOS
        viewModel.getViajesCamionero();
        //BOTON FLOTANTE PARA ANYADIR NUEVOS VIAJES
        btnFlotanteViajes = view.findViewById(R.id.btn_flot_anyadirViaje);
        btnFlotanteViajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NuevoViajeFragment nuevoViajeFragment = new NuevoViajeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.drawer_layout, nuevoViajeFragment).addToBackStack(null).commit();
                Navigation.findNavController(view).navigate(R.id.NuevoViajeFragment);
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

        // ESTE METODO SE LLAMA SI MI FRAGMENTO ESTÁ ACTIVO Y SIRVE PARA MANEJAR EL EVENTO DEL BOTON ATRÁS
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setIcon(R.drawable.logo);
                builder.setPositiveButton(R.string.Si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //        CERRAR LA APP
                        Intent a = new Intent(Intent.ACTION_MAIN);
                        a.addCategory(Intent.CATEGORY_HOME);
                        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(a);

                    }
                });
                builder.setNegativeButton(R.string.No, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // BOTON CANCELAR
                    }
                });
                builder.setMessage(R.string.mensajeSalirApp)
                        .setTitle(R.string.titSalirApp);
                dialog = builder.create();
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface arg0) {
                        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorNaranja));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorNaranja));
                    }
                });
                dialog.show();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo", "prueba@gmail.com");
        viewModel.getCorreoUsuarioPreferencias().setValue(correoUsuario);
        //txt.setText(viewModel.getCorreoUsuarioPreferencias().getValue());
        principalActivity.getCamionero().setCorreo(correoUsuario);
        //txt.setText(principalActivity.getCamionero().getCorreo());
    }

    @Override
    public void listaVacia() {
        binding.txtNoViajes.setText("Aún no tienes ningún viaje programado.");
    }

    private void activarDeslizarParaBorrar() {
        DeslizarParaBorrarRV deslizarParaBorrarRV = new DeslizarParaBorrarRV(this.getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
//                final String item = String.valueOf(adaptadorRecicledViewViajes.getListaViajes().get(position));
                //                PASAR EL ID DEL VIAJE PARA PODER BORRARLO DE LA BASE DE DATOS
                viewModel.getMutableIdViajes().setValue(adaptadorRecicledViewViajes.getListaViajes().get(position).getIdViajes());
                viewModel.borrarViaje();
                adaptadorRecicledViewViajes.removeItem(position);
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, R.string.ElementoBorrado, Snackbar.LENGTH_LONG);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(deslizarParaBorrarRV);
        itemTouchhelper.attachToRecyclerView(binding.rvListaViajes);
    }

}
