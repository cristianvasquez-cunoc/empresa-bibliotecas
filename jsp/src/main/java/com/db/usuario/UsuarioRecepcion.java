
package com.db.usuario;

public class UsuarioRecepcion extends Usuario {
    
    private int codigoBiblioteca;
    private String biblioteca;
    private String direccion;

    public UsuarioRecepcion(int codigoBiblioteca, String biblioteca, String direccion, int codigo, String nombre, String username, String email) {
        super(codigo, nombre, username, email);
        this.codigoBiblioteca = codigoBiblioteca;
        this.biblioteca = biblioteca;
        this.direccion = direccion;
    }
    

    public int getBibliotecaCodigo() {
        return codigoBiblioteca;
    }

    public String getBiblioteca() {
        return biblioteca;
    }

    public String getDireccion() {
        return direccion;
    }
    
}
