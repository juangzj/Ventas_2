package mundo;

import Conexion.Conectar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario 1
 */
public class GestionarUsuario {

    /**
     * Metodo para retornar los usuarios existentes
     *
     * @return
     */
    public List<Usuario> darUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection conexion = null;

        try {
            conexion = Conectar.getConexion(); // Obtenemos la conexión

            String query = "SELECT * FROM usuarios";
            PreparedStatement stmt = conexion.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String contrasenia = rs.getString("contrasenia");
                String rol = rs.getString("rol");

                Usuario usuario = new Usuario(id, nombre, email, contrasenia, rol);
                usuarios.add(usuario);
            }

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexion != null && !conexion.isClosed()) {
                    conexion.close(); // Cerramos la conexión
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return usuarios;
    }

    public GestionarUsuario() {
    }

    /**
     * Metodo para eliminar un usuario segun el id ingresado
     *
     * @param id
     * @return
     */
    public boolean eliminarUsuario(int id) {
        boolean usuarioEliminado = false;
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Establecer conexión con la base de datos
            conexion = Conectar.getConexion();

            // Preparar la consulta SQL para eliminar el usuario
            String sql = "DELETE FROM usuarios WHERE id = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();

            // Si al menos una fila fue afectada, se eliminó un usuario
            if (filasAfectadas > 0) {
                usuarioEliminado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarioEliminado;
    }

    /**
     * Metodo para editar un usuario
     *
     * @param id
     * @param nombre
     * @param correo
     * @param rol
     * @return
     */
    public boolean editarUsuario(int id, String nombre, String correo, String rol) {
        boolean usuarioEditado = false;
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Establecer conexión con la base de datos
            conexion = Conectar.getConexion();

            // Preparar la consulta SQL para actualizar el usuario
            String sql = "UPDATE usuarios SET nombre = ?, email = ?, rol = ? WHERE id = ?";
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setString(3, rol);
            stmt.setInt(4, id);

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();

            // Si al menos una fila fue afectada, significa que el usuario fue editado
            if (filasAfectadas > 0) {
                usuarioEditado = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuarioEditado;
    }

}
