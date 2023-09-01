
package com.db.usuario;

public enum Rol {
    
    FINAL(1),
    ADMIN(2),
    SECRETARIA(3),
    TRANSPORTISTA(4);
    
    private final int value;

    private Rol(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
}
