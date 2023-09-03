
package com.servlets.admin;

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

@WebServlet(name = "SvAdminUnidadesLibros", urlPatterns = {"/admin/libros/unidades-por-biblioteca"})
public class SvAdminUnidadesLibros extends HttpServlet {
    
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String unidades = request.getParameter("unidades");
        
        if (id != null) {
            
            try {
                
                adminDB = new DBAdministracion();
                adminDB.updateUnidades(id, unidades);
                
                loadBibliotecasByIsbn(request, response);
            } catch (Exception ex) {
                response.sendRedirect(request.getContextPath() + "/");
                System.out.println("error");
            }
        }
        
    }
    
    public void loadBibliotecasByIsbn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Libro libro = adminDB.getLibroIsbn(request.getParameter("isbn"));
        
        ArrayList<UnidadesLibroBiblioteca> ulbs = adminDB.getUnidadesBibliotecaIsbn(libro.getIsbn());
        
        request.setAttribute("libro", libro);
        request.setAttribute("ulbs", ulbs);
        
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/libros/unidades-por-biblioteca.jsp");
        dispatcher.forward(request, response);
    }
    
}
