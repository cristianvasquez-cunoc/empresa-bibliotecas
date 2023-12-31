
<%@page import="com.db.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% if (session.getAttribute("usuario") != null) {
        response.sendRedirect("/");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>

    <body class="overflow-hidden" data-bs-theme="dark">

        <div class="row">

            <div class="col-6 d-flex align-items-center justify-content-center row">

                <form class="col-6 gap-4 d-flex flex-column" method="POST" action="${pageContext.request.contextPath}/login">

                    <h1 class="text-center mb-4">Inicio de sesión</h1>

                    <div class="mb-2">
                        <label for="username" class="form-label">Nombre de usuario</label>
                        <input type="text" class="form-control" id="username" name="username">
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="password" name="password">
                    </div>


                    <div class="d-grid">
                        <input type="submit" class="btn btn-primary" type="button" value="Iniciar Sesion"/>
                    </div>


                </form>

            </div>

            <div class="col-6 px-0 d-block">
                <img src="https://images.pexels.com/photos/1290141/pexels-photo-1290141.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                     alt="Login image" class="w-100 vh-100" style="object-fit: cover; object-position: center;">
            </div>

        </div>

        <%
            String errorMessage = (String) session.getAttribute("errorMessage");

            if (errorMessage != null && !errorMessage.isEmpty()) {
        %>

        <script>

            Swal.fire({
                icon: 'error',
                title: '<%=errorMessage%>'
            })

        </script>

        <%
            }
        %>

    </body>
</html>