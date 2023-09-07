
package com.usuariofinal;

import com.db.usuario.Usuario;
import java.util.Date;

public class UsuarioFinal extends Usuario{
    
    private boolean suscrito;
    private boolean suspendido;
    private double saldo;
    private Date fechaSuspension;

    public UsuarioFinal(boolean suscrito, boolean suspendido, double saldo, Date fechaSuspension, int codigo, String nombre, String username, String password, String email, int rol, String activo) {
        super(codigo, nombre, username, password, email, rol, activo);
        this.suscrito = suscrito;
        this.suspendido = suspendido;
        this.saldo = saldo;
        this.fechaSuspension = fechaSuspension;
    }
    
    

    public boolean isSuscrito() {
        return suscrito;
    }

    public boolean isSuspendido() {
        return suspendido;
    }

    public double getSaldo() {
        return saldo;
    }

    public Date getFechaSuspension() {
        return fechaSuspension;
    }
    
    
    
}
