package com.example.easydrive10.principal.ui.viajes.nuevoviaje;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentNavNuevoViajeBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


public class NuevoViajeFragment extends Fragment implements INuevoViaje {
    private FragmentNavNuevoViajeBinding binding;
    private String fechaSeleccionada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nav_nuevo_viaje, container, false);
        View view = binding.getRoot();
        binding.datePickerFechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(binding.datePickerFechaSalida);
            }
        });
        binding.datePickerFechaLlegada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(binding.datePickerFechaLlegada);
            }
        });
        return view;
    }
    private void showDatePickerDialog(final EditText editText) {
//        LLAMAR  A LA CLASE PARA MOSTRAR EL CALENDARIO
        DatePickerFragment fragmentoFecha = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
//                ESTA ES LA FECHA QUE INSERTO EN LA BASE DE DATOS
              fechaSeleccionada = year + "-" + (month + 1) + "-" + day;
//              ESTA ES LA FECHA QUE MUESTRO EN EL EDIT TEXT
              String fechaMostrada = twoDigits(day)+" / " + twoDigits(month+1) + " / " + year;
              editText.setText(fechaMostrada);
            }
        });
        fragmentoFecha.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
    private String twoDigits(int n) {
        return (n<=9) ? ("0"+n) : String.valueOf(n);
    }
}
