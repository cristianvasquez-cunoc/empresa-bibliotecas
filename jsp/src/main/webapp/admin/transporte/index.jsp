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
        <title>Transporte</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="transporte" />
        </jsp:include>
        <div class="d-flex flex-row align-items-center justify-content-center" style="">

            <h1 class="m-4 text-center">Lista de usuarios transportistas</h1>
            <a href="${pageContext.request.contextPath}/admin/transporte/agregar-usuario" type="button" class="btn btn-primary d-flex align-items-center justify-content-center p-3 flex-row">
                <p class="mb-0">Agregar</p>
                &nbsp;&nbsp;
                <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><style>svg{
                        fill:#f9f9f9;
                        font-size: 1.5rem
                    }</style><path d="M256 80c0-17.7-14.3-32-32-32s-32 14.3-32 32V224H48c-17.7 0-32 14.3-32 32s14.3 32 32 32H192V432c0 17.7 14.3 32 32 32s32-14.3 32-32V288H400c17.7 0 32-14.3 32-32s-14.3-32-32-32H256V80z"/></svg>
            </a>

        </div>

        <section id="usuarios" class="container" style="display: flex;flex-wrap: wrap;justify-content: center;">
            <%
                ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");

                for (int i = 0; i < usuarios.size(); i++) {

                    Usuario us = usuarios.get(i);
            %>

            <div class="card col-3 m-3">
                <div class="card-body">
                    <h4 class="card-title"><%= us.getNombre()%></h4>
                    <h6 class="card-subtitle mb-2 text-body-secondary">Username: <%= us.getUsername()%></h6>
                    <ul>
                        <li class="card-text">Email: <%= us.getEmail()%></li>
                        <li class="card-text">Estado: <%= us.isActivo() ? "Activo" : "Inactivo"%></li>
                    </ul>
                    <div class="d-flex flex-column">

                        <a href="${pageContext.request.contextPath}/admin/transporte/modificar-usuario?codigo=<%=us.getCodigo()%>" type="button" class="btn btn-primary mb-3">
                            Modificar
                        </a>
                    </div>
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