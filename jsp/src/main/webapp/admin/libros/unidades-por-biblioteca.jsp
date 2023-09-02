

<%@page import="com.db.usuario.Usuario"%>
<%@page import="classes.UnidadesLibroBiblioteca"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.Biblioteca"%>
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
        <title>Unidades por biblioteca</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark">
        <jsp:include page="/admin/navbar.jsp"/>
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

            <%
                ArrayList<UnidadesLibroBiblioteca> ulbs = (ArrayList<UnidadesLibroBiblioteca>) request.getAttribute("ulbs");

                for (int i = 0; i < ulbs.size(); i++) {

                    UnidadesLibroBiblioteca ulb = ulbs.get(i);

            %>

            <div class="card col-3 m-3">
                <div class="card-body">
                    <h4 class="card-title"><%= ulb.getNombre()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Direccion: <%= ulb.getDireccion()%></h6>
                    <p class="card-text">Unidades <%= ulb.getUnidades()%></p>
                    <button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#biblioteca<%=i%>">
                        Modificar unidades
                    </button>

                </div>
            </div>

            <!-- Modal -->
            <div class="modal fade" id="biblioteca<%=i%>" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <form class="modal-content" method="POST" action="${pageContext.request.contextPath}/admin/libros/unidades-por-biblioteca" >
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="staticBackdropLabel"><%= ulb.getNombre()%></h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Direccion: <%= ulb.getDireccion()%></p>
                            <div class="input-group mb-3">
                                <span class="input-group-text" id="basic-addon1">Unidades: </span>
                                <input type="text" required class="form-control" name="unidades" value="<%= ulb.getUnidades()%>" aria-label="unidades" aria-describedby="basic-addon1">
                            </div>
                        </div>
                        <input type="text" class="form-control d-none" name="id" value="<%= ulb.getId()%>" aria-label="id" aria-describedby="basic-addon1">
                        <input type="text" class="form-control d-none" name="isbn" value="<%= ulb.getIsbn()%>" aria-label="id" aria-describedby="basic-addon1">
                        <div class="form-floating mx-3">
                            <textarea required class="form-control" placeholder="Motivo por el cual se está realizando el ajuste" id="floatingTextarea"></textarea>
                            <label  for="floatingTextarea">Justificacion</label>
                        </div>


                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" >Guardar cambios</button>
                        </div>
                    </form>
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