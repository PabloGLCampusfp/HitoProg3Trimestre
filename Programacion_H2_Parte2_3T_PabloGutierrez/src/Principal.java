import java.util.Scanner; //Importamos Java.util.scanner para poder usar el scanner

public class Principal { //Creamos la clase Principal
    public static void main(String[] args) { //Creamos una clase estatica
        Scanner scanner = new Scanner(System.in);
        int opcion = 0; //Le damos a la variable opcion el valor de 0
        while (opcion != 5) { // Creamos un while con la condicion de que la opcion no puede ser 5 (Si es 5 el while acabara)
        	//Cramos el menu
        	System.out.println("----MENU----");
            System.out.println("1 - Ver películas");
            System.out.println("2 - Añadir películas(A la hora de añadir una pelicula Recuerda que el numero de la sala tiene que ser del 1 al 42)");
            System.out.println("3 - Eliminar películas");
            System.out.println("4 - Modificar películas");
            System.out.println("5 - Salir");
            System.out.print("Elige una opción: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                if (opcion == 1) { // Si la opcion es 1 se veran las peliculas
                    Peliculas.verPeliculas();
                } else if (opcion == 2) { //Si la opcion es 2 nos dejara añadir una pelicula
                    Peliculas.anadirPelicula(scanner);
                } else if (opcion == 3) { // Si la opcion es 3 nos permitira eliminar una pelicula
                    Peliculas.eliminarPelicula(scanner);
                } else if (opcion == 4) { //Si la opcion es 4 nos permitira modificar una pelicula
                    Peliculas.modificarPelicula(scanner);
                } else if (opcion == 5) { // Si la opcion es 5 el bucle while acabara y el programa se cerrara
                    System.out.println("Vas a salir del programa");
                } else {
                    System.out.println("Las únicas opciones válidas son del 1 al 5.");
                }
            } else {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // Limpiar entrada inválida
            }
        }

        scanner.close(); // Cerramos el Scanner
    }
}