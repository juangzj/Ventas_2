
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
@WebServlet(name = "eliminarArticulo", urlPatterns = {"/eliminarArticulo"})
public class eliminarArticulo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    GestionarArticulo gestiona = new GestionarArticulo();
            
    int id = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idProducto = request.getParameter("idArticuloEliminar");
        String eliminar = request.getParameter("eliminacion");
        
        if(idProducto != null){
            id = Integer.parseInt(idProducto);
        }
        if(eliminar != null){
            gestiona.eliminarArticulo(id);
        }
        response.sendRedirect("articulos.jsp");
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
