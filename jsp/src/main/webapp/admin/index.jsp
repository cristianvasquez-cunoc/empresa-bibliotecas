
<%@page import="com.db.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    
    if (usuario == null || usuario.getRol() != 2) response.sendRedirect("/");
    
    else {
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome admin user <%=usuario.getNombre()%></h1>
    </body>
</html>

<% 
    };
%>