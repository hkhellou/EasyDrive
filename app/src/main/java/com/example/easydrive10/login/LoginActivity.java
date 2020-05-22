package com.example.easydrive10.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ActivityLoginBinding;
import com.example.easydrive10.principal.PrincipalActivity;
import com.example.easydrive10.register.RegisterActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity implements IloginInterface {
    private LoginViewModel viewModel;
    private Button btnLoginEnter;
    private TextView txtEmail,txtPass;
    private  boolean existe=false;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        if(recuperarUsuarioPreferencias()){
//            SI EL SWITCH ESTA ACTIVADO ENTRO DIRECTAMENTE SIN PASAR POR EL LOGIN
            Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
            startActivity(i);
            this.finish();
        }
        btnLoginEnter= findViewById(R.id.btnEnterLogin);
        txtEmail = findViewById(R.id.txtLoginEmail);
        txtPass = findViewById(R.id.txtLoginPass);
        viewModel = new ViewModelProvider(this,new LoginViewModelFactory(this,this)).get(LoginViewModel.class);
        viewModel.getMutableCorreo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!Patterns.EMAIL_ADDRESS.matcher(s).matches()){
                    txtEmail.setError("Inserte un correo valido");
                }
            }
        });
        guardarCredenciales();
           binding.setLoginViewModel(viewModel);
           binding.setLifecycleOwner(this);
            }
//        });

    @Override
    public void existeUsuario() {
        String correo = viewModel.getMutableCorreo().getValue();
        Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
        i.putExtra("correoExtra",correo);
        guardarUsuarioPreferencias();
        guardarCredenciales();
        startActivity(i);
//        PARA FINALIZAR LA ACTIVIDAD ACTUAL Y DEJAR SOLO LO QUE SE VA A ABRIR
        this.finish();
//        txtEmail.setText("");
//        txtPass.setText("");

    }

    @Override
    public void noExisteUsuario() {
        Toast.makeText(this, "No Existe", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void falloServidor() {
        Toast.makeText(this, "Fallo del Servidor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mandarPaginaRegistro() {
        Intent iPaginaRegistro = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(iPaginaRegistro);
    }

    @Override
    public void guardarUsuarioPreferencias() {
//        AQUI GUARDO EN LA CARPETA DE PREFERENCIAS EL CORREO Y LA CONTRASEÑA DEL USUARIO
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("correo",viewModel.getMutableCorreo().getValue());
        editor.putString("contrasenia",viewModel.getMutableContrasenia().getValue());
//        editor.putBoolean("sesion",true);
//        PARA GUARDAR TODOS LOS CAMBIOS
        editor.commit();
    }

    @Override
    public boolean recuperarUsuarioPreferencias() {
        SharedPreferences sharedPreferences  = getSharedPreferences("preferenciasUsuario",Context.MODE_PRIVATE);
        boolean sesion =sharedPreferences.getBoolean("sesion",false);
        return sesion;
    }

    @Override
    public void guardarCredenciales() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("sesion", binding.switchCredenciales.isChecked());
        editor.commit();
//        recuperarUsuarioPreferencias();
    }
}
