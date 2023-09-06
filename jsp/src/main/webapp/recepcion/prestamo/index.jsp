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
        <title>Prestamo libro</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <jsp:include page="/includes/resourcesRecepcion.jsp"/>
    </head>
    <body data-bs-theme="dark">
        <jsp:include page="/recepcion/navbar.jsp">
            <jsp:param name="active" value="prestamo" />
        </jsp:include>
        <h1 class="m-4 text-center">Prestamo libro</h1>
        <div class="d-flex flex-column align-items-center">
            <div class="col-6">
                <div class="d-flex flex-row align-items-center row ">
                    <div class="col-6">

                        <div class="input-group rounded">
                            <input id='search' type="search" class="form-control rounded" placeholder="Filtrar por titulo, autor, categoria o isbn" aria-label="Search" aria-describedby="search-addon" />
                            <button id="searchButton" class="btn btn-primary" id="search-addon">
                                <i class="fas fa-search"></i>
                            </button>
                        </div>
                    </div>

                    <div class="col-6">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" checked id="mostrarSoloDisponiblesBiblioteca" />
                            <label class="form-check-label" for="mostrarSoloDisponiblesBiblioteca">Mostrar solo disponibles en la biblioteca</label>
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>


        <section class="container" style="display: flex;flex-wrap: wrap;justify-content: center;"  id='libros'>
            <%
                ArrayList<LibroUnidades> librosConUnidades = (ArrayList<LibroUnidades>) request.getAttribute("librosConUnidades");

                for (int i = 0; i < librosConUnidades.size(); i++) {

                    LibroUnidades libro = librosConUnidades.get(i);
            %>

            <div class="card col-3 m-3">
                <div class="card-body">
                    <h4 class="card-title"><%= libro.getNombre()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Autor/a: <%= libro.getAutor()%></h6>
                    <ul>
                        <li class="card-text">Categoría: <%= libro.getCategoria()%></li>
                        <li class="card-text">ISBN: <%= libro.getIsbn()%></li>
                        <li class="card-text">Costo: Q <%= libro.getCosto()%></li>
                        <li class="card-text">Unidades: <%= libro.getUnidades()%></li>
                    </ul>
                    <div class="d-flex flex-column">

                        <a href="${pageContext.request.contextPath}/recepcion/prestamo?isbn=<%= libro.getIsbn()%>" type="button" class="btn btn-primary mb-3">
                            Prestar
                        </a>
                    </div>
                </div>
            </div>
            <%}
                ArrayList<LibroUnidades> librosSinUnidades = (ArrayList<LibroUnidades>) request.getAttribute("librosSinUnidades");

                for (int i = 0; i < librosSinUnidades.size(); i++) {

                    LibroUnidades libro = librosSinUnidades.get(i);
            %>

            <div class="card col-3 m-3">
                <div class="card-body">
                    <h4 class="card-title text-muted"><%= libro.getNombre()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary text-muted">Autor/a: <%= libro.getAutor()%></h6>
                    <ul>
                        <li class="card-text text-muted">Categoría: <%= libro.getCategoria()%></li>
                        <li class="card-text text-muted">ISBN: <%= libro.getIsbn()%></li>
                        <li class="card-text text-muted">Costo: Q <%= libro.getCosto()%></li>
                    </ul>
                        <p class="card-text text-warning">No hay unidades disponibles</p>
                    <div class="d-flex flex-column">

                        <a href="${pageContext.request.contextPath}/recepcion/prestamo/unidades-libros?isbn=<%= libro.getIsbn()%>" type="button" class="btn btn-secondary mb-3">
                            Ver unidades en bibliotecas
                        </a>
                    </div>
                </div>
            </div>
            <%}%>
            
            
        </section>
            <script src="${pageContext.request.contextPath}/js/filtrarLibrosUnidades.js"></script>
    </body>
</html>

<%
    };
%>