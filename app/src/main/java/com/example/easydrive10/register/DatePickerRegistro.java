package com.example.easydrive10.register;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.easydrive10.R;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class DatePickerRegistro extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private DatePickerDialog.OnDateSetListener listener;

    public static DatePickerRegistro newInstance(DatePickerDialog.OnDateSetListener listener) {
        DatePickerRegistro fragment = new DatePickerRegistro();
        fragment.setListener(listener);
        return fragment;
    }

    public void setListener(DatePickerDialog.OnDateSetListener listener) {
        this.listener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
//        FECHA MINIMA HASTA HACE 21 AÑOS
        c.add(Calendar.YEAR,-21);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this.getContext(), R.style.TemaDatePicker, listener, year, month, day);
//        PARA QUE NO SE PUEDA SELECCIONAR UNA FECHA ANTES DEL DIA ACTUAL
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        // Create a new instance of DatePickerDialog and return it
        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}
