import java.sql.*;
import java.util.Scanner;

public class Peliculas {

	// Muestra por consola una lista de películas con su información y la sala correspondiente.
    public static void verPeliculas() {
        Connection conexion = Conexion.conectar(); // Conexión a la base de datos
        if (conexion == null) return;
        // Consulta SQL
        String sql = "SELECT p.id_pelicula, p.titulo, p.director, p.duracion, p.genero, s.nombre " +
                     "FROM peliculas p JOIN salas s ON p.id_sala = s.id_sala";

        try (Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {
        	// Iteración sobre los resultados y muestra de los datos
            while (resultado.next()) {
                String id = resultado.getString("id_pelicula");
                String titulo = resultado.getString("titulo");
                String director = resultado.getString("director");
                int duracion = resultado.getInt("duracion");
                String genero = resultado.getString("genero");
                String sala = resultado.getString("nombre");

                System.out.println(id + ", " + titulo + ", " + director + ", " +
                                   duracion + " min, " + genero + ", Sala: " + sala);
                System.out.println("----------------------------------------------------");
            }
        } catch (SQLException e) {
        	// Manejo de errores SQL
            System.out.println("Error de datos: " + e.getMessage());
        }
    }

    // Añadir una nueva película a la base de datos
    public static void anadirPelicula(Scanner scanner) {
        Connection conexion = Conexion.conectar();
        // Si la conexion es nula dejara de seguir con el metodo
        if (conexion == null) return;

        try {
            System.out.print("ID de la película: ");
            String id = scanner.next();

            // Vamos a comprobar si la pelicula ya existe
            String ComprobarSQL = "SELECT * FROM peliculas WHERE id_pelicula = ?";
            PreparedStatement ComprobarSiExiste = conexion.prepareStatement(ComprobarSQL); //Prepara la sentecia SQL
            ComprobarSiExiste.setString(1, id);
            ResultSet resultado = ComprobarSiExiste.executeQuery();
            if (resultado.next()) {
                System.out.println("Ya existe una película con ese ID.");
                return;
            }

            // Pedimos los datos
            System.out.print("Título: ");
            String titulo = scanner.next();
            System.out.print("Director: ");
            String director = scanner.next();
            System.out.print("Duración: ");
            int duracion = scanner.nextInt();
            System.out.print("Género: ");
            String genero = scanner.next();
            System.out.print("ID de sala (Recuerda que solo puede ser un numero del 1 al 4): ");
            int idSala = scanner.nextInt();

            String insertarSQL = "INSERT INTO peliculas VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertarSentencia = conexion.prepareStatement(insertarSQL);
            //configuramos los valores de la sentencia SQL
            insertarSentencia.setString(1, id);
            insertarSentencia.setString(2, titulo);
            insertarSentencia.setString(3, director);
            insertarSentencia.setInt(4, duracion);
            insertarSentencia.setString(5, genero);
            insertarSentencia.setInt(6, idSala);

            int filas = insertarSentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("Película añadida correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al añadir: " + e.getMessage());
        }
    }

    // Eliminar una película
    public static void eliminarPelicula(Scanner scanner) {
        Connection conexion = Conexion.conectar();
        if (conexion == null) return;

        try {
            System.out.print("ID de la película a eliminar: ");
            String id = scanner.next();

            String EliminarSQL = "DELETE FROM peliculas WHERE id_pelicula = ?";
            PreparedStatement EliminarSentencia = conexion.prepareStatement(EliminarSQL);
            EliminarSentencia.setString(1, id);

            int filas = EliminarSentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("La pelicula a sido elimianda de la base de datos");
            } else {
                System.out.println("El id que has puesto no existe");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }
    }

    // Modificar al menos dos campos de una película
    public static void modificarPelicula(Scanner scanner) {
        Connection conexion = Conexion.conectar();
        if (conexion == null) return;

        try {
            System.out.print("ID de la película a modificar: ");
            String id = scanner.next();

            String ComprobarSQL = "SELECT * FROM peliculas WHERE id_pelicula = ?";
            PreparedStatement ComprobarSiExiste = conexion.prepareStatement(ComprobarSQL);
            ComprobarSiExiste.setString(1, id);
            ResultSet resultado = ComprobarSiExiste.executeQuery();

            if (!resultado.next()) {
                System.out.println("No existe una película con ese ID.");
                return;
            }

            System.out.print("Nuevo título de la pelicula: ");
            String nuevoTitulo = scanner.next();
            System.out.print("Nueva duración de la pelicula: ");
            int nuevaDuracion = scanner.nextInt();

            String ActualizarSQL = "UPDATE peliculas SET titulo = ?, duracion = ? WHERE id_pelicula = ?";
            PreparedStatement ActualizarSentencia = conexion.prepareStatement(ActualizarSQL);
            ActualizarSentencia.setString(1, nuevoTitulo);
            ActualizarSentencia.setInt(2, nuevaDuracion);
            ActualizarSentencia.setString(3, id);

            int filas = ActualizarSentencia.executeUpdate();
            if (filas > 0) {
                System.out.println("La pelicula se ha actualizado correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
        }
    }
}
