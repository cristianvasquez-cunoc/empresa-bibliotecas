
package classes;

public class UnidadesLibroBiblioteca extends Biblioteca {

    private int id;
    private int unidades;
    private int isbn;
    
    public UnidadesLibroBiblioteca(String nombre, String direccion, int unidades, int id, int isbn) {
        super(nombre, direccion);
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
