package com.example.easydrive10.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partes {
    @SerializedName("Id_Partes")
    @Expose
    private String idPartes;
    @SerializedName("Localizacion")
    @Expose
    private String localizacion;
    @SerializedName("Descripcion")
    @Expose
    private String descripcion;
    @SerializedName("Camion")
    @Expose
    private String camion;
    @SerializedName("Empresa")
    @Expose
    private String empresa;
    @SerializedName("Camionero")
    @Expose
    private String camionero;
    @SerializedName("Nombre")
    @Expose
    private String nombre;

    public String getIdPartes() {
        return idPartes;
    }

    public void setIdPartes(String idPartes) {
        this.idPartes = idPartes;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCamion() {
        return camion;
    }

    public void setCamion(String camion) {
        this.camion = camion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCamionero() {
        return camionero;
    }

    public void setCamionero(String camionero) {
        this.camionero = camionero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
