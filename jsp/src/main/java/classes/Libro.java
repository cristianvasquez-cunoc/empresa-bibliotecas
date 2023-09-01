
package classes;

public class Libro {
    int isbn;
    String nombre;
    String autor;
    String categoria;
    double costo;

    public Libro(int isbn, String nombre, String autor, String categoria, double costo) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getCosto() {
        return costo;
    }
    
    
    
    
}
