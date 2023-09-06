package com.db.usuariofinal;

import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUsuarioFinal {

    private Connection conexion;
    
    private final String INSERT_USUARIO = "insert into usuario (nombre, username, password, email, rol, activo) values (?, ?, ?, ?, 1, true);";
    private final String INSERT_USUARIO_FINAL = "insert into usuario_final (codigo, suscrito, suspendido, saldo) values (?, false, false, ?);";

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

}
