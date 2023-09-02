
package classes;

public class Categoria {
    
    int codigo;
    String name;
    String description;

    public Categoria(int codigo, String name, String description) {
        this.codigo = codigo;
        this.name = name;
        this.description = description;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}
