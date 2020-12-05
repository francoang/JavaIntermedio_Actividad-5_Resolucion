/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.PersistenciaEjercicios;
import dominio.Pieza;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Esta clase se implementa el llamado de la persistencia.
 * Funciones:
 * -Manejar las excepciones
 * -Crear objetos necesarios
 * -Recibir los argumentos del Principal
 * -Llamar metodos de persistencia
 *
 * @author Angonoa Franco
 * @since Julio 2020
 * @version 1.0 (Julio), 1.5 (Diciembre)
 */
public class ImplementacionEjercicios implements Implementacion{
    
    private static PersistenciaEjercicios pers;
    
    public ImplementacionEjercicios(){
        pers = new PersistenciaEjercicios();
    }

    @Override
    public void ejercicioUnoA(String id, Pieza pieza) {
        pers.ejercicioUnoA(id, pieza);        
    }

    @Override
    public String ejercicioUnoB() {
        
        String mostrar = "";
        String cadenaHash = pers.ejercicioUnoB();
        
        StringTokenizer stk = new StringTokenizer(cadenaHash, ",");
        
        while(stk.hasMoreTokens()){
            mostrar += stk.nextToken().replaceAll("\\{", " ").replaceAll("\\}", " ").replaceAll("=", "||");
            mostrar += "\n";
        }
        
        return mostrar;
        
    }

    @Override
    public void ejercicioDosA() {
        pers.ejercicioDosA();
    }

    @Override
    public String ejercicioDosB() {
        return pers.ejercicioDosB().toString();
    }
    
    @Override
    public void ejercicioTres() {
        try {
            pers.ejercicioTres();
        } catch (IOException ex) {
            System.err.println("Error E/S: " + ex);
        }
    }
    
    @Override
    public void ejercicioCuatroA(){
        pers.ejercicioCuatroA();
    }
    
    @Override
    public void ejercicioCuatroB(){
        pers.ejercicioCuatroB();
    }

    @Override
    public void ejercicioCinco() {
        try {
            pers.ejercicioCinco();
        } catch (IOException ex) {
            System.err.println("Error E/S: " + ex);
        }
    }
    
    
}
