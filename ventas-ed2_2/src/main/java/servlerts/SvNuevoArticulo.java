
package servlerts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mundo.GestionarArticulo;

/**
 *
 * @author Usuario 1
 */
@WebServlet(name = "SvNuevoArticulo", urlPatterns = {"/SvNuevoArticulo"})
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
        
        gestiona.agregarProducto(nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(cantidad));
        
        response.sendRedirect("articulos.jsp");
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
