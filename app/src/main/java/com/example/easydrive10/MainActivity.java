package com.example.easydrive10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMainInterface{
    private MainViewModel viewModel;
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this,new MainViewModelFactory(this,this)).get(MainViewModel.class);
        texto = findViewById(R.id.txt_Prueba);
        viewModel.getPresidentesMutables().observe(this, new Observer<ArrayList<Presidente>>() {
            @Override
            public void onChanged(ArrayList<Presidente> presidentes) {
                texto.setText(presidentes.toString());
            }
        });
 //       viewModel.getPresidentes();
//        viewModel.getPresidenteMutable().observe(this, new Observer<Presidente>() {
//            @Override
//            public void onChanged(Presidente presidente) {
//                texto.setText(presidente.toString());
//            }
//        });
        viewModel.getPresidente();
   }

    @Override
    public void loading() {

    }

    @Override
    public void onError(int error) {

    }
}
