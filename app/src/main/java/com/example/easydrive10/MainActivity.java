package com.example.easydrive10;

import android.os.Bundle;
import android.widget.TextView;

import com.example.easydrive10.pojos.Camionero;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity implements IMainInterface{
    private MainViewModel viewModel;
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this,new MainViewModelFactory(this,this)).get(MainViewModel.class);
        texto = findViewById(R.id.txt_Prueba);
        viewModel.getPresidentesMutables().observe(this, (Observer<? super ArrayList<Camionero>>) new Observer<ArrayList<Camionero>>() {
            @Override
            public void onChanged(ArrayList<Camionero> camionero) {
                texto.setText(camionero.toString());
            }
        });
        viewModel.getPresidente();
   }

    @Override
    public void loading() {

    }

    @Override
    public void onError(int error) {

    }
}
