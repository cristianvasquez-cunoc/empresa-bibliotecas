package com.servlets.secretaria.usuariofinal;

import classes.Biblioteca;
import classes.Libro;
import com.db.administacion.DBAdministracion;
import com.db.usuario.DBUsuarioRecepcion;
import com.db.usuario.Usuario;
import com.db.usuario.UsuarioRecepcion;
import com.db.usuariofinal.DBUsuarioFinal;
import com.recepcion.LibroUnidades;
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

@WebServlet(name = "SvPrestamo", urlPatterns = {"/recepcion/prestamo"})
public class SvPrestamo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            DBUsuarioRecepcion recepcionDB = new DBUsuarioRecepcion();

            String isbn = request.getParameter("isbn");

            if (isbn != null) {

                DBAdministracion adminDB = new DBAdministracion();
                Libro libro = adminDB.getLibroIsbn(isbn);
                request.setAttribute("libro", libro);
                
                UsuarioRecepcion usuarioR = recepcionDB.getUsuarioRecepcionCodigo(usuario.getCodigo());
                request.setAttribute("usuarioR", usuarioR);
                
                Biblioteca biblioteca = adminDB.getBibliotecaByCodigo(String.valueOf(usuarioR.getBibliotecaCodigo()));
                request.setAttribute("biblioteca", biblioteca);
                
                double multa = adminDB.getMulta();
                request.setAttribute("multa", multa);

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/prestamo/prestamo.jsp");
                dispatcher.forward(request, response);

            } else {

                int codigoUsuario = usuario.getCodigo();

                String codigoBiblioteca = recepcionDB.getCodigoBiblioteca(codigoUsuario);

                ArrayList<LibroUnidades> librosConUnidades = recepcionDB.getAllLibrosConExistencias(codigoBiblioteca);
                ArrayList<LibroUnidades> librosSinUnidades = recepcionDB.getAllLibrosSinExistencias(codigoBiblioteca);

                request.setAttribute("librosConUnidades", librosConUnidades);
                request.setAttribute("librosSinUnidades", librosSinUnidades);

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/prestamo/");
                dispatcher.forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
