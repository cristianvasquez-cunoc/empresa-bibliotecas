

<%@page import="classes.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libros</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark">
         <jsp:include page="/admin/navbar.jsp"/>
         <h1 class="m-4 text-center">Lista de libros</h1>
        <section class="container" style="display: flex;flex-wrap: wrap;justify-content: center;">
            <%
                ArrayList<Libro> libros = (ArrayList<Libro>) request.getAttribute("libros");

                for (int i = 0; i < libros.size(); i++) {

                    Libro libro = libros.get(i);
            %>

            <div class="card col-3 m-3">
                <div class="card-body">
                    <h4 class="card-title"><%= libro.getNombre()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Autor/a: <%= libro.getAutor()%></h6>
                    <ul>
                        <li class="card-text">Categoría: <%= libro.getCategoria()%></li>
                        <li class="card-text">ISBN: <%= libro.getIsbn()%></li>
                        <li class="card-text">Costo: Q <%= libro.getCosto()%></li>
                    </ul>
                    <button type="button" class="btn btn-secondary">Ver unidades disponibles</button>
                </div>
            </div>

            <%
                }
            %>
        </section>
    </body>
</html>
