
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
@WebServlet(name = "SvNuevoArticulo", urlPatterns = {"/SvNuevoArticulo"})
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class SvNuevoArticulo extends HttpServlet {


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
        
        String nombre = request.getParameter("nombreAgregar");
        String descripcion = request.getParameter("descripcionAgregar");
        String precio = request.getParameter("precioAgregar");
        String cantidad = request.getParameter("cantidadAgregar");
        Part imagen = request.getPart("imagenProducto");
        
        if(nombre != null && descripcion != null && precio != null && cantidad != null && imagen != null ){
        //Obtenemos el nombre de la imagen
        String tituloImagen = Paths.get(imagen.getSubmittedFileName()).getFileName().toString();
        
            //System.out.println("el titulo de la imagen es: " + tituloImagen);
        //Obtenemos los bytes de la imagen
        byte[] datosImagen;
        try (InputStream inputStream = imagen.getInputStream()) {
            datosImagen = inputStream.readAllBytes();
        }
        
        gestiona.agregarProducto(nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(cantidad), tituloImagen, datosImagen );
        }
        response.sendRedirect("articulos.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
