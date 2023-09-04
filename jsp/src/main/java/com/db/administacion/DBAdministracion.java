package com.db.administacion;

import classes.Biblioteca;
import classes.Categoria;
import classes.Libro;
import classes.UnidadesLibroBiblioteca;
import com.db.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBAdministracion {

    private final String SELECT_BIBLIOTECAS = "SELECT * from biblioteca;";
    private final String SELECT_LIBROS = "SELECT l.isbn, l.nombre, l.autor, l.costo, l.categoria as codigoCategoria, c.name as categoria FROM libro as l inner join categoria as c on l.categoria = c.codigo;";
    private final String SELECT_LIBRO_ISBN = "SELECT l.isbn, l.nombre, l.autor, l.costo, l.categoria as codigoCategoria, c.name as categoria FROM libro as l inner join categoria as c on l.categoria = c.codigo WHERE isbn = ?;";
    private final String SELECT_EXISTENCIAS_POR_BIBLIOTECA = "SELECT b.codigo as biblioteca, b.nombre, b.direccion, ub.unidades, ub.id, l.isbn FROM biblioteca as b INNER JOIN unidades_libro as ub ON (b.codigo = ub.biblioteca) INNER JOIN libro as l ON (l.isbn = ub.isbn) WHERE l.isbn = ?;";
    private final String SELECT_CATEGORIAS = "SELECT * FROM categoria;";

    private final String UPDATE_UNIDADES = "update unidades_libro set unidades = ? where id = ?;";
    private final String UPDATE_LIBRO = "UPDATE libro SET isbn = ?, nombre = ?, autor = ?, categoria = ?, costo = ? WHERE isbn = ?";

    private final String INSERT_CATEGORIA = "INSERT INTO categoria (name, description) VALUES (?, ?)";
    private final String INSERT_LIBRO = "INSERT INTO libro (isbn, nombre, autor, categoria, costo) VALUES (?, ?, ?, ?, ?)";
    private final String INSERT_UNIDADES_LIBRO = "INSERT INTO unidades_libro (biblioteca, isbn, unidades) values (?, ?, 0)";
    

    private Connection conexion;

    public DBAdministracion() throws SQLException {
        conexion = new DB().getConexion();
    }

    public ArrayList<Libro> getAllLibros() {
        ArrayList<Libro> libros = new ArrayList<Libro>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_LIBROS);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                Libro libro = new Libro(
                        result.getString("isbn"),
                        result.getString("nombre"),
                        result.getString("autor"),
                        result.getString("categoria"),
                        Double.valueOf(result.getString("costo")),
                        Integer.valueOf(result.getString("codigoCategoria"))
                );

                libros.add(libro);

            }
            return libros;
        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public ArrayList<Biblioteca> getBibliotecas() {
        ArrayList<Biblioteca> bibliotecas = new ArrayList<Biblioteca>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_BIBLIOTECAS);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                Biblioteca bib = new Biblioteca(
                        result.getString("codigo"),
                        result.getString("nombre"),
                        result.getString("direccion")
                );

                bibliotecas.add(bib);

            }
            return bibliotecas;
        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public ArrayList<UnidadesLibroBiblioteca> getUnidadesBibliotecaIsbn(String isbn) {

        ArrayList<UnidadesLibroBiblioteca> unidadesBibliotecas;

        try {

            PreparedStatement select = conexion.prepareStatement(SELECT_EXISTENCIAS_POR_BIBLIOTECA);
            select.setString(1, isbn);
            ResultSet result = select.executeQuery();

            unidadesBibliotecas = new ArrayList<>();

            while (result.next()) {

                UnidadesLibroBiblioteca unidadesBiblioteca = new UnidadesLibroBiblioteca(
                        result.getString("biblioteca"),
                        result.getString("nombre"),
                        result.getString("direccion"),
                        Integer.valueOf(result.getString("unidades")),
                        Integer.valueOf(result.getString("id")),
                        Integer.valueOf(result.getString("isbn"))
                );

                unidadesBibliotecas.add(unidadesBiblioteca);

            }

            return unidadesBibliotecas;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }

    }

    public Libro getLibroIsbn(String isbn) {
        Libro libro;

        try {

            PreparedStatement select = conexion.prepareStatement(SELECT_LIBRO_ISBN);
            select.setString(1, isbn);
            ResultSet result = select.executeQuery();

            if (!result.isBeforeFirst()) { // el result esta vacio
                System.out.println("El libro no existe"); //TODO:
                return null;
            }

            result.next();

            libro = new Libro(
                    result.getString("isbn"),
                    result.getString("nombre"),
                    result.getString("autor"),
                    result.getString("categoria"),
                    Double.valueOf(result.getString("costo")),
                    Integer.valueOf(result.getString("codigoCategoria"))
            );

            return libro;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }

    }

    public void updateUnidades(String id, String unidades) {
        try {
            PreparedStatement update = conexion.prepareStatement(UPDATE_UNIDADES);
            update.setString(1, unidades);
            update.setString(2, id);
            update.executeUpdate();
//            int filasActualizadas =update.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Categoria> getCategorias() {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();

        try {
            PreparedStatement select = conexion.prepareStatement(SELECT_CATEGORIAS);
            ResultSet result = select.executeQuery();

            while (result.next()) {

                categorias.add(new Categoria(
                        Integer.parseInt(result.getString("codigo")),
                        result.getString("name"),
                        result.getString("description")
                ));
            }

            return categorias;
        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int insertCategoria(String nombre, String descripcion) {
        int codigo = -1;

        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_CATEGORIA, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, nombre);
            insert.setString(2, descripcion);

            insert.executeUpdate();
            ResultSet llavesGeneradas = insert.getGeneratedKeys();
            if (llavesGeneradas.next()) {
                codigo = llavesGeneradas.getInt(1);  
                System.out.println("codigo");

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return codigo;
    }

    public void insertLibro(String isbn, String nombre, String autor, int categoria, double costo) {
        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_LIBRO);
            insert.setString(1, isbn);
            insert.setString(2, nombre);
            insert.setString(3, autor);
            insert.setString(4, String.valueOf(categoria));
            insert.setString(5, String.valueOf(costo));
            insert.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertUnidadesLibro (String bibliotecaCodigo, String isbn) {
        try {
            PreparedStatement insert = conexion.prepareStatement(INSERT_UNIDADES_LIBRO);
            insert.setString(1, bibliotecaCodigo);
            insert.setString(2, isbn);
            insert.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateLibro (String isbn, String nombre, String autor, String categoria, String costo) {
        try {
            PreparedStatement update = conexion.prepareStatement(UPDATE_LIBRO);
            update.setString(1, isbn);
            update.setString(2, nombre);
            update.setString(3, autor);
            update.setString(4, categoria);
            update.setString(5, costo);
            update.setString(6, isbn);
            update.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBAdministracion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
