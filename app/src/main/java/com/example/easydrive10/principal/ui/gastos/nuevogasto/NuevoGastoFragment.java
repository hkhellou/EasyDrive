package com.example.easydrive10.principal.ui.gastos.nuevogasto;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentNuevoGastoBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class NuevoGastoFragment extends Fragment implements INuevoGastoInterface {
    private NuevoGastoViewModel nuevoGastoViewModel;
    private FragmentNuevoGastoBinding binding;
    private Double totalGastos, totalCombustible, totalPeajes, totalComida, totalOtrosGastos;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nuevo_gasto, container, false);
        nuevoGastoViewModel = new ViewModelProvider(this, new NuevoGastoViewModelFactory(this, this)).get(NuevoGastoViewModel.class);
        view = binding.getRoot();
        totalGastos = 0.0;
        totalCombustible = 0.0;
        totalPeajes = 0.0;
        totalComida = 0.0;
        totalOtrosGastos = 0.0;
////        CALCULAR COMBUSTIBLE
//        calcular(binding.btnSumarCombustible, binding.txtValorCombustible);
//        total(binding.btnTotalCombustible, binding.txtTotalCombustible);
////        CALCULAR PEAJES
//        calcular(binding.btnSumarPeajes, binding.txtValorPeajes);
//        total(binding.btnTotalPeajes, binding.txtTotalPeajes);
////        CALCULAR COMIDA
//        calcular(binding.btnSumarComida, binding.txtValorComida);
//        total(binding.btnTotalComida, binding.txtTotalComida);
////        CALCULAR OTROS GASTOS
//        calcular(binding.btnSumarOtrosGastos, binding.txtValorOtrosGastos);
//        total(binding.btnTotalOtrosGastos, binding.txtTotalOtrosGastos);
        recibirCorreoPreferencias();
//        CALCULAR COMBUSTIBLE
        binding.btnSumarCombustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txtValorCombustible.getText().toString().isEmpty()) {
                    binding.txtValorCombustible.setText("0.0");
                }
                totalCombustible += Double.valueOf(binding.txtValorCombustible.getText().toString());
                binding.txtValorCombustible.setText("");
            }
        });
        binding.btnTotalCombustible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtTotalCombustible.setText(totalCombustible.toString() + "€");
                nuevoGastoViewModel.getCombustibleMutable().setValue(totalCombustible.toString());
            }
        });

//        CALCULAR PEAJES
        binding.btnSumarPeajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txtValorPeajes.getText().toString().isEmpty()) {
                    binding.txtValorPeajes.setText("0.0");
                }
                totalPeajes += Double.valueOf(binding.txtValorPeajes.getText().toString());
                binding.txtValorPeajes.setText("");
            }
        });
        binding.btnTotalPeajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtTotalPeajes.setText(totalPeajes.toString() + "€");
                nuevoGastoViewModel.getPeajesMutable().setValue(totalPeajes.toString());
            }
        });
//        CALCULAR COMIDA
        binding.btnSumarComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txtValorComida.getText().toString().isEmpty()) {
                    binding.txtValorComida.setText("0.0");
                }
                totalComida += Double.valueOf(binding.txtValorComida.getText().toString());
                binding.txtValorComida.setText("");
            }
        });
        binding.btnTotalComida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtTotalComida.setText(totalComida.toString() + "€");
                nuevoGastoViewModel.getComidaMutable().setValue(totalComida.toString());
            }
        });
//        CALCULAR OTROS GASTOS
        binding.btnSumarOtrosGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txtValorOtrosGastos.getText().toString().isEmpty()) {
                    binding.txtValorOtrosGastos.setText("0.0");
                }
                totalOtrosGastos += Double.valueOf(binding.txtValorOtrosGastos.getText().toString());
                binding.txtValorOtrosGastos.setText("");
            }
        });
        binding.btnTotalOtrosGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.txtTotalOtrosGastos.setText(totalOtrosGastos.toString() + "€");
                nuevoGastoViewModel.getOtros_gastosMutable().setValue(totalOtrosGastos.toString());
            }
        });
        binding.btnTotalGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoGastoViewModel.getTotal_gastosMutable().setValue(String.valueOf(calcularTotalGastos()));
                binding.txtTotalGastos.setText(nuevoGastoViewModel.getTotal_gastosMutable().getValue());
            }
        });
        binding.btnCrearNuevoGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoGastoViewModel.getNombreMutable().setValue(binding.txtNombreGasto.getText().toString());
                nuevoGastoViewModel.insertarGastos();
            }
        });
        binding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo", "prueba@gmail.com");
        nuevoGastoViewModel.getCamioneroMutable().setValue(correoUsuario);
    }

    @Override
    public void respuestaInsertarViaje() {
        Toast.makeText(getContext(), "Gasto insertado", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.nav_gastos);
    }

    public double calcularTotalGastos() {
        double total = 0;
        total = totalCombustible + totalPeajes + totalComida + totalOtrosGastos;
        return total;
    }
//    public void calcular(ImageButton imageButton, final EditText editText) {
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (editText.getText().toString().isEmpty()) {
//                    editText.setText("0.0");
//                }
//                if (editText.getId() == R.id.txtValorCombustible) {
//                    totalCombustible += Double.valueOf(editText.getText().toString());
//                }
//                if (editText.getId() == R.id.txtValorPeajes) {
//                    totalPeajes += Double.valueOf(editText.getText().toString());
//                }
//                if (editText.getId() == R.id.txtValorComida) {
//                    totalComida += Double.valueOf(editText.getText().toString());
//                }
//                if (editText.getId() == R.id.txtValorOtrosGastos) {
//                    totalOtrosGastos += Double.valueOf(editText.getText().toString());
//                }
//                editText.setText("");
//            }
//        });
//    }
//
//    public void total(ImageButton imageButton, final TextView textView) {
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (textView.getId() == R.id.txtValorCombustible) {
//                    textView.setText(totalCombustible.toString() + "€");
//                }
//                if (textView.getId() == R.id.txtValorPeajes) {
//                    textView.setText(totalPeajes.toString() + "€");
//                }
//                if (textView.getId() == R.id.txtValorComida) {
//                    textView.setText(totalComida.toString() + "€");
//                }
//                if (textView.getId() == R.id.txtValorOtrosGastos) {
//                    textView.setText(totalOtrosGastos.toString() + "€");
//                }
//            }
//        });
//    }
}
