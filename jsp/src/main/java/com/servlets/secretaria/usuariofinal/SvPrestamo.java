package com.servlets.secretaria.usuariofinal;

import classes.Libro;
import com.db.administacion.DBAdministracion;
import com.db.usuario.DBUsuarioRecepcion;
import com.db.usuario.Usuario;
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
            request.setAttribute("agregadoExitosamente", false);
            
            DBUsuarioRecepcion recepcionDB = new DBUsuarioRecepcion();
            
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            int codigoUsuario = usuario.getCodigo();
            
            String codigoBiblioteca = recepcionDB.getCodigoBiblioteca(codigoUsuario);
            
            ArrayList<LibroUnidades> librosConUnidades = recepcionDB.getAllLibrosConExistencias(codigoBiblioteca);
            ArrayList<LibroUnidades> librosSinUnidades = recepcionDB.getAllLibrosSinExistencias(codigoBiblioteca);
            
            request.setAttribute("librosConUnidades", librosConUnidades);
            request.setAttribute("librosSinUnidades", librosSinUnidades);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/prestamo/");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
