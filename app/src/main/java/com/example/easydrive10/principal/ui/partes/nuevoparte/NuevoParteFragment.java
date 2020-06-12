package com.example.easydrive10.principal.ui.partes.nuevoparte;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.easydrive10.R;
import com.example.easydrive10.databinding.FragmentNuevoParteBinding;

import java.io.ByteArrayOutputStream;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class NuevoParteFragment extends Fragment implements INuevoParteInterface {
    private NuevoParteViewModel nuevoParteViewModel;
    private FragmentNuevoParteBinding binding;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap imagen;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        nuevoParteViewModel = new ViewModelProvider(this, new NuevoParteViewModelProvider(this, this)).get(NuevoParteViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_nuevo_parte, container, false);
        view = binding.getRoot();
        recibirCorreoPreferencias();
        nuevoParteViewModel.getMutableCamion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.txtCamion.setText(nuevoParteViewModel.getMutableCamion().getValue());
            }
        });
        nuevoParteViewModel.getCamionero();
        int comprobarPermisoUbicacion = ContextCompat.checkSelfPermission(this.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
        if (comprobarPermisoUbicacion == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this.getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }

        binding.btnAnyadirImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSelecImg();
            }
        });
        binding.btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        binding.txtUbicacion.setText(location.getLatitude() + " " + location.getLongitude());
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                };
                int comprobarPermisoUbicacion = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION);
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        });
        binding.btnCrearParte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoParteViewModel.getMutableNombre().setValue(binding.txtNombreParte.getText().toString());
                nuevoParteViewModel.getMutableDescripcion().setValue(binding.txtDescripcion.getText().toString());
                nuevoParteViewModel.getMutableLocalizacion().setValue(binding.txtUbicacion.getText().toString());
                nuevoParteViewModel.insertarParte();
            }
        });
        binding.setLifecycleOwner(this);
        return view;
    }

    private void mostrarSelecImg() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecciona una Imagen"), PICK_IMAGE_REQUEST);
    }

//    @SuppressLint("RestrictedApi")
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode) {
//            case 1:
//                if (resultCode == Activity.RESULT_OK) {
//                    InputStream stream;
//                    try {
//                        stream = getActivity().getContentResolver().openInputStream(data.getData());
//                        imagen = BitmapFactory.decodeStream(stream);
//                        getStringImagen(imagen);
//                        stream.close();
//                        imagen = Bitmap.createScaledBitmap(imagen, 500, 400, true);
//                        if (imagen == null) {
//                            Toast.makeText(this.getContext(), "imagen vacia", Toast.LENGTH_SHORT).show();
//                        } else {
//                        }
//                    } catch (Exception e) {
//
//                        e.printStackTrace();
//                        Toast.makeText(this.getContext(), "Ocurrio un problema al guardar tu imagen.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                break;
//            case 2:
//        }
//    }


    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correoUsuario = sharedPreferences.getString("correo", "prueba@gmail.com");
        nuevoParteViewModel.getMutableCorreo().setValue(correoUsuario);
    }

    @Override
    public void respuestaInsertarParte() {
        Toast.makeText(getContext(), "Parte Insertado", Toast.LENGTH_SHORT).show();
        Navigation.findNavController(view).navigate(R.id.nav_partes);
    }
}
