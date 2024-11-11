
package servlerts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mundo.GestionarUsuario;

/**
 *
 * @author Usuario 1
 */
@WebServlet(name = "SvEditarUsuario", urlPatterns = {"/SvEditarUsuario"})
public class SvEditarUsuario extends HttpServlet {

    GestionarUsuario gestiona = new GestionarUsuario();
    int id = 0;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        String idUsuario = request.getParameter("idUsuarioEditar");
        String nombre = request.getParameter("nombreEditar");
        String correo = request.getParameter("emailEditar");
        String rol = request.getParameter("rolEditar");
        
        
        if(idUsuario != null){
            id = Integer.parseInt(idUsuario);
        }
        if(nombre != null && correo != null && rol != null){
            gestiona.editarUsuario(id, nombre, correo, rol);
        }
        
        response.sendRedirect("usuarios.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
