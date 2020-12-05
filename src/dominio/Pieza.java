package dominio;

import java.io.Serializable;

/**
 * Clase del dominio de problemas. Se trata de las piezas que posee la fábrica
 *
 * @author Angonoa Franco
 * @since Julio 2020
 * @version 1.0
 */
public class Pieza implements Serializable{
    private String nombre;
    private Tipos tipoPieza;

    public Pieza() {
    }

    public Pieza(String nombre, Tipos tipoPieza) {
        this.nombre = nombre;
        this.tipoPieza = tipoPieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipos getTipoPieza() {
        return tipoPieza;
    }

    public void setTipoPieza(Tipos tipoPieza) {
        this.tipoPieza = tipoPieza;
    }

    @Override
    public String toString() {
        return "Pieza - nombre: " + nombre + ". Tipo: " + tipoPieza;
    }
    
    
}
