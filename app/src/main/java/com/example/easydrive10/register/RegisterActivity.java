package com.example.easydrive10.register;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ActivityRegisterBinding;
import com.example.easydrive10.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class RegisterActivity extends AppCompatActivity implements IRegisterInterface {
    private RegisterViewModel viewModel;
    private TextView txtEmail;
    private String fechaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityRegisterBinding bindingRegister = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = new ViewModelProvider(this, new RegisterViewModelFactory(this, this)).get(RegisterViewModel.class);
        txtEmail = findViewById(R.id.txtRegisterEmail);
        viewModel.getMutableCorreoRegister().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    txtEmail.setError("Inserte un correo valido");
                }
            }
        });
        bindingRegister.datePickerFecNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(bindingRegister.datePickerFecNac);
            }
        });
        bindingRegister.setRegisterViewModel(viewModel);
        bindingRegister.setLifecycleOwner(this);
    }

    @Override
    public void respuestaInsertUsuario() {
        Toast.makeText(this, "Registrado", Toast.LENGTH_SHORT).show();
        Intent mandarPaginaLogin = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(mandarPaginaLogin);
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerRegistro datePickerRegistro = new DatePickerRegistro().newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
//                ESTA ES LA FECHA QUE INSERTO EN LA BASE DE DATOS
                fechaSeleccionada = year + "-" + (month + 1) + "-" + day;
//              ESTA ES LA FECHA QUE MUESTRO EN EL EDIT TEXT
                String fechaMostrada = twoDigits(day) + " / " + twoDigits(month + 1) + " / " + year;
                editText.setText(fechaSeleccionada);
            }
        });
        datePickerRegistro.show(this.getSupportFragmentManager(),"datePicker");
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }
}
