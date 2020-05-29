package com.example.easydrive10.principal.ui.viajes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentNavNuevoViajeBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


public class NuevoViajeFragment extends Fragment {
    private FragmentNavNuevoViajeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_nav_nuevo_viaje,container,false);
        View view = binding.getRoot();
        return view;
    }
}
