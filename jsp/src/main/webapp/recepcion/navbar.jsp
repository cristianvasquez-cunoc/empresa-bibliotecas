<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary position-sticky top-0 z-3 shadow-sm ">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/">Biblioteca</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link <%= (request.getParameter("active") != null && request.getParameter("active").equals("inicio")) ? "active" : "" %>" aria-current="page" 
                       href="${pageContext.request.contextPath}/recepcion/"
                       >Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= (request.getParameter("active") != null && request.getParameter("active").equals("agregar")) ? "active" : "" %>" aria-current="page" 
                       href="${pageContext.request.contextPath}/recepcion/agregar-usuario"
                       >Agregar usuario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= (request.getParameter("active") != null && request.getParameter("active").equals("prestamo")) ? "active" : "" %>" aria-current="page" 
                       href="${pageContext.request.contextPath}/recepcion/prestamo"
                       >Prestamo libro</a>
                </li>
            </ul>
        </div>
        <a class="nav-link" style="margin-right: 1.5rem" href="${pageContext.request.contextPath}/login?estado=unlogged">Cerrar sesion</a>
    </div>
</nav>