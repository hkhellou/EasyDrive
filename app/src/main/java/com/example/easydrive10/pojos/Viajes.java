package com.example.easydrive10.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Viajes {
    @SerializedName("Id_viajes")
    @Expose
    private String idViajes;
    @SerializedName("Pais_Destino")
    @Expose
    private String paisDestino;
    @SerializedName("Nombre_Empresa_Destino")
    @Expose
    private String nombreEmpresaDestino;
    @SerializedName("Direccion")
    @Expose
    private String direccion;
    @SerializedName("Fecha_Salida")
    @Expose
    private String fechaSalida;
    @SerializedName("Fecha_Llegada")
    @Expose
    private String fechaLlegada;
    @SerializedName("Camionero")
    @Expose
    private String camionero;

    public String getIdViajes() {
        return idViajes;
    }

    public void setIdViajes(String idViajes) {
        this.idViajes = idViajes;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }

    public String getNombreEmpresaDestino() {
        return nombreEmpresaDestino;
    }

    public void setNombreEmpresaDestino(String nombreEmpresaDestino) {
        this.nombreEmpresaDestino = nombreEmpresaDestino;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getCamionero() {
        return camionero;
    }

    public void setCamionero(String camionero) {
        this.camionero = camionero;
    }

    @Override
    public String toString() {
        return "Viajes{" +
                "idViajes='" + idViajes + '\'' +
                ", paisDestino='" + paisDestino + '\'' +
                ", nombreEmpresaDestino='" + nombreEmpresaDestino + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", fechaLlegada='" + fechaLlegada + '\'' +
                ", camionero='" + camionero + '\'' +
                '}';
    }
}
