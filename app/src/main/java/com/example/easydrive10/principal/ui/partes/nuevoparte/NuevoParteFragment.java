package com.example.easydrive10.principal.ui.partes.nuevoparte;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easydrive10.R;

import androidx.fragment.app.Fragment;


public class NuevoParteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevo_parte, container, false);
    }
}
