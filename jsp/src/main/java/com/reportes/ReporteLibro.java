
package com.reportes;

public class ReporteLibro {
    
    String nombre;
    int totalPrestamos;

    public ReporteLibro(String nombre, int totalPrestamos) {
        this.nombre = nombre;
        this.totalPrestamos = totalPrestamos;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTotalPrestamos() {
        return totalPrestamos;
    }
    
    
    
}
