package com.servlets.secretaria;

import classes.Biblioteca;
import com.db.administacion.DBAdministracion;
import com.db.usuario.UsuarioRecepcion;
import com.db.usuario.DBUsuarioRecepcion;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvAgregarUsuario", urlPatterns = {"/admin/recepcion/agregar-usuario"})
public class SvAgregarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        obtenerBibliotecas(request, response, Boolean.FALSE);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo;

        try {
            String nombre = request.getParameter("nombre");
            String usernmae = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String biblioteca = request.getParameter("bibliotecas");

            DBUsuarioRecepcion usuarioRDB = new DBUsuarioRecepcion();

            usuarioRDB.insertarUsuarioRecepcion(nombre, usernmae, password, email, biblioteca);

            obtenerBibliotecas(request, response, Boolean.TRUE);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void obtenerBibliotecas(HttpServletRequest request, HttpServletResponse response, Boolean agregadoExitosamente)
            throws ServletException, IOException {
        try {
            // obtener bibliotecas
            DBAdministracion adminDB = new DBAdministracion();

            ArrayList<Biblioteca> bibliotecas = adminDB.getBibliotecas();
            request.setAttribute("bibliotecas", bibliotecas);
            request.setAttribute("agregadoExitosamente", agregadoExitosamente);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/recepcion/agregar-usuario.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("agregadoExitosamente", false);
        }
    }

}
