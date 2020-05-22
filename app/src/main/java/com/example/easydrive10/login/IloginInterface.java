package com.example.easydrive10.login;

public interface IloginInterface{
    void existeUsuario();
    void noExisteUsuario();
    void falloServidor();
    void mandarPaginaRegistro();
    void guardarUsuarioPreferencias();
    boolean recuperarUsuarioPreferencias();
    void guardarCredenciales();
}
