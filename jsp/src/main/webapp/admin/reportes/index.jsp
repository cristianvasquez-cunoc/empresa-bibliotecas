<%@page import="com.reportes.ReporteLibro"%>
<%@page import="com.reportes.RecepcionistaPrestamosFinalizados"%>
<%@page import="com.reportes.UsuarioSuscrito"%>
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
        <title>Recepcion</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="reportes" />
        </jsp:include>
        <section class="container">

            <h1 class=" my-3">Reportes</h1>
            <h2 class=" mb-3">Usuarios que han adquirido una suscripción en los últimos 3 meses.</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Username</th>
                        <th scope="col">Correo electronico</th>
                        <th scope="col">Suscrito</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<UsuarioSuscrito> usuarios = (ArrayList<UsuarioSuscrito>) request.getAttribute("suscritos");

                        for (int i = 0; i < usuarios.size(); i++) {

                            UsuarioSuscrito us = usuarios.get(i);
                    %>
                    <tr>
                        <th scope="row"><%=i + 1%></th>
                        <td scope="row"><%=us.getNombre()%></td>
                        <td scope="row"><%=us.getUsername()%></td>
                        <td scope="row"><%=us.getEmail()%></td>
                        <td scope="row"><%=us.isSuscrito()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <h2 class=" mb-3">Top 5 usuario de recepcion con mas prestamos finalizados</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Codigo</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Biblioteca</th>
                        <th scope="col">Prestamos finalizados</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<RecepcionistaPrestamosFinalizados> recepcionistas = (ArrayList<RecepcionistaPrestamosFinalizados>) request.getAttribute("recepcionistas");

                        for (int i = 0; i < recepcionistas.size(); i++) {

                            RecepcionistaPrestamosFinalizados recepcionista = recepcionistas.get(i);
                    %>
                    <tr>
                        <th scope="row"><%=i + 1%></th>
                        <td scope="row"><%=recepcionista.getCodigo()%></td>
                        <td scope="row"><%=recepcionista.getNombre()%></td>
                        <td scope="row"><%=recepcionista.getBiblioteca()%></td>
                        <td scope="row"><%=recepcionista.getTotalPrestamosFinalizados()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <h2 class=" mb-3">Top 10 de libros que más han sido prestados en un rango de tiempo</h2>
            <form method="POST" action="${pageContext.request.contextPath}/admin/reportes">
                <div class="mb-3">
                    <label for="isbn" class="form-label">Fecha inicio</label>
                    <input type="text" required name="fechaInicio" class="form-control" id="fechaInicio" aria-describedby="fechaInicio">
                    <div id="fechaInicio" class="form-text">Tiene que ser un formato valido yyyy-MM-dd</div>
                </div>
                <div class="mb-3">
                    <label for="isbn" class="form-label">Fecha final</label>
                    <input type="text" required name="fechaFinal" class="form-control" id="fechaFinal" aria-describedby="fechaFinal">
                    <div id="fechaFinal" class="form-text">Tiene que ser un formato valido yyyy-MM-dd</div>
                </div>
                <input type="submit" value="Enviar rango" class="btn btn-primary"/>
            </form>

            <%

                ArrayList<ReporteLibro> libros = (ArrayList<ReporteLibro>) request.getAttribute("libros");
                if (libros != null) {
            %>

            <table class="table %>">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Total Libros</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        for (int i = 0; i < libros.size(); i++) {

                            ReporteLibro libro = libros.get(i);
                    %>
                    <tr>
                        <th scope="row"><%=i + 1%></th>
                        <td scope="row"><%=libro.getNombre()%></td>
                        <td scope="row"><%=libro.getTotalPrestamos()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
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