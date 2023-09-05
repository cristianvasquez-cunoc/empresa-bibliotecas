
package com.servlets.transporte;


import com.db.transporte.DBUsuarioTransporte;
import com.db.usuario.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "SvListaUsuariosT", urlPatterns = {"/admin/transporte"})
public class SvListaUsuariosT extends HttpServlet {

    DBUsuarioTransporte transporteDB;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            transporteDB = new DBUsuarioTransporte();
            
            //usuarios
            ArrayList<Usuario> usuarios = transporteDB.getAllUsuarios();
            request.setAttribute("usuarios", usuarios);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/transporte/");
            dispatcher.forward(request, response);
            
        }  catch (Exception ex) {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

}
