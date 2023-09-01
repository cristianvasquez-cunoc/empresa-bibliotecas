package com.db;

import java.sql.*;

public class DB {

    private static String URL_MYSQL = "jdbc:mysql://localhost:3306/empresa_bibliotecas";
    private static String USER = "root";
    private static String PASSWORD = "pass123";

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

}
