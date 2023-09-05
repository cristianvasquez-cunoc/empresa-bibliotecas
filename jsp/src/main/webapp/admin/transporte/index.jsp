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
        
        <script src="${pageContext.request.contextPath}/js/filtrarRecepcionistasBiblioteca.js"></script>
    </body>
</html>
<%
    }
    ;
%>