package com.example.easydrive10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.easydrive10.login.LoginActivity;
import com.example.easydrive10.principal.PrincipalActivity;

public class Redireccion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redireccion);
        new Handler().postDelayed(new Runnable() {
//            ESTE METODO TARDA 2 SEGUNDOS EN EJECUTARSE
//            MUESTRA EL LOGO COMO PANTALLA DE CARA Y REDIRIGE A LA ACTIVIDAD DE INICIO DE SESION
//            O A LA PRINCIPAL DEPENDIENDO DE SI ESTAN GUARDADOS LOS DATOS DE ACCESO DE USUARIO
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
                boolean sesion = sharedPreferences.getBoolean("sesion", false);
                if(sesion){
                    Intent i = new Intent(getApplicationContext(), PrincipalActivity.class);
                    startActivity(i);
                }else{
                    Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(i);
                }
            }

        },2000);
    }
}
