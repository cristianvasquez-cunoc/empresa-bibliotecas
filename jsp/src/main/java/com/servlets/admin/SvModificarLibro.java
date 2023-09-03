
package com.servlets.admin;

import classes.Categoria;
import classes.Libro;
import com.db.administacion.DBAdministracion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvModificarLibro", urlPatterns = {"/admin/libros/modificar"})
public class SvModificarLibro extends HttpServlet {

    DBAdministracion adminDB;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String isbn = request.getParameter("isbn");
            
            adminDB = new DBAdministracion();
            
            Libro libro = adminDB.getLibroIsbn(isbn);
            ArrayList<Categoria> categorias = adminDB.getCategorias();

            request.setAttribute("categorias", categorias);
            request.setAttribute("libro", libro);
                    request.setAttribute("libroModificado", false);

            
            RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/libros/modificar-libro.jsp");
        dispatcher.forward(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(SvModificarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int codigo;

        try {
            String isbn = request.getParameter("isbn");
            String nombre = request.getParameter("nombre");
            String autor = request.getParameter("autor");
            String costo = request.getParameter("costo");
            String categoria = request.getParameter("seleccionCategoria");
            String nombreCategoria = request.getParameter("nombreCategoria");
            String descripcionCategoria = request.getParameter("descripcionCategoria");

            adminDB = new DBAdministracion();

            if (categoria.equals("otra")) {
                codigo = adminDB.insertCategoria(nombreCategoria, descripcionCategoria);
            } else {
                codigo = Integer.parseInt(categoria);
            }

            adminDB.updateLibro(isbn, nombre, autor, String.valueOf(codigo), costo);

            ArrayList<Libro> libros = adminDB.getAllLibros();
            
        request.setAttribute("libros", libros);
        request.setAttribute("libroModificado", true);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/libros/");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvAdminAgregarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
