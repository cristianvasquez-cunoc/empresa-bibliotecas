<%@page import="classes.UnidadesLibroBiblioteca"%>
<%@page import="com.recepcion.LibroUnidades"%>
<%@page import="classes.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.db.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null || usuario.getRol() != 3)
        response.sendRedirect("/");

    else {
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unidades disponibles</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <jsp:include page="/includes/resourcesRecepcion.jsp"/>
    </head>
    <body data-bs-theme="dark">
        <jsp:include page="/recepcion/navbar.jsp">
            <jsp:param name="active" value="prestamo" />
        </jsp:include>
        <div class="container mb-4" >
            <h1 class="m-4 text-center">Existencias de '${libro.getNombre()}'</h1>
            <h4 >Detalles del libro</h4>

            <ul style="font-size: 18px" class="d-flex flex-row gap-4">
                <li>isbn: ${libro.getIsbn()}</li>
                <li>autor: ${libro.getAutor()}</li>
                <li>categoria: ${libro.getCategoria()}</li>
                <li>costo: Q ${libro.getCosto() }</li>
            </ul>
            <hr/>

        </div>
        <section class="container" style="display: flex;flex-wrap: wrap;justify-content: center;">

            <%                ArrayList<UnidadesLibroBiblioteca> ulbs = (ArrayList<UnidadesLibroBiblioteca>) request.getAttribute("ulbs");

                for (int i = 0; i < ulbs.size(); i++) {

                    UnidadesLibroBiblioteca ulb = ulbs.get(i);

            %>

            <div class="card col-3 m-3">
                <div class="card-body">
                    <h4 class="card-title"><%= ulb.getNombre()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Direccion: <%= ulb.getDireccion()%></h6>
                    <p class="card-text">Unidades <%= ulb.getUnidades()%></p>
                    

                </div>
            </div>

            <%
                }
            %>
        </section>
    </body>
</html>

<%
    };
%>