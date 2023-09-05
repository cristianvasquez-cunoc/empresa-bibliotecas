package com.servlets.secretaria;

import classes.Biblioteca;
import classes.Categoria;
import classes.Libro;
import com.db.administacion.DBAdministracion;
import com.db.usuario.DBUsuarioRecepcion;
import com.db.usuario.UsuarioRecepcion;
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

@WebServlet(name = "SvModificarUsuario", urlPatterns = {"/admin/recepcion/modificar-usuario"})
public class SvModificarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("usuarioModificadoExitosamente", false);
        loadUsuario(request, response, request.getParameter("codigo"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String bibliotecaCodigo = request.getParameter("bibliotecas");
            String activo = request.getParameter("estado");

            DBUsuarioRecepcion recepcionDB = new DBUsuarioRecepcion();

            if (password.isBlank()) {
                recepcionDB.updateUsuarioRecepcion(nombre, username, email, codigo, bibliotecaCodigo, activo);
            } else {
                recepcionDB.updateUsuarioRecepcion(nombre, username, password, email, codigo, bibliotecaCodigo, activo);
            }
            
                        request.setAttribute("usuarioModificadoExitosamente", true);

            
            loadUsuario(request, response, codigo);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadUsuario(HttpServletRequest request, HttpServletResponse response, String codigoS)
            throws ServletException, IOException {

        try {

            int codigo = Integer.parseInt(codigoS);

            DBUsuarioRecepcion recepcionDB = new DBUsuarioRecepcion();
            DBAdministracion adminDB = new DBAdministracion();

            UsuarioRecepcion usuarioR = recepcionDB.getUsuarioRecepcionCodigo(codigo);
            ArrayList<Biblioteca> bibliotecas = adminDB.getBibliotecas();

            request.setAttribute("usuarioR", usuarioR);
            request.setAttribute("bibliotecas", bibliotecas);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/recepcion/modificar-usuario.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvModificarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
