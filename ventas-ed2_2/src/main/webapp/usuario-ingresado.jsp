 <%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="mundo.Articulo"%>
<%@page import="mundo.GestionarArticulo"%>
<%@page import="mundo.GestionarUsuario"%>
<%@include file="libs/header.jsp" %>

<%@include file="libs/navbar-usuario-ingresado.jsp" %>

<div class="container-fluid p-0">
    <img src="imagenes/electrodomesticos.png" class="img-fluid w-100" style="height: 250px; object-fit: cover;" alt="Imagen de ancho completo">
</div>

<div class="container py-5">
    <h1 class="text-center mb-5">Catálogo de Electrodomésticos</h1>

    <div class="row">
        <%
            GestionarArticulo gestiona = new GestionarArticulo();
            List<Articulo> listaArticulos = gestiona.obtenerArticulos();
        %>

        <%
            if (listaArticulos != null && !listaArticulos.isEmpty()) {
                String imagenBase64 = "";
                for (Articulo a : listaArticulos) {
                    imagenBase64 = Base64.getEncoder().encodeToString(a.getImagen()).trim();
        %>

        <!-- Tarjeta de producto -->
        <div class="col-md-4 mb-4">
            <div class="card h-100 shadow-sm">
                <img src="data:image/png;base64,<%=imagenBase64%>" class="card-img-top" alt="<%=a.getNombre()%>" style="height: 200px; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title"><%=a.getNombre()%></h5>
                    <p class="card-text"><%=a.getDescripcion()%></p>
                    <p class="card-text"><strong>Precio:</strong> $<%=a.getPrecio()%></p>
                    <p class="card-text"><strong>Stock:</strong> <%=a.getCantidad()%> unidades</p>
                </div>
                <div class="card-footer text-center">
                    <a href="#" class="btn btn-success btn-sm w-100">Comprar</a>
                </div>
            </div>
        </div>

        <%
                }
            } else {
        %>

        <!-- Mensaje cuando no hay productos -->
        <div class="col-12 text-center">
            <p class="alert alert-warning">No hay productos disponibles en este momento</p>
        </div>

        <%
            }
        %>
    </div> <!-- Fin de la fila -->

</div>

<footer class="bg-dark text-light py-4">
    <div class="container text-center">
        <p>© 2024 Electrodomésticos. Todos los derechos reservados.</p>
        <div>
            <a href="#" class="text-light me-2">Facebook</a>
            <a href="#" class="text-light">Twitter</a>
        </div>
    </div>
</footer>

        <%@include file="libs/foother.jsp" %>
