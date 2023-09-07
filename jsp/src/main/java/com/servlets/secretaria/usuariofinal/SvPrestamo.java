package com.servlets.secretaria.usuariofinal;

import classes.Biblioteca;
import classes.Libro;
import com.db.administacion.DBAdministracion;
import com.db.usuario.DBUsuarioRecepcion;
import com.db.usuario.Usuario;
import com.db.usuario.UsuarioRecepcion;
import com.db.usuariofinal.DBUsuarioFinal;
import com.recepcion.LibroUnidades;
import com.usuariofinal.UsuarioFinal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                request.setAttribute("errorMsg", "");

                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/prestamo/prestamo.jsp");
                dispatcher.forward(request, response);

            } else {
                request.setAttribute("primeraVez", true);
                loadLibros(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SvPrestamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            DBAdministracion adminDB = new DBAdministracion();
            DBUsuarioFinal ufDB = new DBUsuarioFinal();

            String isbn = request.getParameter("isbn");
            String biblioteca = request.getParameter("biblioteca");
            String recepcionista = request.getParameter("recepcionista");
            String username = request.getParameter("username");
            String diasPrestamo = request.getParameter("diasPrestamo");

            String multa = adminDB.getSystemParameter("multa");

            UsuarioFinal us = adminDB.getUsuarioFinalByUsername(username);

            String limiteDias = "0";
            String limiteLibros = "0";

            if (us == null) {
                request.setAttribute("errorMsg", "El usuario no existe");
            } else {
                limiteDias = (us.isSuscrito()) ? adminDB.getSystemParameter("limite_dias_premium") : adminDB.getSystemParameter("limite_dias");
                limiteLibros = (us.isSuscrito()) ? adminDB.getSystemParameter("limite_libros_premium") : adminDB.getSystemParameter("limite_libros");

                int totalPrestamos = ufDB.getTotalPrestamosUsuario(us.getCodigo());

                if (Integer.valueOf(diasPrestamo) > Integer.valueOf(limiteDias)) {
                    request.setAttribute("errorMsg", "El limite de dias del prestamos para el usuario es de " + limiteDias + " y esta intentando prestarlo por " + diasPrestamo +" dias.");
                } else if (totalPrestamos >= Integer.valueOf(limiteLibros)) {
                    request.setAttribute("errorMsg", "El usuario tiene un total de " + totalPrestamos + " prestamos y el limite es " + limiteLibros);
                } else {
                    request.setAttribute("errorMsg", "");
                }
            }

            if (((String) request.getAttribute("errorMsg")).isEmpty()) {
                int codigoPrestamo = ufDB.crearPrestamo(biblioteca, recepcionista, multa, String.valueOf(us.getCodigo()), isbn, diasPrestamo);
                Date fechaEntrega = ufDB.getFechaEntrega(codigoPrestamo);
                request.setAttribute("descripcionPrestamo", "El prestamo tiene el codigo " + codigoPrestamo + " y se debe entregar en la fecha " + new SimpleDateFormat("dd/MM/yy").format(fechaEntrega));
                
            }
            // 3. ver que el total de libros prestados no exceda el limite segun esta suscrito

            request.setAttribute("primeraVez", false);
            loadLibros(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadLibros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            DBUsuarioRecepcion recepcionDB = new DBUsuarioRecepcion();

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
