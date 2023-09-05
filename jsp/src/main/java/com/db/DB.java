package com.db;

import classes.Administracion;
import java.sql.*;

public class DB {

    private static String URL_MYSQL = "jdbc:mysql://localhost:3306/empresa_bibliotecas";
    private static String USER = "root";
    private static String PASSWORD = "pass123";

    private final String SELECT_DATA = "SELECT * FROM administracion where id = 1";
    
    private final String UPDATE_DATA = "UPDATE administracion SET dias_suspension = ?, multa = ?, costo_domicilio = ?, costo_suscripcion = ?, descuento_domicilio_premium = ?, limite_dias = ?, limite_libros = ?, limite_dias_premium = ?, limite_libros_premium = ? WHERE id = 1;";
    
    private Connection conexion;

    public DB() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public ResultSet enviarQuery(String query) throws SQLException {
        PreparedStatement select = conexion.prepareStatement(query);

        return select.executeQuery();
    }
    
    public Administracion getDataAdministracion() {
        
        try {
            Administracion administracion;

            PreparedStatement select = conexion.prepareStatement(SELECT_DATA);
            ResultSet result = select.executeQuery();

            if (!result.isBeforeFirst()) { // el result esta vacio
                System.out.println("Hubo un error, no encontro los parametros"); //TODO:
                return null;
            }

            result.next();

            administracion = new Administracion(
                    result.getInt("dias_suspension"), 
                    result.getDouble("multa"), 
                    result.getDouble("costo_domicilio"), 
                    result.getDouble("costo_suscripcion"),
                    result.getDouble("descuento_domicilio_premium"),
                    result.getInt("limite_dias"),
                    result.getInt("limite_libros"), 
                    result.getInt("limite_dias_premium"), 
                    result.getInt("limite_libros_premium")
            );

            return administracion;

        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
            return null;
        }
        
    }
    
    public void updateDataAdministracion (String diasSuspension, String multa, String costoDomicilio, String costoSuscripcion, String descuentoDomicilioPremium, String limiteDias, String limiteLibros, String limiteDiasPremium, String limiteLibrosPremium) {
        
        try {
            PreparedStatement update = conexion.prepareStatement(UPDATE_DATA);
            update.setString(1, diasSuspension);
            update.setString(2, multa);
            update.setString(3, costoDomicilio);
            update.setString(4, costoSuscripcion);
            update.setString(5, descuentoDomicilioPremium);
            update.setString(6, limiteDias);
            update.setString(7, limiteLibros);
            update.setString(8, limiteDiasPremium);
            update.setString(9, limiteLibrosPremium);
            update.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

}
