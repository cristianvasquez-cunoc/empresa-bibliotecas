package com.db.usuario;

public class Usuario {

    protected int codigo;
    protected String nombre;
    protected String username;
    protected String password;
    protected String email;
    protected int rol;

    public Usuario(int codigo, String nombre, String username, String password, String email, int rol) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
    }

    public Usuario(int codigo, String nombre, String username, String email, int rol) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.rol = rol;
    }

    public Usuario(int codigo, String nombre, String username, String email) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRol() {
        return rol;
    }

    public String getEmail() {
        return email;
    }

    public int getCodigo() {
        return codigo;
    }
    
    

}
