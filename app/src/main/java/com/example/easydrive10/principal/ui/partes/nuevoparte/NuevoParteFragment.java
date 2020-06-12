package com.example.easydrive10.principal.ui.partes.nuevoparte;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentNuevoParteBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class NuevoParteFragment extends Fragment implements INuevoParteInterface {
    private NuevoParteViewModel nuevoParteViewModel;
    private FragmentNuevoParteBinding binding;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap imagen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nuevo_parte, container, false);
        View view = binding.getRoot();
        nuevoParteViewModel = new ViewModelProvider(this, new NuevoParteViewModelProvider(this, this)).get(NuevoParteViewModel.class);
        binding.btnAnyadirImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });
        binding.btnSubirImagenPartes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return view;
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una Imagen"), PICK_IMAGE_REQUEST);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK) {
                    InputStream stream;
                    try {
                        stream = getActivity().getContentResolver().openInputStream(data.getData());
                        imagen = BitmapFactory.decodeStream(stream);
                        getStringImagen(imagen);
                        stream.close();
                        imagen = Bitmap.createScaledBitmap(imagen, 500, 400, true);
                        if (imagen == null) {
                            Toast.makeText(this.getContext(), "imagen vacia", Toast.LENGTH_SHORT).show();
                        } else {
                            uploadImage();
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                        Toast.makeText(this.getContext(), "Ocurrio un problema al guardar tu imagen.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 2:
        }
    }

    private void uploadImage() {

    }

    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}
