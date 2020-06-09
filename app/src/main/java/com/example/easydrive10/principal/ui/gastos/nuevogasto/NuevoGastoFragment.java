package com.example.easydrive10.principal.ui.gastos.nuevogasto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentNuevoGastoBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class NuevoGastoFragment extends Fragment implements INuevoGastoInterface {
    private NuevoGastoViewModel nuevoGastoViewModel;
    private FragmentNuevoGastoBinding binding;
    private Double totalCombustible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nuevo_gasto, container, false);
        View view = binding.getRoot();
        totalCombustible = 0.0;
        binding.btnSumarCombustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.txtValorCombustible.getText().toString().isEmpty()){
                    binding.txtValorCombustible.setText("0.0");
                }
                totalCombustible += Double.valueOf(binding.txtValorCombustible.getText().toString());
                binding.txtValorCombustible.setText("");
            }
        });
        binding.btnTotalCombustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtTotalCombustible.setText(totalCombustible.toString());
            }
        });
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo", "prueba@gmail.com");
    }
}
