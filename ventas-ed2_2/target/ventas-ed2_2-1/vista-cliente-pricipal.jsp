<%@page import="java.util.List"%>
<%@page import="mundo.Articulo"%>
<%@page import="mundo.GestionarArticulo"%>
<%@page import="mundo.GestionarUsuario"%>
<%@include file="libs/header.jsp" %>

<%@include file="libs/navbar-vista-principal.jsp" %>

<div class="container-fluid p-0">
    <img src="imagenes/electrodomesticos.png" class="img-fluid w-100" style="height: 400px; object-fit: cover;" alt="Imagen de ancho completo">
</div>

<div class="container py-5">
    <h1 class="text-center mb-5">Catálogo de Electrodomésticos</h1>

    <table class="table custom-table table-striped table-bordered">
        <thead class="table-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nombre del Producto</th>
                <th scope="col">Descripción</th>
                <th scope="col">Precio</th>
                <th scope="col">Cantidad de Stock</th>
                <th scope="col">Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                GestionarArticulo gestiona = new GestionarArticulo();
                List<Articulo> listaArticulos = gestiona.obtenerArticulos();

                if (listaArticulos != null) {

                    for (Articulo articulo : listaArticulos) {
            %>

            <tr>
                <th scope="row"><%= articulo.getId() %></th>
                <td><%= articulo.getNombre()%></td>
                <td><%= articulo.getDescripcion()%></td>
                <td class="text-success"><%= articulo.getPrecio()%></td>
                <td class="text-success"><%= articulo.getCantidad()%></td>
                <td>
                    <a href="index.jsp" class="btn custom-btn btn-sm">Ingresar y comprar</a>
                </td>
            </tr>
            <%
                    }
                }else{
            %>
            <tr> 
                <td colspan="5" class="text-center">No hay datos que mostrar</td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<footer class="text-center mt-5 p-4 bg-dark text-light">
    <p>© 2024 Electrodomésticos. Todos los derechos reservados.</p>
    <div>
        <a href="#" class="text-light me-2">Facebook</a>
        <a href="#" class="text-light">Twitter</a>
    </div>
</footer>
<%@include file="libs/foother.jsp" %>