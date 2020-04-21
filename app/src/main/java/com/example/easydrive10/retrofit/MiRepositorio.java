package com.example.easydrive10.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiRepositorio {
    //SINGLETON PARA TENER UNA UNICA INSTANCIA PARA EL ACCESO AL SERVIDOR
    private static final String ROOTURL = "http://reversoappdb.ddns.net:8080/houssam/php/";
    private static final long TIME_OUT = 10;

    private static Retrofit getRetrofitInstance(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().writeTimeout(TIME_OUT, TimeUnit.SECONDS).readTimeout(TIME_OUT, TimeUnit.SECONDS).connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();

        return new Retrofit.Builder().baseUrl(ROOTURL).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
    }

    public static MiServicio getMiServicio(){
        return getRetrofitInstance().create(MiServicio.class);
    }
}
