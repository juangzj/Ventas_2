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

        // Obtener los parámetros de la solicitud
        String idProducto = request.getParameter("idEditar");
        String nombre = request.getParameter("nombreEditar");
        String descripcion = request.getParameter("descripcionEditar");
        String precio = request.getParameter("precioEditar");
        String cantidad = request.getParameter("stockEditar");
        Part imagen = request.getPart("imagenEditar");

        // Inicializar variables para la imagen
        byte[] datosImagen = null;
        String nombreImagen = null;

        // Verificar si se ha cargado una nueva imagen
        if (imagen != null && imagen.getSize() > 0) {
            // Obtener los datos de la imagen
            try (InputStream inputStream = imagen.getInputStream()) {
                datosImagen = inputStream.readAllBytes();
            }
            // Obtener el nombre de la imagen
            nombreImagen = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
        }

        // Actualizar artículo con o sin imagen
        if (datosImagen == null) {
            gestiona.editarArticulo(Integer.parseInt(idProducto), nombre, descripcion,
                    Double.parseDouble(precio), Integer.parseInt(cantidad));
        } else {
            gestiona.actualizarArticulo(Integer.parseInt(idProducto), nombre, descripcion,
                    Double.parseDouble(precio), Integer.parseInt(cantidad), nombreImagen, datosImagen);
        }

        // Redirigir después de la actualización
        response.sendRedirect("articulos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
