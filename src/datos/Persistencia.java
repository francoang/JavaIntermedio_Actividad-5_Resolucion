
package datos;

import dominio.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Interfaz que representa los métodos a ejecutar para realizar la persistencia.
 *
 * @author Angonoa Franco
 * @since Julio 2020
 * @version 1.0 (Julio), 1.5 (Diciembre)
 */
public interface Persistencia {
    
    public void ejercicioUnoA(String id, Pieza pieza);
    
    public String ejercicioUnoB();
    
    public void ejercicioDosA();
    
    public Map<String,Pieza> ejercicioDosB();
    
    public void ejercicioTres() throws IOException, FileNotFoundException;
    
    public void ejercicioCuatroA();
    
    public void ejercicioCuatroB();
    
    public void ejercicioCinco() throws IOException, FileNotFoundException;
}
