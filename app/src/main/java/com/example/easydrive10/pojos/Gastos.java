package com.example.easydrive10.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gastos {
    @SerializedName("Id_Gastos")
    @Expose
    private String idGastos;
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
    @SerializedName("Total_Gastos")
    @Expose
    private String totalGastos;

    public String getIdGastos() {
        return idGastos;
    }

    public void setIdGastos(String idGastos) {
        this.idGastos = idGastos;
    }

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

    public String getTotalGastos() {
        return totalGastos;
    }

    public void setTotalGastos(String totalGastos) {
        this.totalGastos = totalGastos;
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "idGastos='" + idGastos + '\'' +
                ", nombre='" + nombre + '\'' +
                ", combustible='" + combustible + '\'' +
                ", peajes='" + peajes + '\'' +
                ", comida='" + comida + '\'' +
                ", otrosGastos='" + otrosGastos + '\'' +
                ", camionero='" + camionero + '\'' +
                ", totalGastos='" + totalGastos + '\'' +
                '}';
    }
}
