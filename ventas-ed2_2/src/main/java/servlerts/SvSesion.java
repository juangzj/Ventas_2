package servlerts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mundo.GestionarInicioSesion;

/**
 *
 * @author Usuario 1
 */
@WebServlet(name = "SvSesion", urlPatterns = {"/SvSesion"})
public class SvSesion extends HttpServlet {

    GestionarInicioSesion gestiona = new GestionarInicioSesion();

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

        String correo = request.getParameter("correoIngresar");
        String contrasenia = request.getParameter("contraseniaIngresar");

        String tipoUsuario = gestiona.ingresar(correo, contrasenia);

        if (tipoUsuario != null) {
            if (tipoUsuario.equals("Cliente")) {
                response.sendRedirect("vista-cliente-pricipal.jsp");
            }
            if (tipoUsuario.equals("Administrador")) {
                response.sendRedirect("administador-vista.jsp");
            }
        } else {

            response.sendRedirect("index.jsp");
            System.out.println("No puedo ingresar al sistema");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
