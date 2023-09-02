package com.servlets;

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

@WebServlet(name = "SvLogin", urlPatterns = {"/login"})
public class SvLogin extends HttpServlet {

    UsuarioDB usuarioDB;
    Usuario usuario;

    String estado; // logged | unlogged

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        estado = request.getParameter("estado");


        if (estado.equals("unlogged")) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", null);
            RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/");
                dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        estado = request.getParameter("estado");
        
        if(estado != null && estado.equals("logged")) {
            RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/");
                dispatcher.forward(request, response);
                
        }else {
        try {

            usuarioDB = new UsuarioDB();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            usuario = usuarioDB.verificarLoginInformation(username, password);

            if (usuario != null) {
                // login session
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                estado = "logged";

                String path = getPathByRol(usuario.getRol());
                request.setAttribute("usuario", usuario);
                //Forward
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + path);
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("usuario", null);
                estado = "unlogged";
                //Forward
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/");
                dispatcher.forward(request, response);
            }

        } catch (SQLException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            //Forward
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/");
            dispatcher.forward(request, response);
        }
        }

    }

    private String getPathByRol(int rol) {
        switch (rol) {
            case 1:
                return "/dashboard";

            case 2:
                return "/admin";

            case 3:
                return "/secretaria";

            case 4:
                return "/transportista";

            default:
                throw new AssertionError();
        }
    }

}
