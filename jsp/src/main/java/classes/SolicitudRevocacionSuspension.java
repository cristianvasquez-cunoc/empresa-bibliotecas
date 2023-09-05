
package classes;

import java.util.Date;

public class SolicitudRevocacionSuspension {
    
    int id;
    boolean aprovada;
    int codigoUsuario;
    Date fechaSuspension;
    Date fechaAprovacion;
    boolean usuarioSuspendido;
    String nombreUsuario;
    String usernameUsuario;

    public SolicitudRevocacionSuspension(int id, boolean aprovada, int codigoUsuario, Date fechaSuspension, Date fechaAprovacion, boolean usuarioSuspendido, String nombreUsuario, String usernameUsuario) {
        this.id = id;
        this.aprovada = aprovada;
        this.codigoUsuario = codigoUsuario;
        this.fechaSuspension = fechaSuspension;
        this.fechaAprovacion = fechaAprovacion;
        this.usuarioSuspendido = usuarioSuspendido;
        this.nombreUsuario = nombreUsuario;
        this.usernameUsuario = usernameUsuario;
    }

    public boolean isUsuarioSuspendido() {
        return usuarioSuspendido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getUsernameUsuario() {
        return usernameUsuario;
    }

    public int getId() {
        return id;
    }

    public boolean isAprovada() {
        return aprovada;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public Date getFechaSuspension() {
        return fechaSuspension;
    }

    public Date getFechaAprovacion() {
        return fechaAprovacion;
    }
    
    
}
