

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
        <jsp:include page="/includes/resourcesRecepcion.jsp"/>
    </head>
    <body data-bs-theme="dark">
        <jsp:include page="/recepcion/navbar.jsp">
            <jsp:param name="active" value="inicio" />
        </jsp:include>
        <h1 class="container mt-4">Bienvenido usuario recepcion <%=usuario.getNombre()%>!</h1>
        
    </body>
</html>

<%
    };
%>