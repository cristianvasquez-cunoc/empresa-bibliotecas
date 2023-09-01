package com.db.administacion;

import classes.Libro;
import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAdministracion {

    private final String SELECT_LIBROS = "SELECT l.isbn, l.nombre, l.autor, l.costo, c.name as categoria FROM libro as l inner join categoria as c on l.categoria = c.codigo;";

    private Connection conexion;

    public DBAdministracion() throws SQLException {
        conexion = new DB().getConexion();
    }

    public ArrayList<Libro> getAllLibros() {
        ArrayList<Libro> libros = new ArrayList<Libro>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_LIBROS);
            ResultSet result = select.executeQuery();
            
            while(result.next()) {
                
                Libro libro = new Libro(
                        Integer.valueOf(result.getString("isbn")),
                        result.getString("nombre"),
                        result.getString("autor"),
                        result.getString("categoria"),
                        Double.valueOf(result.getString("costo"))
                );
                
                libros.add(libro);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return libros;
    }

}
