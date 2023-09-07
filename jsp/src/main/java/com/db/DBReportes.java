package com.db;

import com.reportes.RecepcionistaPrestamosFinalizados;
import com.reportes.ReporteLibro;
import com.reportes.UsuarioSuscrito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBReportes {

    private Connection conexion;

    public DBReportes() throws SQLException {
        conexion = new DB().getConexion();
    }

    public ArrayList<UsuarioSuscrito> getReporteUsuariosUltimos3Meses() {

        ArrayList<UsuarioSuscrito> suscritos = new ArrayList<>();

        try {
            String QUERY = "select u.nombre, u.username, u.email, uf.suscrito from usuario as u inner join usuario_final as uf on u.codigo = uf.codigo where u.rol = 1 and uf.suscrito = 1;";
            PreparedStatement select = conexion.prepareStatement(QUERY);
            ResultSet result = select.executeQuery();

            while (result.next()) {
                UsuarioSuscrito us = new UsuarioSuscrito(result.getString("nombre"), result.getString("username"), result.getString("email"), result.getBoolean("suscrito"));
                suscritos.add(us);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBReportes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return suscritos;

    }

    public ArrayList<RecepcionistaPrestamosFinalizados> getRecepcionistasPrestamosFinalizados() {

        ArrayList<RecepcionistaPrestamosFinalizados> recepcionistas = new ArrayList<>();

        try {
            String QUERY = "SELECT u.codigo, us.nombre, b.nombre as biblioteca, COUNT(p.pendiente) AS total_prestamos_finalizados\n"
                    + "FROM usuario_secretaria AS u\n"
                    + "JOIN prestamo AS p ON u.codigo = p.recepcionista\n"
                    + "INNER JOIN usuario as us on us.codigo = u.codigo\n"
                    + "INNER JOIN biblioteca as b on b.codigo = u.biblioteca\n"
                    + "WHERE p.pendiente = false\n"
                    + "GROUP BY u.codigo\n"
                    + "ORDER BY total_prestamos_finalizados DESC\n"
                    + "LIMIT 5;";
            PreparedStatement select = conexion.prepareStatement(QUERY);
            ResultSet result = select.executeQuery();


            while (result.next()) {
                RecepcionistaPrestamosFinalizados recepcionista = new RecepcionistaPrestamosFinalizados(
                        result.getInt("codigo"),
                        result.getString("nombre"),
                        result.getString("biblioteca"),
                        result.getInt("total_prestamos_finalizados")
                );
                recepcionistas.add(recepcionista);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBReportes.class.getName()).log(Level.SEVERE, null, ex);
        }

        return recepcionistas;

    }

    public ArrayList<ReporteLibro> top10Libros(String fecha1, String fecha2 ) {
        
        ArrayList<ReporteLibro> libros = new ArrayList<>();
        try {
            String SELECT = "SELECT l.nombre, COUNT(p.isbn) AS total_prestamos\n"
                    + "FROM libro AS l\n"
                    + "INNER JOIN prestamo AS p ON l.isbn = p.isbn\n"
                    + "WHERE p.fecha_prestamo >= ? AND p.fecha_prestamo <= ?\n"
                    + "GROUP BY l.nombre\n"
                    + "ORDER BY total_prestamos DESC\n"
                    + "LIMIT 10;";
            PreparedStatement select = conexion.prepareStatement(SELECT);
            select.setString(1, fecha1);
            select.setString(2, fecha2);
            ResultSet result = select.executeQuery();

            while (result.next()) {
                ReporteLibro libro = new ReporteLibro(
                        result.getString("nombre"),
                        result.getInt("total_prestamos")
                );
                libros.add(libro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return libros;
        
    }

}
