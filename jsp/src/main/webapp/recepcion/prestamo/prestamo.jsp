<%@page import="classes.Biblioteca"%>
<%@page import="com.db.usuario.UsuarioRecepcion"%>
<%@page import="classes.Libro"%>
<%@page import="com.db.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null || usuario.getRol() != 3)
        response.sendRedirect("/");

    else {

        Libro libro = (Libro) request.getAttribute("libro");
        UsuarioRecepcion usuarioR = (UsuarioRecepcion) request.getAttribute("usuarioR");
        Biblioteca biblioteca = (Biblioteca) request.getAttribute("biblioteca");
        double multa = (double) request.getAttribute("multa");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prestamo libro</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <jsp:include page="/includes/resourcesRecepcion.jsp"/>
    </head>
    <body data-bs-theme="dark">
        <jsp:include page="/recepcion/navbar.jsp">
            <jsp:param name="active" value="prestamo" />
        </jsp:include>
    </body>
    <section class="container mt-3" >
        <div class="container mb-4" >
            <h1 class="m-4 text-center">Prestamo de libro '${libro.getNombre()}'</h1>
            <h4 >Detalles del libro</h4>

            <ul style="font-size: 18px" class="d-flex flex-row gap-4">
                <li>isbn: ${libro.getIsbn()}</li>
                <li>autor: ${libro.getAutor()}</li>
                <li>categoria: ${libro.getCategoria()}</li>
                <li>costo: Q ${libro.getCosto() }</li>
            </ul>
            <hr/>
            <h4 >Detalles de la biblioteca</h4>

            <ul style="font-size: 18px" class="d-flex flex-row gap-4">
                <li>Nombre: ${biblioteca.getNombre()}</li>
                <li>Direccion: ${biblioteca.getDireccion()}</li>
            </ul>
            <hr/>
            <h4 >Detalles de recepcionista</h4>

            <ul style="font-size: 18px" class="d-flex flex-row gap-4">
                <li>Nombre: ${usuarioR.getNombre()}</li>
                <li>Correo electronico: ${usuarioR.getEmail()}</li>
            </ul>
            <hr/>
            <h4 >Detalles del prestamo</h4>

            <ul style="font-size: 18px" class="d-flex flex-row gap-4">
                <li>Multa en caso de entrega tardia: Q ${multa}</li>
            </ul>
            <hr/>
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/recepcion/prestamo?isbn=<%=libro.getIsbn()%>&biblioteca=<%=biblioteca.getCodigo()%>&recepcionista=<%=usuarioR.getCodigo()%>" id="formRealizarPrestamo">

            <div class="mb-3">
                <label for="username" class="form-label">Username usuario</label>
                <input type="text" required name="username" class="form-control" id="username" aria-describedby="username">
            </div>
            <div class="mb-3">
                <label for="diasPrestamo" class="form-label">Dias del prestamo</label>
                <input type="diasPrestamo" required name="diasPrestamo" class="form-control" id="diasPrestamo" aria-describedby="diasPrestamo">
            </div>
            <button type="submit" class="btn btn-primary">Realizar prestamo</button>
        </form>
    </section>
    
</html>

<%
    };
%>