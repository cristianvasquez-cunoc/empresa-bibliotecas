package com.servlets.transporte;

import classes.Biblioteca;
import classes.Categoria;
import classes.Libro;
import com.db.administacion.DBAdministracion;
import com.db.transporte.DBUsuarioTransporte;
import com.db.usuario.DBUsuarioRecepcion;
import com.db.usuario.Usuario;
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

@WebServlet(name = "SvModificarUsuarioT", urlPatterns = {"/admin/transporte/modificar-usuario"})
public class SvModificarUsuarioT extends HttpServlet {
    
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
            String activo = request.getParameter("estado");
            
            DBUsuarioTransporte transporteDB = new DBUsuarioTransporte();
            
            transporteDB.updateUsuario(nombre, username, password, email, codigo, activo);
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
            
            DBUsuarioTransporte transporteDB = new DBUsuarioTransporte();
            
            Usuario us = transporteDB.getUsuarioCodigo(codigo);
            
            request.setAttribute("us", us);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/transporte/modificar-usuario.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/");
            
        }
    }
}
