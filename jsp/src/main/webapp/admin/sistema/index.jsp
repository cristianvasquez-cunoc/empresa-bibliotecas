

<%@page import="classes.Administracion"%>
<%@page import="com.db.usuario.Usuario"%>
<!DOCTYPE html>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");

    if (usuario == null || usuario.getRol() != 2)
        response.sendRedirect("/");

    else {

        Administracion administracion = (Administracion) request.getAttribute("administracion");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recepcion</title>
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body data-bs-theme="dark" >
        <jsp:include page="/admin/navbar.jsp">
            <jsp:param name="active" value="sistema" />
        </jsp:include>
        <section class="container mt-3 mb-5">
            <h1>Parametros del sistema</h1>
            <h5 class="text-muted">Todos los precios se manejan en quetzales GTQ</h5>
            <form method="POST" action="${pageContext.request.contextPath}/admin/sistema" id="formAdministracion">
                <div class="mb-3">
                    <label for="diasSuspension" class="form-label">Días suspensión</label>
                    <input type="number" value="<%=administracion.getDiasSuspension()%>" required name="diasSuspension" class="form-control" id="diasSuspension" aria-describedby="diasSuspension">
                </div>
                <div class="mb-3">
                    <label for="multa" class="form-label">Multa por daño / pérdida</label>
                    <input type="number" step="any" value="<%=administracion.getMulta()%>" required name="multa" class="form-control" id="multa" aria-describedby="multa">
                </div>
                <div class="mb-3">
                    <label for="costoDomicilio" class="form-label">Costo entrega a domicilio</label>
                    <input type="number" step="any" value="<%=administracion.getCostoDomicilio()%>" required name="costoDomicilio" class="form-control" id="costoDomicilio" aria-describedby="costoDomicilio">
                </div>
                <div class="mb-3">
                    <label for="costoSuscripcion" class="form-label">Costo suscripción premium</label>
                    <input type="number" step="any" value="<%=administracion.getCostoSuscripcion()%>" required name="costoSuscripcion" class="form-control" id="costoSuscripcion" aria-describedby="costoSuscripcion">
                </div>
                <div class="mb-3">
                    <label for="descuentoDomicilioPremium" class="form-label">Descuento entrega a domicilio para usuarios premium</label>
                    <input type="number" step="any" value="<%=administracion.getDescuentoDomicilioPremium()%>" required name="descuentoDomicilioPremium" class="form-control" id="descuentoDomicilioPremium" aria-describedby="descuentoDomicilioPremium">
                </div>
                <div class="mb-3">
                    <label for="limiteDias" class="form-label">Limite de días para devolver un libro</label>
                    <input type="number" value="<%=administracion.getLimiteDias()%>" required name="limiteDias" class="form-control" id="limiteDias" aria-describedby="limiteDias">
                </div>
                <div class="mb-3">
                    <label for="limiteLibros" class="form-label">Limite de libros prestados</label>
                    <input type="number" value="<%=administracion.getLimiteLibros()%>" required name="limiteLibros" class="form-control" id="limiteLibros" aria-describedby="limiteLibros">
                </div>
                <div class="mb-3">
                    <label for="limiteDiasPremium" class="form-label">Limite de dias para devolver un libro para usuarios premium</label>
                    <input type="number" value="<%=administracion.getLimiteDiasPremium()%>" required name="limiteDiasPremium" class="form-control" id="limiteDiasPremium" aria-describedby="limiteDiasPremium">
                </div>
                <div class="mb-3">
                    <label for="limiteLibrosPremium" class="form-label">Limite de libros prestados para un usuario premium</label>
                    <input type="number" value="<%=administracion.getLimiteLibrosPremium()%>" required name="limiteLibrosPremium" class="form-control" id="limiteLibrosPremium" aria-describedby="limiteLibrosPremium">
                </div>

                <button type="submit" class="btn btn-primary">Actualizar datos</button>
            </form>
        </section>

        <%
            Boolean datosActualizados = (Boolean) request.getAttribute("datosActualizados");

            if (datosActualizados) {
        %>

        <script>

            Swal.fire({
                icon: 'success',
                title: 'Los datos se actualizaron exitosamente',
                timer: 2000
            })

        </script>

        <%}%>
    </body>
</html>
<%}%>