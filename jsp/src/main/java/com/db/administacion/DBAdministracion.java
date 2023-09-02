package com.db.administacion;

import classes.Libro;
import classes.UnidadesLibroBiblioteca;
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
    private final String SELECT_LIBRO_ISBN = "SELECT l.isbn, l.nombre, l.autor, l.costo, c.name as categoria FROM libro as l inner join categoria as c on l.categoria = c.codigo WHERE isbn = ?;";
    private final String SELECT_EXISTENCIAS_POR_BIBLIOTECA = "SELECT b.nombre, b.direccion, ub.unidades, ub.id, l.isbn FROM biblioteca as b INNER JOIN unidades_libro as ub ON (b.codigo = ub.biblioteca) INNER JOIN libro as l ON (l.isbn = ub.isbn) WHERE l.isbn = ?;";

    private final String UPDATE_UNIDADES = "update unidades_libro set unidades = ? where id = ?;";

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
                        Double.valueOf(result.getString("costo"))
                );

                libros.add(libro);

            }
            return libros;
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
                    Double.valueOf(result.getString("costo"))
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

}
