package com.example.easydrive10.retrofit;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.pojos.Gastos;
import com.example.easydrive10.pojos.Partes;
import com.example.easydrive10.pojos.Usuario;
import com.example.easydrive10.pojos.Viajes;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MiServicio {
    //    @GET SIRVE PARA INDICAR EL PHP QUE VAMOS A USAR PARA LA CONSULTA
    @GET("cogerPresidentes.php")
//    Call LLAMADA AL GET, Y DEVUELVE LO QUE HAY DENTRO DEL CALL
    Call<ArrayList<Camionero>> getCamioneros();

    //    @GET SIRVE PARA INDICAR EL PHP QUE VAMOS A USAR PARA LA CONSULTA
    @GET("consultaCamioneros.php")
//    Call LLAMADA AL GET, Y DEVUELVE LO QUE HAY DENTRO DEL CALL
    Call<ArrayList<Camionero>> getPresidente();

    //    CONSULTA USUARIOS
    @GET("consultaUsuarios.php")
    Call<ArrayList<Usuario>> getUsuarios();

    @GET("consularGastosCamioneroViaje.php")
    Call<ArrayList<Gastos>> obtenerGastosCamionero(@Query("correo") String Scorreo);

    //   INSERTAR EN BBDD
    @GET("insertarUsuario.php")
    Call<Void> insertarUsuario(@Query("correo") String SCorreo, @Query("contrasenia") String SContrasenia,@Query("nombre") String nombre, @Query("apellidos") String apellidos,@Query("dni") String dni, @Query("fec_nacimiento") String fecNacimiento,@Query("camion") String camion);

    @GET("comprobarUsuarioExiste.php")
    Call<Usuario> comprobarUsuario(@Query("correo") String SCorreo, @Query("contrasenia") String SContrasenia);

    @GET("viajesCamionero.php")
    Call<ArrayList<Viajes>> mostrartViajesCamionero(@Query("correo") String Scorreo);

    //    OBTENER CAMIONEROS POR CORREO
    @GET("consultarCamionerosPorCorreo.php")
    Call<Camionero> getCamionerosPorCorreo(@Query("correo") String Scorreo);

    //     OBTENER PARTES POR CAMIONERO
    @GET("consultarPartes.php")
    Call<ArrayList<Partes>> mostrarPartes(@Query("correo") String correo);

    //   OBTENER GASTOS POR VIAJE Y CAMIONERO
    @GET("insertarViajeCamionero.php")
    Call<Void> insertarViajes(@Query("pais_destino") String PaisDestino, @Query("nombre_empresa_destino") String NombreEmpresaDestino, @Query("direccion") String DireccionDestino, @Query("fecha_salida") String FechaSalida, @Query("fecha_llegada") String FechaLlegada, @Query("camionero") String Camionero);
//    INSERTAR GASTOS
    @GET("insertarGastos.php")
    Call<Void> insertarGastos(@Query("nombre") String nombre, @Query("combustible") double combustible, @Query("peajes") double peajes, @Query("comida") double comida, @Query("otros_gastos") double otros_gastos, @Query("camionero") String camionero,@Query("total_gastos") double total_gastos);
//  INSERTAR PARTES
    @GET("insertarNuevoParte.php")
    Call<Void> insertarParte(@Query("localizacion") String localizacion, @Query("descripcion") String descripcion, @Query("camion") String camion, @Query("camionero") String camionero, @Query("nombre") String nombre);
    @GET("borrarViaje.php")
    Call<Void> borrarViaje(@Query("id_viajes") String id_viaje);
    @GET("borrarParte.php")
    Call<Void> borrarParte(@Query("id_partes") String id_viaje);
    @GET("borrarGasto.php")
    Call<Void> borrarGasto(@Query("id_gastos") String id_gasto);
}
