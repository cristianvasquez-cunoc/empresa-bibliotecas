
package com.servlets.secretaria.usuariofinal;

import com.db.usuariofinal.DBUsuarioFinal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SvAgregarUsuarioFinal", urlPatterns = {"/recepcion/agregar-usuario"})
public class SvAgregarUsuarioFinal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("agregadoExitosamente", false);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/agregar-usuario/");
            dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String nombre = request.getParameter("nombre");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String saldoInicial = request.getParameter("saldoInicial");

            DBUsuarioFinal usuariofinalDB = new DBUsuarioFinal();

            int codigo = usuariofinalDB.insertUsuario(nombre, username, password, email);
            usuariofinalDB.insertUsuarioFinal(String.valueOf(codigo), saldoInicial);
            request.setAttribute("agregadoExitosamente", true);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/recepcion/agregar-usuario/");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
