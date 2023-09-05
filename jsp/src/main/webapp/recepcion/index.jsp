

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
        <title>Pagina recepcion</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark">
        <h1 class="container mt-4">Bienvenido usuario recepcion <%=usuario.getNombre()%>!</h1>
    </body>
</html>

<%
    };
%>