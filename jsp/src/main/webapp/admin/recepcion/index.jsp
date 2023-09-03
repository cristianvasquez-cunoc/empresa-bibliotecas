

<%@page import="com.db.usuario.Usuario"%>
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
        <title>Modulo Recepcion</title>
        <jsp:include page="/includes/resources.jsp"/>

    </head>
    <body data-bs-theme="dark">
        <jsp:include page="/admin/navbar.jsp"/>
        <section class="container mt-4">
            <h1 >Hello World!</h1>
        </section>
    </body>
</html>

<%
    };
%>