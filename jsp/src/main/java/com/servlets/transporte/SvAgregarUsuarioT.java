package com.servlets.transporte;

import com.db.transporte.DBUsuarioTransporte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SvAgregarUsuarioT", urlPatterns = {"/admin/transporte/agregar-usuario"})
public class SvAgregarUsuarioT extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agregadoExitosamente", false);
        request.setAttribute("errorMessage", null);

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/transporte/agregar-usuario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("nombre");
            String usernmae = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            DBUsuarioTransporte usuarioDBT = new DBUsuarioTransporte();

            String message = usuarioDBT.insertUsuario(nombre, usernmae, password, email);
            if (message.equals("Completado")) {
                request.setAttribute("agregadoExitosamente", true);
                request.setAttribute("errorMessage", null);
            } else {
                request.setAttribute("agregadoExitosamente", false);
                request.setAttribute("errorMessage", message);
            }

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/transporte/agregar-usuario.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
