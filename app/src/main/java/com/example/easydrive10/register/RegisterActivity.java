package com.example.easydrive10.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ActivityRegisterBinding;
import com.example.easydrive10.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity implements IRegisterInterface{
    private RegisterViewModel viewModel;
    private TextView txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterBinding bindingRegister = DataBindingUtil.setContentView(this,R.layout.activity_register);
        viewModel = new ViewModelProvider(this,new RegisterViewModelFactory(this,this)).get(RegisterViewModel.class);
        txtEmail = findViewById(R.id.txtRegisterEmail);
        viewModel.getMutableCorreoRegister().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!Patterns.EMAIL_ADDRESS.matcher(s).matches()){
                    txtEmail.setError("Inserte un correo valido");
                }
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
}
