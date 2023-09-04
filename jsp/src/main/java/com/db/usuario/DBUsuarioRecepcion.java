package com.db.usuario;

import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUsuarioRecepcion {

    private final String SELECT_USUARIOS_RECEPCION = "select u.codigo, u.nombre, u.username, u.email, b.codigo as codigo_biblioteca, b.nombre as biblioteca, b.direccion from usuario as u inner join usuario_secretaria as us on u.codigo = us.codigo inner join biblioteca as b on b.codigo = us.biblioteca;";
    private final String SELECT_USUARIOS_RECEPCION_CODIGO = "select u.codigo, u.nombre, u.username, u.email, b.codigo as codigo_biblioteca, b.nombre as biblioteca, b.direccion from usuario as u inner join usuario_secretaria as us on u.codigo = us.codigo inner join biblioteca as b on b.codigo = us.biblioteca WHERE u.codigo = ?;";

    private final String INSERT_USUARIO = "INSERT INTO usuario (nombre, username, password, email, rol) values (?, ?, ?, ?, ?)";
    private final String INSERT_USUARIO_RECEPCION = "INSERT INTO usuario_secretaria (codigo, biblioteca) values (?, ?)";

    private Connection conexion;

    public DBUsuarioRecepcion() throws SQLException {
        conexion = new DB().getConexion();
    }

    public ArrayList<UsuarioRecepcion> getAllUsuarios() {
        ArrayList<UsuarioRecepcion> usuarios = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_USUARIOS_RECEPCION);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                UsuarioRecepcion usuarioR = new UsuarioRecepcion(
                        Integer.parseInt(result.getString("codigo_biblioteca")),
                        result.getString("biblioteca"),
                        result.getString("direccion"),
                        Integer.parseInt(result.getString("codigo")),
                        result.getString("nombre"),
                        result.getString("username"),
                        result.getString("email")
                );

                usuarios.add(usuarioR);

            }
            return usuarios;
        } catch (SQLException ex) {
            ex.getStackTrace();
            return null;
        }

    }

    public int insertUsuario(String nombre, String username, String password, String email, String rol) {
        int codigo = -1;

        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_USUARIO, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, nombre);
            insert.setString(2, username);
            insert.setString(3, password);
            insert.setString(4, email);
            insert.setString(5, rol);

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

    public void insertarUsuarioRecepcion(String nombre, String usernmae, String password, String email, String codigoBiblioteca) {
        try {
            String codigo = String.valueOf(insertUsuario(nombre, usernmae, password, email, "3"));
            PreparedStatement insert = conexion.prepareStatement(INSERT_USUARIO_RECEPCION);
            insert.setString(1, codigo);
            insert.setString(2, codigoBiblioteca);
            insert.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
    }

    public UsuarioRecepcion getUsuarioRecepcionCodigo (int codigo) {
        UsuarioRecepcion usRec;

        try {

            PreparedStatement select = conexion.prepareStatement(SELECT_USUARIOS_RECEPCION_CODIGO);
            select.setString(1, String.valueOf(codigo));
            ResultSet result = select.executeQuery();

            if (!result.isBeforeFirst()) { // el result esta vacio
                System.out.println("El usuario no existe"); //TODO:
                return null;
            }

            result.next();

            usRec = new UsuarioRecepcion(
                    Integer.parseInt(result.getString("codigo_biblioteca")),
                    result.getString("biblioteca"),
                    result.getString("direccion"),
                    Integer.parseInt(result.getString("codigo")),
                    result.getString("nombre"),
                    result.getString("username"),
                    result.getString("email")
            );

            return usRec;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
    }
}
