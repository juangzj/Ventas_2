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
@WebServlet(name = "editarArticulo", urlPatterns = {"/editarArticulo"})
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
    int id = 0;
    GestionarArticulo gestiona = new GestionarArticulo();
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProducto = request.getParameter("idArticuloEditar");
        String nombre = request.getParameter("nombreEditar");
        String descripcion = request.getParameter("descripcionEditar");
        String precio = request.getParameter("precioEditar");
        String cantidad = request.getParameter("cantidadEditar");
        
        if(idProducto != null){
            id = Integer.parseInt(idProducto);
        }
        if(nombre != null && descripcion != null && precio != null && cantidad != null){
            gestiona.actualizarArticulo(id, nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(cantidad));
        }
        
        
        response.sendRedirect("articulos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
