/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            usuarioDB = new UsuarioDB();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            usuario = usuarioDB.verificarLoginInformation(username, password);

            if (usuario != null) {
                // login session
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                
                String path = loadModuloSegunRol(usuario.getRol());
                request.setAttribute("usuario", usuario);
                //Forward
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + path);
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("usuario", null);
                //Forward
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(this.getServletContext().getContextPath() + "/login");
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

    private String loadModuloSegunRol(int rol) {
        switch (rol) {
            case 1:
                loadFinal();
                return "/dashboard";

            case 2:
                loadAdmin();
                return "/admin";

            case 3:
                loadSecretaria();
                return "/secretaria";

            case 4:
                loadTransportista();
                return "/transportista";

            default:
                throw new AssertionError();
        }
    }

    private void loadFinal() {
        System.out.println("es final");

    }

    private void loadAdmin() {

        System.out.println("admin");
    }

    private void loadSecretaria() {
        System.out.println("es secretaria");

    }

    private void loadTransportista() {
        System.out.println("es transportista");

    }
}
