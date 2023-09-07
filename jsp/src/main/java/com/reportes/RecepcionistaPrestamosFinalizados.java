
package com.reportes;


public class RecepcionistaPrestamosFinalizados {
    
    int codigo;
    String nombre;
    String biblioteca;
    int totalPrestamosFinalizados;

    public RecepcionistaPrestamosFinalizados(int codigo, String nombre, String biblioteca, int totalPrestamosFinalizados) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.biblioteca = biblioteca;
        this.totalPrestamosFinalizados = totalPrestamosFinalizados;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getBiblioteca() {
        return biblioteca;
    }

    public int getTotalPrestamosFinalizados() {
        return totalPrestamosFinalizados;
    }
    
    
    
    
}
