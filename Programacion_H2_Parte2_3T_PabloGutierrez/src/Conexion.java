import java.sql.*;

public class Conexion {
    public static Connection conectar() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3307/cine_pablogutierrez", "root", ""); // la URL
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage()); //Si no se puede conectar a la base de datos saltara el error
            return null;
        }
    }
}
