package com.example.easydrive10.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gastos {
    @SerializedName("Nombre")
    @Expose
    private String nombre;
    @SerializedName("Combustible")
    @Expose
    private String combustible;
    @SerializedName("Peajes")
    @Expose
    private String peajes;
    @SerializedName("Comida")
    @Expose
    private String comida;
    @SerializedName("Otros_Gastos")
    @Expose
    private String otrosGastos;
    @SerializedName("Camionero")
    @Expose
    private String camionero;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getPeajes() {
        return peajes;
    }

    public void setPeajes(String peajes) {
        this.peajes = peajes;
    }

    public String getComida() {
        return comida;
    }

    public void setComida(String comida) {
        this.comida = comida;
    }

    public String getOtrosGastos() {
        return otrosGastos;
    }

    public void setOtrosGastos(String otrosGastos) {
        this.otrosGastos = otrosGastos;
    }

    public String getCamionero() {
        return camionero;
    }

    public void setCamionero(String camionero) {
        this.camionero = camionero;
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "nombre='" + nombre + '\'' +
                ", combustible='" + combustible + '\'' +
                ", peajes='" + peajes + '\'' +
                ", comida='" + comida + '\'' +
                ", otrosGastos='" + otrosGastos + '\'' +
                ", camionero='" + camionero + '\'' +
                '}';
    }
}
