<%@page import="java.text.SimpleDateFormat"%>
<%@page import="classes.SolicitudRevocacionSuspension"%>
<%@page import="classes.Biblioteca"%>
<%@page import="com.db.usuario.UsuarioRecepcion"%>
<%@page import="com.db.usuario.Usuario"%>
<%@page import="classes.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null || usuario.getRol() != 2)
        response.sendRedirect("/");

    else {
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solicitudes</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="solicitudes" />
        </jsp:include>
        <div class="d-flex flex-row align-items-center justify-content-center" style="">

            <h1 class="m-4 text-center">Lista solicitudes de revocacion de suspension</h1>


        </div>

        <section id="srs" class="container" style="display: flex;flex-wrap: wrap;justify-content: center;">
            <%
                ArrayList<SolicitudRevocacionSuspension> listaSrs = (ArrayList<SolicitudRevocacionSuspension>) request.getAttribute("listaSrs");

                for (int i = 0; i < listaSrs.size(); i++) {

                    SolicitudRevocacionSuspension srs = listaSrs.get(i);
            %>

            <div class="card col-3 m-3 <%=srs.isAprovada() ? "bg-secondary text-light" : ""%>">
                <div class="card-body ">
                    <h4 class="card-title">Suspension id: <%= srs.getId()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Nombre <%= srs.getNombreUsuario()%></h6>
                    <ul>
                        <li class="card-text">Estado: <%= srs.isAprovada() ? "Aprovada" : "No aprovada"%></li>
                        <li class="card-text">Fecha suspension: <%= new SimpleDateFormat().format(srs.getFechaSuspension())%></li>
                        <li class="card-text">Fecha aprovacion <%=srs.isAprovada() ? new SimpleDateFormat().format(srs.getFechaAprovacion()) : "Pendiente"%></li>
                    </ul>
                    <%
                        if (!srs.isAprovada()) {

                    %>
                    <div class="d-flex flex-column">

                        <a href="${pageContext.request.contextPath}/admin/solicitudes?id=<%=srs.getId()%>&codigo=<%=srs.getCodigoUsuario()%>" type="button" class="btn btn btn-outline-primary mb-3">
                            Aprovar
                        </a>
                    </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <%
                }
            %>
        </section>
    </body>
</html>
<%
    }
    ;
%>