package servlerts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mundo.GestionarArticulo;
import mundo.GestionarUsuario;

/**
 *
 * @author Usuario 1
 */
@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {

   GestionarUsuario gestionar = new GestionarUsuario();
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
        
        String idUsuario = request.getParameter("idUsuarioEliminar");
        String confirmacion = request.getParameter("confirmacionEliminacion");
        
        if(idUsuario != null){
            id = Integer.parseInt(idUsuario);
        }
        if(confirmacion != null){
            gestionar.eliminarUsuario(id);
        }
        
        
        response.sendRedirect("usuarios.jsp");
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
