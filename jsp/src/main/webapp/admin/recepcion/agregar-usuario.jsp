<%@page import="classes.Biblioteca"%>
<%@page import="classes.Categoria"%>
<%@page import="java.util.ArrayList"%>
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
        <title>Agregar Usuario Recepcion</title>
        <jsp:include page="/includes/resources.jsp"/>

    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="recepcion" />
        </jsp:include>
        <section class="container mt-3">
            <h1 class="h1 mb-3">Agregar Usuario</h1>

            <form method="POST" action="${pageContext.request.contextPath}/admin/recepcion/agregar-usuario" id="formAgregarUsuario">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" required name="nombre" class="form-control" id="nombre" aria-describedby="nombre">
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" required name="username" class="form-control" id="username" aria-describedby="username">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" required name="password" class="form-control" id="password" aria-describedby="password">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electronico</label>
                    <input type="email" required name="email" class="form-control" id="email" aria-describedby="email">
                </div>
                
                <div class="mb-3">
                    <label for="bibliotecas" class="form-label">Biblioteca</label>
                    <select required class="form-select" aria-label="Default select example" id="bibliotecas" name="bibliotecas">
                        <%                            
                            ArrayList<Biblioteca> bibliotecas = (ArrayList<Biblioteca>) request.getAttribute("bibliotecas");
                            for (Biblioteca bib : bibliotecas) {

                        %>
                        <option value="<%=bib.getCodigo()%>"><%=bib.getNombre()%></option>
                        <%                            }
                        %>
                    </select>

                </div>
                <button type="submit" class="btn btn-primary">Guardar</button>
            </form>
        </section>
        <script src="${pageContext.request.contextPath}/js/mostrarCategoriaForm.js"></script>
        <script src="${pageContext.request.contextPath}/js/validaciones.js"></script>
        <%
            Boolean agregadoExitosamente = (Boolean) request.getAttribute("agregadoExitosamente");

            if (agregadoExitosamente) {
        %>

        <script>

            Swal.fire({
                icon: 'success',
                title: 'El usuario se agregó exitosamente',
                timer: 2000
            })

        </script>

        <%
            }
        %>
    </body>
</html>
<%    };
%>