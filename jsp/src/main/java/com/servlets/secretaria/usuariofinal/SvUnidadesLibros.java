/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.servlets.secretaria.usuariofinal;

import classes.Libro;
import classes.UnidadesLibroBiblioteca;
import com.db.administacion.DBAdministracion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SvUnidadesLibros", urlPatterns = {"/recepcion/prestamo/unidades-libros"})
public class SvUnidadesLibros extends HttpServlet {
    
        DBAdministracion adminDB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            
            adminDB = new DBAdministracion();
            
            loadBibliotecasByIsbn(request, response);
            
        } catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
    
    public void loadBibliotecasByIsbn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Libro libro = adminDB.getLibroIsbn(request.getParameter("isbn"));
        
        ArrayList<UnidadesLibroBiblioteca> ulbs = adminDB.getUnidadesBibliotecaIsbn(libro.getIsbn());
        
        request.setAttribute("libro", libro);
        request.setAttribute("ulbs", ulbs);
        
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/prestamo/unidades-por-biblioteca.jsp");
        dispatcher.forward(request, response);
    }

}
