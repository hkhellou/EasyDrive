package com.example.easydrive10.retrofit;

import com.example.easydrive10.Presidente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MiServicio {
//    @GET SIRVE PARA INDICAR EL PHP QUE VAMOS A USAR PARA LA CONSULTA
    @GET("cogerPresidentes.php")
//    Call LLAMADA AL GET, Y DEVUELVE LO QUE HAY DENTRO DEL CALL
    Call<ArrayList<Presidente>> getPresidentes();
    //    @GET SIRVE PARA INDICAR EL PHP QUE VAMOS A USAR PARA LA CONSULTA
    @GET("cogerPres.php")
//    Call LLAMADA AL GET, Y DEVUELVE LO QUE HAY DENTRO DEL CALL
    Call<ArrayList<Presidente>> getPresidente();
}
