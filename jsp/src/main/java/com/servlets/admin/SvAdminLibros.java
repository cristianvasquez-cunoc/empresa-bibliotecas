package com.servlets.admin;

import classes.Libro;
import com.db.administacion.Administracion;
import com.db.administacion.DBAdministracion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SvAdminLibros", urlPatterns = {"/admin/libros"})
public class SvAdminLibros extends HttpServlet {
    
    DBAdministracion adminDB;
    Administracion admin;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            adminDB = new DBAdministracion();
            
            ArrayList<Libro> libros = adminDB.getAllLibros();
            
            request.setAttribute("libros", libros);
            request.setAttribute("libroModificado", false);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/libros/");
            dispatcher.forward(request, response);
            
        }  catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
