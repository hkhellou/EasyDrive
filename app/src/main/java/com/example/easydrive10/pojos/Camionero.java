package com.example.easydrive10.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Camionero {
    @SerializedName("Correo")
    @Expose
    private String correo;
    @SerializedName("Contrasenia")
    @Expose
    private String contrasenia;
    @SerializedName("Nombre")
    @Expose
    private Object nombre;
    @SerializedName("Apellidos")
    @Expose
    private Object apellidos;
    @SerializedName("Dni")
    @Expose
    private Object dni;
    @SerializedName("Fec_Nacimiento")
    @Expose
    private Object fecNacimiento;
    @SerializedName("Camion")
    @Expose
    private Object camion;
    @SerializedName("Empresa")
    @Expose
    private Object empresa;
    @SerializedName("Partes")
    @Expose
    private Object partes;
    @SerializedName("Gastos")
    @Expose
    private Object gastos;
    @SerializedName("Viajes")
    @Expose
    private Object viajes;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Object getNombre() {
        return nombre;
    }

    public void setNombre(Object nombre) {
        this.nombre = nombre;
    }

    public Object getApellidos() {
        return apellidos;
    }

    public void setApellidos(Object apellidos) {
        this.apellidos = apellidos;
    }

    public Object getDni() {
        return dni;
    }

    public void setDni(Object dni) {
        this.dni = dni;
    }

    public Object getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Object fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public Object getCamion() {
        return camion;
    }

    public void setCamion(Object camion) {
        this.camion = camion;
    }

    public Object getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Object empresa) {
        this.empresa = empresa;
    }

    public Object getPartes() {
        return partes;
    }

    public void setPartes(Object partes) {
        this.partes = partes;
    }

    public Object getGastos() {
        return gastos;
    }

    public void setGastos(Object gastos) {
        this.gastos = gastos;
    }

    public Object getViajes() {
        return viajes;
    }

    public void setViajes(Object viajes) {
        this.viajes = viajes;
    }

    @Override
    public String toString() {
        return "Camionero{" +
                "correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", nombre=" + nombre +
                ", apellidos=" + apellidos +
                ", dni=" + dni +
                ", fecNacimiento=" + fecNacimiento +
                ", camion=" + camion +
                ", empresa=" + empresa +
                ", partes=" + partes +
                ", gastos=" + gastos +
                ", viajes=" + viajes +
                '}';
    }
}
