package com.example.easydrive10.retrofit;

import com.example.easydrive10.pojos.Camionero;
import com.example.easydrive10.pojos.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

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

}
