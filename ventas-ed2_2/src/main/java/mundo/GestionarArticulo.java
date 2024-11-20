/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class GestionarArticulo {

    /**
     * Método para mostrar los artículos.
     *
     * @return Lista de objetos Articulo con los datos de cada producto.
     */
    public List<Articulo> obtenerArticulos() {
        List<Articulo> articulos = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener la conexión a la base de datos
            conexion = Conectar.getConexion();

            // SQL para seleccionar todos los artículos, incluyendo imagen y nombre de imagen
            String sql = "SELECT id, nombre, descripcion, precio, cantidad_stock, nombre_imagen, imagen FROM articulos";

            // Preparar la sentencia
            pstmt = conexion.prepareStatement(sql);

            // Ejecutar la consulta
            rs = pstmt.executeQuery();

            // Iterar sobre los resultados
            while (rs.next()) {
                // Crear un nuevo objeto Articulo usando el constructor
                Articulo articulo = new Articulo(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad_stock"),
                        rs.getString("nombre_imagen"),
                        rs.getBytes("imagen") // Obtener la imagen en bytes
                );

                // Agregar el artículo a la lista
                articulos.add(articulo);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los artículos: " + e.getMessage());
            e.printStackTrace(); // Mostrar la traza del error para depuración
        } finally {
            // Cerrar recursos en el orden inverso a su apertura
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return articulos; // Retornar la lista de artículos
    }

    /**
     * Metodo para agregar un nuevo producto
     *
     * @param nombre El nombre del producto
     * @param descripcion La descripcion del producto
     * @param precio El precio del producto
     * @param cantidad La cantidad en stock
     * @param nombreImagen El nombre del archivo de imagen
     * @param imagen La imagen en formato byte[]
     * @return true si el producto fue agregado exitosamente, false en caso
     * contrario
     */
    public boolean agregarProducto(String nombre, String descripcion, double precio, int cantidad, String nombreImagen, byte[] imagen) {

        System.out.println("El nomrbe de la imagen es: " + nombreImagen);
        boolean productoAgregado = false;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;

        try {
            // Obtener la conexión a la base de datos
            conexion = Conectar.getConexion();

            // Definir la consulta SQL para insertar el producto
            String sql = "INSERT INTO articulos (nombre, descripcion, precio, cantidad_stock, nombre_imagen, imagen) VALUES (?, ?, ?, ?, ?, ?)";

            // Preparar la declaración
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, descripcion);
            preparedStatement.setDouble(3, precio);
            preparedStatement.setInt(4, cantidad);
            preparedStatement.setString(5, nombreImagen);
            preparedStatement.setBytes(6, imagen); // Establecer el campo imagen como byte[]

            // Ejecutar la inserción y verificar si se afectó alguna fila
            productoAgregado = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el producto: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos en el orden inverso a su apertura
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }

        return productoAgregado; // Retornar el resultado de la inserción
    }

    public GestionarArticulo() {
    }

    /**
     * Metodo para editar un articulo
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param precio
     * @param stock
     * @return
     */
    public boolean editarArticulo(int id, String nombre, String descripcion, double precio, int stock) {
        boolean articuloEditado = false;
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Establecer la conexión a la base de datos
            conexion = Conectar.getConexion();

            // SQL para actualizar el artículo en la base de datos
            String sql = "UPDATE articulos SET nombre = ?, descripcion = ?, precio = ?, cantidad_stock = ? WHERE id = ?";

            // Preparar la sentencia SQL
            stmt = conexion.prepareStatement(sql);

            // Establecer los valores de los parámetros
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, stock);
            stmt.setInt(5, id);

            // Ejecutar la actualización
            int filasAfectadas = stmt.executeUpdate();

            // Si se ha actualizado al menos un registro, se considera que la operación fue exitosa
            if (filasAfectadas > 0) {
                articuloEditado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        } finally {
            // Cerrar los recursos
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        }

        return articuloEditado;
    }


    /**
     * Metodo para eliminar un articulo segun el id
     *
     * @param id
     * @return
     */
    public boolean eliminarArticulo(int id) {
        boolean articuloEliminado = false;
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Obtener la conexión a la base de datos
            conexion = Conectar.getConexion();

            // Crear la consulta SQL para eliminar el artículo
            String sql = "DELETE FROM articulos WHERE id = ?";

            // Preparar la declaración
            stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id); // Establecer el ID del artículo a eliminar

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();

            // Verificar si se eliminó algún registro
            if (filasAfectadas > 0) {
                articuloEliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de la excepción en caso de error
        } finally {
            // Cerrar los recursos
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

        return articuloEliminado;
    }

    /**
     * Metodo para editar un articulo segun el id
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param precio
     * @param stock
     * @return
     */
    public boolean actualizarArticulo(int id, String nombre, String descripcion, double precio, int stock, String nombreImagen, byte[] imagen) {
        boolean articuloEditado = false;
        Connection conexion = null;
        PreparedStatement stmt = null;

        try {
            // Obtener la conexión a la base de datos
            conexion = Conectar.getConexion();

            // Crear la consulta SQL para actualizar el artículo
            String sql = "UPDATE articulos SET nombre = ?, descripcion = ?, precio = ?, cantidad_stock = ?, nombre_imagen = ?, imagen = ? WHERE id = ?";

            // Preparar la declaración
            stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);              // Establecer el nuevo nombre
            stmt.setString(2, descripcion);         // Establecer la nueva descripción
            stmt.setDouble(3, precio);              // Establecer el nuevo precio
            stmt.setInt(4, stock);                  // Establecer el nuevo stock
            stmt.setString(5, nombreImagen);        // Establecer el nuevo nombre de la imagen
            stmt.setBytes(6, imagen);               // Establecer la nueva imagen
            stmt.setInt(7, id);                     // Establecer el id del artículo a editar

            // Ejecutar la consulta
            int filasAfectadas = stmt.executeUpdate();

            // Verificar si se actualizó algún registro
            if (filasAfectadas > 0) {
                articuloEditado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de la excepción en caso de error
        } finally {
            // Cerrar los recursos
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

        return articuloEditado;
    }

}
