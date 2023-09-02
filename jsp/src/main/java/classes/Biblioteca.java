
package classes;

public class Biblioteca {
    
    protected String codigo;
    protected String nombre;
    protected String direccion;

    public Biblioteca(String codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigo() {
        return codigo;
    }
    
}
