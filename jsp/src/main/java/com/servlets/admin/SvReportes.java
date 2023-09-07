package com.servlets.admin;

import com.reportes.UsuarioSuscrito;
import com.db.DBReportes;
import com.reportes.RecepcionistaPrestamosFinalizados;
import com.reportes.ReporteLibro;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SvReportes", urlPatterns = {"/admin/reportes"})
public class SvReportes extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        loadReportes(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<ReporteLibro> libros = new ArrayList<>();

        try {
            DBReportes reportesDB = new DBReportes();

            String fechaInicio = (String) request.getAttribute("fechaInicio");
            String fechaFinal = (String) request.getAttribute("fechaFinal");

            libros = reportesDB.top10Libros(fechaInicio, fechaFinal);
            request.setAttribute("libros", libros);

            loadReportes(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(SvReportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadReportes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DBReportes reportesDB = new DBReportes();
            
            ArrayList<UsuarioSuscrito> suscritos = reportesDB.getReporteUsuariosUltimos3Meses();
            request.setAttribute("suscritos", suscritos);
            
            ArrayList<RecepcionistaPrestamosFinalizados> recepcionistas = reportesDB.getRecepcionistasPrestamosFinalizados();
            request.setAttribute("recepcionistas", recepcionistas);
            
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/reportes/");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SvReportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
