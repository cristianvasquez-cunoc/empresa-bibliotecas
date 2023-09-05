

<%@page import="classes.Biblioteca"%>
<%@page import="com.db.usuario.UsuarioRecepcion"%>
<%@page import="classes.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="classes.Libro"%>
<%@page import="com.db.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null || usuario.getRol() != 2)
        response.sendRedirect("/");

    else {

        UsuarioRecepcion usuarioR = (UsuarioRecepcion) request.getAttribute("usuarioR");


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar libro</title>
        <jsp:include page="/includes/resources.jsp"/>

    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="recepcion" />
        </jsp:include>
        <section class="container mt-3">
            <h1 class="h1 mb-3">Modificar usuario</h1>

            <form method="POST" action="${pageContext.request.contextPath}/admin/recepcion/modificar-usuario?codigo=<%=usuarioR.getCodigo()%>" id="formModificarUsuario">
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" value="<%=usuarioR.getNombre()%>" required name="nombre" class="form-control" id="nombre" aria-describedby="nombre">
                </div>
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" value="<%=usuarioR.getUsername()%>" required name="username" class="form-control" id="username" aria-describedby="username">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" placeholder="•••••" name="password" value="" class="form-control" id="password" aria-describedby="password">
                    <div class="form-text">Si no desea cambiar la password deje el campo vacio</div>

                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Correo electronico</label>
                    <input type="email" value="<%=usuarioR.getEmail()%>" required name="email" class="form-control" id="email" aria-describedby="email">
                </div>

                <div class="mb-3">
                    <label for="bibliotecas" class="form-label">Biblioteca</label>
                    <select required class="form-select" aria-label="Default select example" id="bibliotecas" name="bibliotecas">
                        <%
                            ArrayList<Biblioteca> bibliotecas = (ArrayList<Biblioteca>) request.getAttribute("bibliotecas");
                            for (Biblioteca bib : bibliotecas) {
                                if (bib.getCodigo().equals(String.valueOf(usuarioR.getBibliotecaCodigo()))) {

                        %>
                        <option selected value="<%=bib.getCodigo()%>"><%=bib.getNombre()%></option>
                        <%
                        } else {
                        %>
                        <option value="<%=bib.getCodigo()%>"><%=bib.getNombre()%></option>
                        <%
                                }
                            }

                        %>
                    </select>

                </div>
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado del usuario</label>
                    <select required class="form-select" aria-label="Default select example" id="estado" name="estado">
                        <%                            
                            Boolean isActivo = (Boolean) usuarioR.isActivo();
                            if (isActivo) {

                        %>
                        <option selected value="1">Activo</option>
                        <option  value="0">Inactivo</option>
                        <%                        } else {
                        %>
                        <option value="1">Activo</option>
                        <option selected value="0">Inactivo</option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Modificar</button>
            </form>
        </section>

        <%
            Boolean usuarioModificadoExitosamente = (Boolean) request.getAttribute("usuarioModificadoExitosamente");

            if (usuarioModificadoExitosamente) {
        %>

        <script>

            Swal.fire({
                icon: 'success',
                title: 'El usuario recepcionista se modificó exitosamente',
                timer: 2000
            })

        </script>

        <%
                request.setAttribute("usuarioModificadoExitosamente", false);
            }
        %>

    </body>
</html>
<%}%>