package com.example.easydrive10.principal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.easydrive10.R;
import com.example.easydrive10.login.LoginActivity;
import com.example.easydrive10.pojos.Camionero;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class PrincipalActivity extends AppCompatActivity implements IPrincipalInteface {

    private AppBarConfiguration mAppBarConfiguration;
    private PrincipalViewModel viewModel;
    private DrawerLayout drawer;
    private TextView txtCorreoNav_Header, txtNombreNav_Header;
    private Camionero camionero = new Camionero();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewModel = new ViewModelProvider(this, new PrincipalViewModelFactory(this, this)).get(PrincipalViewModel.class);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_viajes, R.id.nav_gastos, R.id.nav_partes, R.id.NuevoViajeFragment, R.id.btn_cerrarSesion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        CON EL SIGUIENTE CÓDIGO SE CONSIGUE OBTENER EL ID DE UN MENU ITEM Y PODER CREAR UNA ACCION AL PULSAR EN ESTE
        MenuItem btnCerrarSesion = navigationView.getMenu().findItem(R.id.btn_cerrarSesion);
        btnCerrarSesion.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
//                LLAMO AL MÉTODO PARA CERRAR LA SESIÓN
                cerrarSesion();
                return false;
            }
        });
        //        LLAMO A LA VISTA DEL HEADER VIEW
        View header = navigationView.getHeaderView(0);
        txtCorreoNav_Header = header.findViewById(R.id.txt_correoNavMenu);
        txtNombreNav_Header = header.findViewById(R.id.txtNombreHeader);
        recibirCorreoPreferencias();
    }

    @Override
    public void onBackPressed() {

//        METODO PARA QUE CUANDO EL MENU LATERAL ESTE DESPLEGADO Y PULSEMOS ATRAS SE VUELVA A PLEGAR
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void recibirCorreoPreferencias() {
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        String correo = sharedPreferences.getString("correo", "kkk@kkk.com");
        viewModel.getMutableCorreoPrincipal().setValue(correo);
        viewModel.getCamioneroMutable().observe(this, new Observer<Camionero>() {
            @Override
            public void onChanged(Camionero camionero2) {
                camionero = camionero2;
//                RECIBO LOS DATOS DE ESTE USUARIO PARA PONERLOS EN EL HEADER DEL MENU
                txtCorreoNav_Header.setText(camionero.getCorreo());
                txtNombreNav_Header.setText(camionero.getNombre().toString() + " " + camionero.getApellidos().toString());
            }
        });
        viewModel.getCamionero();
        camionero.setCorreo(viewModel.getMutableCorreoPrincipal().getValue());
    }

    @Override
    public void cerrarSesion() {
//        MÉTODO PARA CERRAR LA SESIÓN ACTUAL DEL USUSARIO LO QUE HACE ES BORRAR LOS DATOS DE LA CARPETA SHARED COMPONENTS
        SharedPreferences sharedPreferences = getSharedPreferences("preferenciasUsuario", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
        Intent iLogin = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(iLogin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Camionero getCamionero() {
        return camionero;
    }
}
