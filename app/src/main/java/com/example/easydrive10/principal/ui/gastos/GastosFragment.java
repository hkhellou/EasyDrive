package com.example.easydrive10.principal.ui.gastos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easydrive10.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class GastosFragment extends Fragment {

    private GastosViewModel gastosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        gastosViewModel =
                ViewModelProviders.of(this).get(GastosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        gastosViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
