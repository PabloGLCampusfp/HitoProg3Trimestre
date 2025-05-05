import java.util.Scanner; //Importamos Java.util.scanner para poder usar el scanner

public class Principal { //Creamos la clase Principal
    public static void main(String[] args) { //Creamos una clase estatica
        Scanner scaner = new Scanner(System.in);
        int opcion = 0; //Le damos a la variable opcion el valor de 0
        while (opcion != 2) { // Creamos un while con la condicion de que la opcion no puede ser 2 (Si es 2 el while acabara)
        	//Cramos el menu
        	System.out.println("----MENU----");
            System.out.println("1 - Ver películas");
            System.out.println("2 - Salir");
            System.out.print("Elige una opción: ");
            if (scaner.hasNextInt()) {
                opcion = scaner.nextInt();
                if (opcion == 1) { //Si la opcion es 1 se veran las peliculas
                    Peliculas.verPeliculas();
                } else if (opcion == 2) { // Si la opcion es 2 el bucle while acabara y el programa se cerrara
                    System.out.println("Vas a salir");
                } else { // En el caso de dar una respuesta que no sea ni 1 ni 2, dara el siguiente error
                    System.out.println("Las unicas opciones que valen son el 1 y el 2");
                }
            } else { //En el caso de dar una respuesta que no sea ni 1 ni 2, dara el siguiente error
                System.out.println("Las unicas opciones que valen son el 1 y el 2");
                scaner.next();
            }
        }
        scaner.close(); //Cerramos el scanner
    }
}
