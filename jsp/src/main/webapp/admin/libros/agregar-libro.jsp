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
        <title>Agregar libro</title>
        <jsp:include page="/includes/resources.jsp"/>

    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="libros" />
        </jsp:include>
        <section class="container mt-3">
            <h1 class="h1 mb-3">Agregar libro</h1>

            <form method="POST" action="${pageContext.request.contextPath}/admin/libros/agregar" id="formAgregarLibro">
                <div class="mb-3">
                    <label for="isbn" class="form-label">Isbn</label>
                    <input type="text" required name="isbn" class="form-control" id="isbn" aria-describedby="isbn">
                    <div id="isbn" class="form-text">Valor numérico que no exista en la base de datos</div>
                </div>
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" required name="nombre" class="form-control" id="nombre" aria-describedby="nombre">
                </div>
                <div class="mb-3">
                    <label for="autor" class="form-label">Autor</label>
                    <input type="text" required name="autor" class="form-control" id="autor" aria-describedby="autor">
                </div>
                <div class="mb-3">
                    <label for="costo" class="form-label">Costo</label>
                    <input type="number" step="any" value=0 required name="costo" class="form-control" id="costo" aria-describedby="costo">
                    <div id="costo" class="form-text">El costo esta en quetzales, debe ser un numero y puede ser decimal</div>
                </div>
                <div class="mb-3">
                    <label for="categoria" class="form-label">Categorias</label>
                    <select required class="form-select" aria-label="Default select example" id="seleccionCategoria" name="seleccionCategoria">
                        <option selected>Selecciona una opcion</option>
                        <%                            ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
                            for (Categoria categ : categorias) {

                        %>
                        <option value="<%=categ.getCodigo()%>"><%=categ.getName()%></option>
                        <%                            }
                        %>
                        <option value="otra">Otra</option>
                    </select>

                </div>
                <div id="categoriaForm" class="d-none">
                    <div class="mb-3">
                        <label for="nombreCategoria" class="form-label">Nombre de la categoria</label>
                        <input type="text" name="nombreCategoria" class="form-control" id="nombreCategoria" aria-describedby="nombre">
                    </div>
                    <div class="form-floating mb-3">
                        <textarea class="form-control" placeholder="" id="floatingTextarea" name="descripcionCategoria"></textarea>
                        <label for="floatingTextarea">Descripcion de la categoria</label>
                    </div>

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
                title: 'El libro se agregó exitosamente',
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