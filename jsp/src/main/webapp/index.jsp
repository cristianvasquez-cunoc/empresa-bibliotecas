
<%@page import="com.db.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if(usuario != null) {
        switch (usuario.getRol()) {
                case 1:
                    response.sendRedirect("/dashboard/");
                    break;
                case 2:
                    response.sendRedirect("/admin/");
                    break;
                case 3:
                    response.sendRedirect("/recepcion/");
                    break;
                default:
                    throw new AssertionError();
            }
    }else {
        response.sendRedirect("/login/");
    }
%>