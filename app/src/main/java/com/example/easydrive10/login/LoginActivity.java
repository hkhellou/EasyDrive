package com.example.easydrive10.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easydrive10.MainActivity;
import com.example.easydrive10.MainViewModel;
import com.example.easydrive10.R;
import com.example.easydrive10.databinding.ActivityLoginBinding;
import com.example.easydrive10.pojos.Usuario;
import com.example.easydrive10.principal.PrincipalActivity;
import com.example.easydrive10.register.RegisterActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements IloginInterface {
    private LoginViewModel viewModel;
    private Button btnLoginEnter;
    private TextView txtEmail,txtPass;
    private  boolean existe=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        btnLoginEnter= findViewById(R.id.btnEnterLogin);
        txtEmail = findViewById(R.id.txtLoginEmail);
        txtPass = findViewById(R.id.txtLoginPass);
        viewModel = new ViewModelProvider(this,new LoginViewModelFactory(this,this)).get(LoginViewModel.class);
//        btnLoginEnter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                viewModel.getListaUsuariosMutable().observe(LoginActivity.this, new Observer<ArrayList<Usuario>>() {
//                    @Override
//                    public void onChanged(ArrayList<Usuario> usuarios) {
//                        String email=txtEmail.getText().toString();
//                        String pass=txtPass.getText().toString();
//                        for(Usuario user : usuarios){
//                            if(user.getCorreo().equals(email)){
//                                if(user.getContrasenia().equals(pass)){
//                                    existe=true;
//                                }
//                            }
//                        }
//                    }
//                });
//                viewModel.getUsuarios();
//                if(existe){
//                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(i);
//                }
//                existe=false;
//                viewModel.getMutableCorreo().setValue(txtEmail.getText().toString());
//                viewModel.getMutableContrasenia().setValue(txtPass.getText().toString());
//                viewModel.insertarUsuario();
        viewModel.getMutableCorreo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!Patterns.EMAIL_ADDRESS.matcher(s).matches()){
                    txtEmail.setError("Inserte un correo valido");
                }
            }
        });
           binding.setLoginViewModel(viewModel);
           binding.setLifecycleOwner(this);
            }
//        });

    @Override
    public void existeUsuario() {
        Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
        startActivity(i);
        txtEmail.setText("");
        txtPass.setText("");

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
}
