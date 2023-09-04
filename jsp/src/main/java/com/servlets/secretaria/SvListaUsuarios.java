
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
import java.util.ArrayList;


@WebServlet(name = "SvListaUsuarios", urlPatterns = {"/admin/recepcion"})
public class SvListaUsuarios extends HttpServlet {

    DBUsuarioRecepcion recepcionDB;
    DBAdministracion adminDB;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            recepcionDB = new DBUsuarioRecepcion();
            adminDB = new DBAdministracion();
            
            //usuarios
            ArrayList<UsuarioRecepcion> usuarios = recepcionDB.getAllUsuarios();
            request.setAttribute("usuarios", usuarios);
            
            //bibliotecas
            ArrayList<Biblioteca> bibliotecas = adminDB.getBibliotecas();
            request.setAttribute("bibliotecas", bibliotecas);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/recepcion/");
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
