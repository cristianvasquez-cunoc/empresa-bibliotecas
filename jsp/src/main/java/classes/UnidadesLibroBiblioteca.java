
package classes;

public class UnidadesLibroBiblioteca extends Biblioteca {

    private int id;
    private int unidades;
    private int isbn;
    private String biblioteca;
    
    public UnidadesLibroBiblioteca(String biblioteca, String nombre, String direccion, int unidades, int id, int isbn) {
        super(biblioteca, nombre, direccion);
        this.id = id;
        this.unidades = unidades;
        this.isbn = isbn;
    }

    public int getUnidades() {
        return unidades;
    }

    public int getId() {
        return id;
    }

    public int getIsbn() {
        return isbn;
    }
    
}
