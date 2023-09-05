
import classes.Administracion;
import com.db.DB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SvSistema", urlPatterns = {"/admin/sistema"})
public class SvSistema extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("datosActualizados", false);
        loadData(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String diasSuspension = request.getParameter("diasSuspension");
            String multa = request.getParameter("multa");
            String costoDomicilio = request.getParameter("costoDomicilio");
            String costoSuscripcion = request.getParameter("costoSuscripcion");
            String descuentoDomicilioPremium = request.getParameter("descuentoDomicilioPremium");
            String limiteDias = request.getParameter("limiteDias");
            String limiteLibros = request.getParameter("limiteLibros");
            String limiteDiasPremium = request.getParameter("limiteDiasPremium");
            String limiteLibrosPremium = request.getParameter("limiteLibrosPremium");

            DB db = new DB();

            Administracion administracion = db.getDataAdministracion();

            db.updateDataAdministracion(diasSuspension, multa, costoDomicilio, costoSuscripcion, descuentoDomicilioPremium, limiteDias, limiteLibros, limiteDiasPremium, limiteLibrosPremium);
            request.setAttribute("datosActualizados", true);

            loadData(request, response);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void loadData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            DB db = new DB();

            Administracion administracion = db.getDataAdministracion();

            request.setAttribute("administracion", administracion);

            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/admin/sistema/");
            dispatcher.forward(request, response);
        } catch (SQLException ex) {
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher(this.getServletContext().getContextPath() + "/");
            dispatcher.forward(request, response);

        }
    }
}
