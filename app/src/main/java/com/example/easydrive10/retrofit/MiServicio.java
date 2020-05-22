package com.example.easydrive10.retrofit;

import com.example.easydrive10.pojos.Camionero;
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
//   INSERTAR EN BBDD
    @GET("insertarUsuario.php")
    Call<Void> insertarUsuario(@Query("correo")String SCorreo,@Query("contrasenia")String SContrasenia);
    @GET("comprobarUsuarioExiste.php")
    Call<Usuario> comprobarUsuario(@Query("correo")String SCorreo,@Query("contrasenia")String SContrasenia);
    @GET("viajesCamionero.php")
    Call<ArrayList<Viajes>> mostrartViajesCamionero(@Query("correo") String Scorreo);
//    OBTENER CAMIONEROS POR CORREO
    @GET("consultarCamionerosPorCorreo.php")
    Call<Camionero> getCamionerosPorCorreo(@Query("correo") String Scorreo);
}
