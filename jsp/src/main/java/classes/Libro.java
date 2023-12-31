
package classes;

public class Libro {
    String isbn;
    String nombre;
    String autor;
    String categoria;
    double costo;
    int codigoCategoria;

    public Libro(String isbn, String nombre, String autor, String categoria, double costo, int codigoCategoria) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.costo = costo;
        this.codigoCategoria = codigoCategoria;
    }
    
    public Libro(String isbn, String nombre, String autor, String categoria, double costo) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.categoria = categoria;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIsbn() {
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

    public int getCodigoCategoria() {
        return codigoCategoria;
    }
    
    
    
    
}
