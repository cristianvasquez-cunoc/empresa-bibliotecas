package com.db.transporte;

import com.db.DB;
import com.db.usuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBUsuarioTransporte {

    private final String SELECT_USUARIOS = "select * from usuario where rol = 4;";
    private final String SELECT_USUARIOS_TRANSPORTE_CODIGO = "select * from usuario where codigo = ? and rol = 4;";

    private final String INSERT_USUARIO = "INSERT INTO usuario (nombre, username, password, email, rol) values (?, ?, ?, ?, ?)";

    private final String UPDATE_USUARIO_PASSWORD = "update usuario set nombre = ?, username = ?, email = ?, activo = ?, password = ? where codigo = ?;";
    private final String UPDATE_USUARIO = "update usuario set nombre = ?, username = ?, email = ?, activo = ? where codigo = ?;";

    private Connection conexion;

    public DBUsuarioTransporte() throws SQLException {
        conexion = new DB().getConexion();
    }

    public ArrayList<Usuario> getAllUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_USUARIOS);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                Usuario usuario = new Usuario(
                        Integer.valueOf(result.getString("codigo")),
                        result.getString("nombre"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        Integer.valueOf(result.getString("rol")),
                        result.getString("activo")
                );

                usuarios.add(usuario);

            }
            return usuarios;
        } catch (SQLException ex) {
            ex.getStackTrace();
            return null;
        }

    }

    public String insertUsuario(String nombre, String username, String password, String email) {

        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_USUARIO);
            insert.setString(1, nombre);
            insert.setString(2, username);
            insert.setString(3, password);
            insert.setString(4, email);
            insert.setString(5, "4");

            insert.executeUpdate();

            return "Completado";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }

    }

    public Usuario getUsuarioCodigo(int codigo) {
        Usuario us;

        try {

            PreparedStatement select = conexion.prepareStatement(SELECT_USUARIOS_TRANSPORTE_CODIGO);
            select.setString(1, String.valueOf(codigo));
            ResultSet result = select.executeQuery();

            if (!result.isBeforeFirst()) { // el result esta vacio
                System.out.println("El usuario no existe"); //TODO:
                return null;
            }

            result.next();

            us = new Usuario(
                    Integer.valueOf(result.getString("codigo")),
                    result.getString("nombre"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("email"),
                    Integer.valueOf(result.getString("rol")),
                    result.getString("activo")
            );

            return us;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
    }

    public void updateUsuario(String nombre, String username, String password, String email, String codigo, String activo) {

        try {
            
            PreparedStatement updateUsuario;

            if (password.isBlank()) {
                updateUsuario = conexion.prepareStatement(UPDATE_USUARIO);
                updateUsuario.setString(5, codigo);
            } else {
                updateUsuario = conexion.prepareStatement(UPDATE_USUARIO_PASSWORD);
                updateUsuario.setString(5, password);
                updateUsuario.setString(6, codigo);

            }

            updateUsuario.setString(1, nombre);
            updateUsuario.setString(2, username);
            updateUsuario.setString(3, email);
            updateUsuario.setString(4, activo);
            
            updateUsuario.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
