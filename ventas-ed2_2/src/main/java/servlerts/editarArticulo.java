package servlerts;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import mundo.GestionarArticulo;

/**
 *
 * @author Usuario 1
 */
@WebServlet(name = "editarArticulo", urlPatterns = {"/editarArticulo"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class editarArticulo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    GestionarArticulo gestiona = new GestionarArticulo();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProducto = request.getParameter("idEditar");
        String nombre = request.getParameter("nombreEditar");
        String descripcion = request.getParameter("descripcionEditar");
        String precio = request.getParameter("precioEditar");
        String cantidad = request.getParameter("stockEditar");
        Part imagen = request.getPart("imagenEditar");

        // Imprimir para verificar que las variables están llegando
        System.out.println("ID Producto: " + idProducto);
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Precio: " + precio);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Imagen: " + (imagen != null ? "Imagen recibida" : "Imagen no recibida"));
        
        if (idProducto != null && nombre != null && descripcion != null && precio != null && cantidad != null && imagen != null) {

            //Obtenemos el nombre de la imagen
            String nombreImagen = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();

            //Obtenemos los bytes de la imagen
            byte[] datosImagen;
            try (InputStream inputStream = imagen.getInputStream()) {
                datosImagen = inputStream.readAllBytes();
            }

            gestiona.actualizarArticulo(Integer.parseInt(idProducto), nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(cantidad), nombreImagen, datosImagen);
        }

        response.sendRedirect("articulos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
