
package com.db;

import classes.SolicitudRevocacionSuspension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBSolicitudes {
    
    private final String SELECT_SRS = "select rs.id, rs.aprovada, rs.codigo_usuario, rs.fecha_suspension, rs.fecha_aprovacion, uf.suspendido, u.nombre, u.username from revocacion_suspension as rs inner join usuario_final as uf on rs.codigo_usuario = uf.codigo inner join usuario as u on uf.codigo = u.codigo order by rs.aprovada; ";
    
    private final String UPDATE_SOLICITUD = "update revocacion_suspension set fecha_aprovacion = CURRENT_TIMESTAMP, aprovada = true where id = ?;";
    private final String UPDATE_USUARIO = "update usuario_final set suspendido = false, fecha_suspension = null where codigo = ?;";
    private Connection conexion;
    
    
    public DBSolicitudes() throws SQLException {
        conexion = new DB().getConexion();
    }
    
    public ArrayList<SolicitudRevocacionSuspension> getSolicitudes() {
        
        try {
            ArrayList<SolicitudRevocacionSuspension> listaSrs = new ArrayList<>();

            PreparedStatement select = conexion.prepareStatement(SELECT_SRS);
            ResultSet result = select.executeQuery();

            if (!result.isBeforeFirst()) { // el result esta vacio
                System.out.println("Hubo un error, no encontro los parametros"); //TODO:
                return null;
            }

            while(result.next()){
                SolicitudRevocacionSuspension srs = new SolicitudRevocacionSuspension(
                        result.getInt("id"),
                        result.getBoolean("aprovada"),
                        result.getInt("codigo_usuario"),
                        result.getDate("fecha_suspension"),
                        result.getDate("fecha_aprovacion"),
                        result.getBoolean("suspendido"),
                        result.getString("nombre"),
                        result.getString("username")
                );
                listaSrs.add(srs);
            }

            return listaSrs;
            


        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
        
    }
    
    public void aprovar (String id, String codigoUsuario) {
        try {
            PreparedStatement updateSRS = conexion.prepareStatement(UPDATE_SOLICITUD);
            updateSRS.setString(1, id);
            updateSRS.executeUpdate();
            
            PreparedStatement updateUsuario = conexion.prepareStatement(UPDATE_USUARIO);
            updateUsuario.setString(1, codigoUsuario);
            updateUsuario.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
