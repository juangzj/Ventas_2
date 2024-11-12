<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="mundo.Articulo"%>
<%@page import="mundo.GestionarArticulo"%>
<%@page import="mundo.GestionarUsuario"%>
<%@include file="libs/header.jsp" %>

<%@include file="libs/navbar-usuario-ingresado.jsp" %>

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
                <th scope="col">Imagen</th>
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
                    String imagenBase64 = "";
                    for (Articulo a : listaArticulos) {
                    // Convertimos los bytes en base64 solo si no es null
                            imagenBase64 = Base64.getEncoder().encodeToString(a.getImagen()).trim();
            %>

            <tr>
          <td><%=a.getId()%></td>  
                <td><%=a.getNombre()%></td>
                <td><img src="data:image/png;base64,<%=imagenBase64%>" style="max-width: 100px; max-height: 80px; object-fit: cover;" /></td>
                <td><%=a.getDescripcion()%></td>
                <td><%=a.getPrecio()%></td>
                <td><%=a.getCantidad()%></td>
                <td>
                    <a href="#" class="btn custom-btn btn-sm">comprar</a>
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