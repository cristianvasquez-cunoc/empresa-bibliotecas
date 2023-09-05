package com.servlets;

import classes.SolicitudRevocacionSuspension;
import com.db.DBSolicitudes;
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

@WebServlet(name = "SvSolicitudes", urlPatterns = {"/admin/solicitudes"})
public class SvSolicitudes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            DBSolicitudes solicitudesDB = new DBSolicitudes();
            String id = request.getParameter("id");
            String codigo = request.getParameter("codigo");

            if (id != null && !id.isEmpty())
                solicitudesDB.aprovar(id,codigo);

            ArrayList<SolicitudRevocacionSuspension> listaSrs = solicitudesDB.getSolicitudes();

            request.setAttribute("listaSrs", listaSrs);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/solicitudes/");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
