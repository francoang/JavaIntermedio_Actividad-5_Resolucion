package datos;

import dominio.Pieza;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.*;

/**
 * Contiene las operaciones necesarias con respecto a la persistencia.
 * En este caso, la persistencia hace referencia a guardar datos en:
 * --HashMap
 * --Serializacion
 * --Archivos txt
 * --Base de datos
 *
 * @author Angonoa Franco
 * @since Julio 2020
 * @version 1.0 Julio, 1.5 Diciembre
 */
public class PersistenciaEjercicios implements Persistencia {

    private static Map<String, Pieza> hashPiezas;
    private static final String URL = "jdbc:sqlite:./basededatos/respuestosBD.db";

    public PersistenciaEjercicios() {
        hashPiezas = new HashMap<>();
    }

    @Override
    public void ejercicioUnoA(String id, Pieza pieza) {
        hashPiezas.put(id, pieza);
    }

    @Override
    public String ejercicioUnoB() {
        return hashPiezas.toString();

    }

    @Override
    public void ejercicioDosA() {
        Path rutaPers = Paths.get(".\\Persistencia");

        if (Files.notExists(rutaPers)) {
            crearDirectorio("Persistencia");
        }

        try (ObjectOutputStream serializar
                = new ObjectOutputStream(new FileOutputStream(".\\Persistencia\\MapSerializado.dat"))) {
            serializar.writeObject(hashPiezas);

        } catch (IOException ex) {
            System.err.println("IOException Serializar:" + ex.getMessage());
        }
    }

    @Override
    public Map<String, Pieza> ejercicioDosB() {

        try (ObjectInputStream deserializar
                = new ObjectInputStream(new FileInputStream(".\\Persistencia\\MapSerializado.dat"))) {
            Map<String, Pieza> mapDeserializado = (Map<String, Pieza>) deserializar.readObject();
            return mapDeserializado;

        } catch (IOException ex) {
            System.out.println("IOException Deserializar: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException Deserializar: " + ex.getMessage());
        }

        return null;
    }

    public boolean crearDirectorio(String nombreDirectorio) {
        Path rutaPers = Paths.get(".\\" + nombreDirectorio);
        try {
            Files.createDirectory(rutaPers);
            return true;
        } catch (IOException ex) {
            System.err.println("No se pudo crear el directorio: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public void ejercicioTres() throws IOException, FileNotFoundException {
        try (
                BufferedWriter outputStream = new BufferedWriter(new FileWriter(".\\Persistencia\\Listado de Piezas.txt"))) {

            Set<String> ids = hashPiezas.keySet();

            for (String id : ids) {
                outputStream.write(id);
                outputStream.newLine();
            }

        }
    }

    @Override
    public void ejercicioCuatroA() {
        //RECORDAR ANTES DE EJECUTAR, DE AGREGAR EL DRIVER SQLITE EN LIBRARIES

        //CODIGO EN BD:
        /*
            CREATE TABLE Piezas(id VARCHAR(50),
            nombre VARCHAR(50),
            tipo VARCHAR(50));
         */
        try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pStmt = conn.prepareStatement("INSERT INTO Piezas VALUES(?,?,?);");) 
        {
            int result;

            Set<String> ids = hashPiezas.keySet();
            for (String id : ids) {
                Pieza unaPieza = hashPiezas.get(id);
                pStmt.setString(1, id);
                pStmt.setString(2, unaPieza.getNombre());
                pStmt.setString(3, unaPieza.getTipoPieza().toString());
                result = pStmt.executeUpdate();
            }

            //SE PODRIA HACER CON UNA TRANSACTION
        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        }

    }

    @Override
    public void ejercicioCuatroB() {
        Connection conn = null;
        Statement stmt = null;

        try{
            //CODIGO EN BD:
            /*
            CREATE TABLE Reserva(nombre VARCHAR(50),
            idProducto integer);
             */

            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false); //Desactivamos las consultas por cada sentencia
            stmt = conn.createStatement();
            int rs = stmt.executeUpdate("INSERT INTO Reserva VALUES(\"Carlos Jimenez\",\"PF002P\");");
            rs = stmt.executeUpdate("INSERT INTO Reserva VALUES(\"Carlos Jimenez\",\"PF003C\");");
            rs = stmt.executeUpdate("INSERT INTO Reserva VALUES(\"Carlos Jimenez\",\"PF004C\");");

            conn.commit(); //Si todo sale bien, devolver una confirmación.

        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());

            try {
                if (conn != null) {
                    System.err.printf("Falla en la operación. Rollback %n");
                    conn.rollback(); //Si algo salio mal, descartar cambios con rollback.
                }
            } catch (SQLException ex1) {
                System.out.println("Error en el rollback: " + ex1);
            }
        }
        finally {
            try {
                //Orden de cierre: Statement, Connect.
                if (conn != null) {
                    conn.setAutoCommit(true); //VOLVEMOS A ACTIVAR EL AUTOCOMMIT
                    stmt.close();
                    conn.close();                    
                }                
            } catch (SQLException ex) {
                System.out.println("Error en autocommit: " + ex);
            }
        }

    }

    @Override
    public void ejercicioCinco() throws IOException, FileNotFoundException {
        List<String> palabrasGuardar = aplicarFiltro();

        try (
                BufferedWriter outputStream = new BufferedWriter(new FileWriter(".\\Persistencia\\Filtro.txt"))) {

            for (String elementos : palabrasGuardar) {
                outputStream.write(elementos);
                outputStream.newLine();
            }
        }
    }

    /**
     * Método que puede ayudar a resolver el ejercicio cinco.
     *
     * @return una lista con los nombres de los articulos que contengan a.
     */
    public List<String> aplicarFiltro() {
        Pieza[] piezas = hashPiezas.values().toArray(new Pieza[0]);
        List<String> nombresConFiltros = new ArrayList<>();
        Pattern pat = Pattern.compile(".*[aA].*");
        Matcher match;

        for (Pieza elementos : piezas) {
            String nombrePieza = elementos.getNombre();
            match = pat.matcher(nombrePieza);

            if (match.find()) {
                nombresConFiltros.add(nombrePieza);
            }
        }

        return nombresConFiltros;
    }

}
