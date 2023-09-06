package com.db.usuario;

import classes.Libro;
import com.db.DB;
import com.recepcion.LibroUnidades;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUsuarioRecepcion {

    private final String SELECT_USUARIOS_RECEPCION = "select u.codigo, u.nombre, u.username, u.email, b.codigo as codigo_biblioteca, b.nombre as biblioteca, b.direccion, u.activo from usuario as u inner join usuario_secretaria as us on u.codigo = us.codigo inner join biblioteca as b on b.codigo = us.biblioteca;";
    private final String SELECT_USUARIOS_RECEPCION_CODIGO = "select u.codigo, u.nombre, u.username, u.email, b.codigo as codigo_biblioteca, b.nombre as biblioteca, b.direccion, u.activo from usuario as u inner join usuario_secretaria as us on u.codigo = us.codigo inner join biblioteca as b on b.codigo = us.biblioteca WHERE u.codigo = ?;";
    private final String SELECT_LIBROS_CON_EXISTENCIAS = "select l.isbn, l.nombre, l.autor, l.costo, c.name as categoria, ul.biblioteca, ul.unidades from libro as l inner join unidades_libro as ul inner join categoria as c on c.codigo = l.categoria where ul.biblioteca = ? and ul.unidades > 0 and l.isbn = ul.isbn;";
    private final String SELECT_LIBROS_SIN_EXISTENCIAS = "select l.isbn, l.nombre, l.autor, l.costo, c.name as categoria, ul.biblioteca, ul.unidades from libro as l inner join unidades_libro as ul inner join categoria as c on c.codigo = l.categoria where ul.biblioteca = ? and ul.unidades = 0 and l.isbn = ul.isbn;";

    private final String INSERT_USUARIO = "INSERT INTO usuario (nombre, username, password, email, rol) values (?, ?, ?, ?, ?)";
    private final String INSERT_USUARIO_RECEPCION = "INSERT INTO usuario_secretaria (codigo, biblioteca) values (?, ?)";

    private final String UPDATE_USUARIO_PASSWORD = "update usuario set nombre = ?, username = ?, password = ?, email = ?, activo = ? where codigo = ?;";
    private final String UPDATE_USUARIO = "update usuario set nombre = ?, username = ?, email = ?, activo = ? where codigo = ?;";
    private final String UPDATE_BIBLIOTECA_USUARIO = "update usuario_secretaria  set biblioteca = ? where  codigo = ?;";

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
                        result.getString("email"),
                        Integer.parseInt(result.getString("activo"))
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

    public UsuarioRecepcion getUsuarioRecepcionCodigo(int codigo) {
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
                    result.getString("email"),
                    Integer.parseInt(result.getString("activo"))
            );

            return usRec;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
    }

    public void updateUsuarioRecepcion(String nombre, String username, String password, String email, String codigo, String biblioteca, String activo) {

        try {
            PreparedStatement updateUsuario = conexion.prepareStatement(UPDATE_USUARIO_PASSWORD);
            updateUsuario.setString(1, nombre);
            updateUsuario.setString(2, username);
            updateUsuario.setString(3, password);
            updateUsuario.setString(4, email);
            updateUsuario.setString(5, activo);
            updateUsuario.setString(6, codigo);
            updateUsuario.executeUpdate();

            PreparedStatement updateBiblioteca = conexion.prepareStatement(UPDATE_BIBLIOTECA_USUARIO);
            updateBiblioteca.setString(1, biblioteca);
            updateBiblioteca.setString(2, codigo);
            updateBiblioteca.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    ;
    
    public void updateUsuarioRecepcion(String nombre, String username, String email, String codigo, String biblioteca, String activo) {

        try {
            PreparedStatement update = conexion.prepareStatement(UPDATE_USUARIO);
            update.setString(1, nombre);
            update.setString(2, username);
            update.setString(3, email);
            update.setString(4, activo);
            update.setString(5, codigo);
            update.executeUpdate();

            PreparedStatement updateBiblioteca = conexion.prepareStatement(UPDATE_BIBLIOTECA_USUARIO);
            updateBiblioteca.setString(1, biblioteca);
            updateBiblioteca.setString(2, codigo);
            updateBiblioteca.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    ;
    
    public ArrayList<LibroUnidades> getAllLibrosConExistencias(String codigoBiblioteca) {
        ArrayList<LibroUnidades> libros = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_LIBROS_CON_EXISTENCIAS);
            select.setString(1, codigoBiblioteca);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                LibroUnidades libro = new LibroUnidades(
                        result.getString("isbn"),
                        result.getString("nombre"),
                        result.getString("autor"),
                        result.getString("categoria"),
                        Double.valueOf(result.getString("costo")),
                        Integer.valueOf(result.getString("unidades"))
                );

                libros.add(libro);

            }
            return libros;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<LibroUnidades> getAllLibrosSinExistencias(String codigoBiblioteca) {
        ArrayList<LibroUnidades> libros = new ArrayList<>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_LIBROS_SIN_EXISTENCIAS);
            select.setString(1, codigoBiblioteca);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                LibroUnidades libro = new LibroUnidades(
                        result.getString("isbn"),
                        result.getString("nombre"),
                        result.getString("autor"),
                        result.getString("categoria"),
                        Double.valueOf(result.getString("costo")),
                        Integer.valueOf(result.getString("unidades"))
                );

                libros.add(libro);

            }
            return libros;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getCodigoBiblioteca(int codigoUsuario) {
        try {
            PreparedStatement select = conexion.prepareStatement("select * from usuario_secretaria where codigo = ?");
            select.setString(1, String.valueOf(codigoUsuario));
            ResultSet result = select.executeQuery();

            result.next();

            return result.getString("biblioteca");
        } catch (SQLException ex) {
            Logger.getLogger(DBUsuarioRecepcion.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
