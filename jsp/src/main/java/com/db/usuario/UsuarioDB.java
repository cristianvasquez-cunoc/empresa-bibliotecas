package com.db.usuario;

import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDB {

    private final String INSERT = "INSERT INTO usuario (codigo, nombre, username, password, rol, email) VALUES (?, ?, ?, ?, ?, ?);";
    private final String SELECT_USER = "SELECT * FROM usuario WHERE (username = ? and password = ?)";
    private final String SELECT_BY_CARNET = "SELECT * FROM estudiante WHERE carnet = ?";

    private Connection conexion;

    public UsuarioDB() throws SQLException {
        conexion = new DB().getConexion();
    }

    public boolean enviarInsert(int codigo, String nombre, String username, String password, Rol rol, String email) {

        try {

            PreparedStatement insert = conexion.prepareStatement(INSERT);
            insert.setString(1, String.valueOf(codigo));
            insert.setString(2, nombre);
            insert.setString(3, username);
            insert.setString(4, password);
            insert.setString(5, String.valueOf(rol.getValue()));
            insert.setString(6, email);

            insert.executeQuery();

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public Usuario verificarLoginInformation(String username, String password) {
        try {

            PreparedStatement select = conexion.prepareStatement(SELECT_USER);
            select.setString(1, username);
            select.setString(2, password);
            ResultSet result = select.executeQuery();

            if (!result.isBeforeFirst()) { // el result esta vacio
                System.out.println("El password o nombre de usuario estan incorrectos");
                return null;
            }
            
            result.next();

            Usuario usuario = new Usuario(
                    Integer.valueOf(result.getString("codigo")),
                    result.getString("nombre"),
                    result.getString("username"),
                    result.getString("password"),
                    Integer.valueOf(result.getString("rol"))
            );
            
            return usuario;
            
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
    }
}
