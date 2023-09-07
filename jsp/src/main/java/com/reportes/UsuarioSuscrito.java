
package com.reportes;

public class UsuarioSuscrito {
    
    String nombre;
    String username;
    String email;
    boolean suscrito;

    public UsuarioSuscrito(String nombre, String username, String email, boolean suscrito) {
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.suscrito = suscrito;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSuscrito() {
        return suscrito;
    }
    
    
    
}
