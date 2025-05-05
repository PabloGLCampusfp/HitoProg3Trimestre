import java.sql.*;
public class Peliculas {

     // Muestra por consola una lista de películas con su información y la sala correspondiente.
    public static void verPeliculas() {
        Connection conexion = Conexion.conectar(); // Conexión a la base de datos
        if (conexion == null) return;

        // Consulta SQL
        String sql = "SELECT p.id_pelicula, p.titulo, p.director, p.duracion, p.genero, s.nombre " +
                     "FROM peliculas p JOIN salas s ON p.id_sala = s.id_sala";

        try (Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql)) {

            // Iteración sobre los resultados y muestra de los datos
            while (resultado.next()) {
                String id = resultado.getString("id_pelicula");
                String titulo = resultado.getString("titulo");
                String director = resultado.getString("director");
                int duracion = resultado.getInt("duracion");
                String genero = resultado.getString("genero");
                String sala = resultado.getString("nombre");

                System.out.println(id + ", " + titulo + ", " + director + ", " + duracion + ", " + genero + ", " + sala);
                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
            // Manejo de errores SQL
            System.out.println("Error de datos: " + e.getMessage());
        }
    }
}