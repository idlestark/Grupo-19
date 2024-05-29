import utils.CSVReader;
import utils.Tarea;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "utils.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

    private HashMap<String, Tarea> tareas;

    public Servicios(String pathProcesadores, String pathTareas) {
        CSVReader reader = new CSVReader();
        reader.readProcessors(pathProcesadores);
        reader.readTasks(pathTareas, this.tareas);
    }

    public Tarea servicio1 (String ID) {
        return this.tareas.get(ID);
        //Complejidad temporal: 0(1), ya que el acceso al HashMap es directo y no necesita recorrerlo en su totalidad.
    }

    public List<Tarea> servicio2 (boolean esCritica) {
        List<Tarea> lista = new ArrayList<>();
        for (Tarea t: tareas.values()) {
            if (t.isCritica() == esCritica) lista.add(t);
        }
        return lista;
        //Complejidad o(n) porque se recorre el HashMap para encontrar los que cumplen la condición de crítica.
    }

    public List<Tarea> servicio3 (int prioridadInferior, int prioridadSuperior) {
        List<Tarea> lista = new ArrayList<>();
        for (Tarea t: tareas.values()) {
            if (t.getPrioridad() > prioridadInferior && t.getPrioridad() < prioridadSuperior) lista.add(t);
        }
        return lista;
        //Complejidad o(n) porque se recorre el HasMap para encontrar los que cumplen la condición de prioridad.
    }

}