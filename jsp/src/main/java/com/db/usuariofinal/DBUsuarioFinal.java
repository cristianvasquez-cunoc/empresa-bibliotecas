package com.db.usuariofinal;

import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUsuarioFinal {

    private Connection conexion;

    private final String INSERT_USUARIO = "insert into usuario (nombre, username, password, email, rol, activo) values (?, ?, ?, ?, 1, true);";
    private final String INSERT_USUARIO_FINAL = "insert into usuario_final (codigo, suscrito, suspendido, saldo) values (?, false, false, ?);";

    private final String INSERT_PRESTAMO = "INSERT INTO prestamo (biblioteca, recepcionista, multa, codigo_usuario, isbn, dias_prestamo) values (?, ?, ?, ?, ?, ?);";
    private final String UPDATE_UNIDADES = "UPDATE unidades_libro set unidades = unidades - 1 where biblioteca = ? and isbn = ?;";

    public DBUsuarioFinal() throws SQLException {
        conexion = new DB().getConexion();
    }

    public int insertUsuario(String nombre, String username, String password, String email) {
        int codigo = -1;

        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, nombre);
            insert.setString(2, username);
            insert.setString(3, password);
            insert.setString(4, email);
            insert.executeUpdate();

            ResultSet llavesGeneradas = insert.getGeneratedKeys();
            if (llavesGeneradas.next()) {
                codigo = llavesGeneradas.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return codigo;

    }

    public void insertUsuarioFinal(String codigo, String saldo) {
        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_USUARIO_FINAL);
            insert.setString(1, codigo);
            insert.setString(2, saldo);
            insert.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public int crearPrestamo(String codigoBiblioteca, String codigoRecepcionista, String multa, String codigoUsuario, String isbn, String diasPrestamo) {
        int codigoPrestamo = -1;
        try {
            PreparedStatement insertPrestamo = conexion.prepareStatement(INSERT_PRESTAMO, Statement.RETURN_GENERATED_KEYS);
            insertPrestamo.setString(1, codigoBiblioteca);
            insertPrestamo.setString(2, codigoRecepcionista);
            insertPrestamo.setString(3, multa);
            insertPrestamo.setString(4, codigoUsuario);
            insertPrestamo.setString(5, isbn);
            insertPrestamo.setString(6, diasPrestamo);
            insertPrestamo.executeUpdate();

            PreparedStatement updateExistencias = conexion.prepareStatement(UPDATE_UNIDADES);
            updateExistencias.setString(1, codigoBiblioteca);
            updateExistencias.setString(2, isbn);
            updateExistencias.executeUpdate();
            
            ResultSet llavesGeneradas = insertPrestamo.getGeneratedKeys();
            if (llavesGeneradas.next()) {
                codigoPrestamo = llavesGeneradas.getInt(1);
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return codigoPrestamo;

    }
    
    public Date getFechaEntrega(int codigoPrestamo) {
        Date fechaEntrega = null;
        try {
            PreparedStatement select = conexion.prepareStatement("SELECT fecha_devolucion from prestamo where codigo = ?");
            select.setString(1, String.valueOf(codigoPrestamo));
            ResultSet result = select.executeQuery();
            
            result.next();
            
            fechaEntrega = result.getDate("fecha_devolucion");
            
        } catch (SQLException ex) {
            Logger.getLogger(DBUsuarioFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaEntrega;
        
    }

    public int getTotalPrestamosUsuario(int codigoUsuario) {
        try {
            String query = "SELECT COUNT(*) AS total_prestamos \n"
                    + "FROM usuario_final AS uf\n"
                    + "JOIN prestamo AS p ON uf.codigo = p.codigo_usuario\n"
                    + "WHERE p.pendiente = 1 and uf.codigo = ?;";
            PreparedStatement select = conexion.prepareStatement(query);
            select.setString(1, String.valueOf(codigoUsuario));
            ResultSet result = select.executeQuery();

            result.next();

            return result.getInt("total_prestamos");

        } catch (SQLException ex) {
            Logger.getLogger(DBUsuarioFinal.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
