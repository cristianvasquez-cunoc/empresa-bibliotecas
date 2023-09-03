package com.servlets.admin;

import classes.Biblioteca;
import classes.Categoria;
import classes.Libro;
import classes.UnidadesLibroBiblioteca;
import com.db.administacion.DBAdministracion;
import com.db.usuario.Usuario;
import com.db.usuario.UsuarioDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvAdminAgregarLibro", urlPatterns = {"/admin/libros/agregar"})
public class SvAdminAgregarLibro extends HttpServlet {

    DBAdministracion adminDB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            adminDB = new DBAdministracion();

            ArrayList<Categoria> categorias = adminDB.getCategorias();

            request.setAttribute("categorias", categorias);
            request.setAttribute("agregadoExitosamente", false);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/libros/agregar-libro.jsp");
            dispatcher.forward(request, response);

        } catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/");
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

            adminDB.insertLibro(isbn, nombre, autor, codigo, Double.parseDouble(costo));

            ArrayList<Categoria> categorias = adminDB.getCategorias();

            request.setAttribute("categorias", categorias);
            request.setAttribute("agregadoExitosamente", true);
            
            // create unidades_libro
            ArrayList<Biblioteca> bibliotecas = adminDB.getBibliotecas();
            
            for (Biblioteca biblioteca : bibliotecas)
                    adminDB.insertUnidadesLibro(biblioteca.getCodigo(), isbn);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/libros/agregar-libro.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvAdminAgregarLibro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
