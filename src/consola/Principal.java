package consola;

import dominio.Pieza;
import static dominio.Tipos.*;
import negocio.ImplementacionEjercicios;

/**
 *
 * @author Angonoa Franco
 * @version 1.0
 * @since Diciembre 2020
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ImplementacionEjercicios ie = new ImplementacionEjercicios();
        
        ie.ejercicioUnoA("PF001P", new Pieza("Revestimiento de rodillos", POLIURETANO));
        ie.ejercicioUnoA("PF002P", new Pieza("Amortiguación", POLIURETANO));
        ie.ejercicioUnoA("PF003C", new Pieza("Burletes", POLIURETANO));
        ie.ejercicioUnoA("PF004C", new Pieza("Diafragmas", CAUCHO));
        ie.ejercicioUnoA("PF005T", new Pieza("Acrilicos", CAUCHO));
        ie.ejercicioUnoA("PF006T", new Pieza("Derling", TERMOPLASTICO));
        
        System.out.println("\n===EJERCICIO 1 B===");
        System.out.println(ie.ejercicioUnoB());
        
        System.out.println("\n===EJERCICIO 2 A===");
        ie.ejercicioDosA();
        
        System.out.println("Consultar carpeta del proyecto: Persistencia - MapSerializado.dat");
        
        System.out.println("\n===EJERCICIO 2 B===");
        System.out.println(ie.ejercicioDosB());
        
        System.out.println("\n===EJERCICIO 3===");
        ie.ejercicioTres();
        System.out.println("Consultar carpeta del proyecto: Persistencia - Listado de Piezas.txt");
        
        System.out.println("\n===EJERCICIO 4 A===");
        ie.ejercicioCuatroA();
        System.out.println("Consultar base de datos. SELECT * FROM Piezas;");
        
        System.out.println("\n===EJERCICIO 4 B===");
        ie.ejercicioCuatroB();
        System.out.println("Consultar base de datos. SELECT * FROM Reserva;");

        System.out.println("\n===EJERCICIO 5===");
        ie.ejercicioCinco();
        System.out.println("Consultar carpeta del proyecto: Persistencia - Filtro.txt");
    }
    
}
