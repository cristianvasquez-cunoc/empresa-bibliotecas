
package com.recepcion;

import classes.Libro;


public class LibroUnidades extends Libro{
    
    int unidades;

    public LibroUnidades(String isbn, String nombre, String autor, String categoria, double costo, int unidades) {
        super(isbn, nombre, autor, categoria, costo);
        this.unidades = unidades;
    }

    public int getUnidades() {
        return unidades;
    }
    
}
