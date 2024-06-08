package main;
import utils.CSVReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "utils.main.Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

    //SE DECLARAN TODAS LAS ESTRUCUTRAS NECESARIAS PARA LOS SERVICIOS
    private BSTid tareasPorId;
    private BSTpriority tareasPorPrioridad;
    private List<Tarea> tareasCriticas;
    private List<Tarea> tareasNoCriticas;
    private HashMap<String, Procesador> procesadores;
    private Map<String, Tarea> mapaTareas;

    public Servicios(String pathProcesadores, String pathTareas) {
        this.tareasPorPrioridad = new BSTpriority();
        this.tareasPorId = new BSTid();
        this.tareasCriticas = new ArrayList<>();
        this.tareasNoCriticas = new ArrayList<>();
        this.procesadores = new HashMap<>();
        this.mapaTareas = new HashMap<>();
        CSVReader reader = new CSVReader();

        //SE LLENAN LAS ESTRUCTURAS CON LOS PROCESADORES Y TAREAS
        reader.readProcessors(pathProcesadores, this.procesadores);
        reader.readTasks(pathTareas, this.tareasPorId);
        reader.readTasks(pathTareas, this.tareasPorPrioridad);

        //SE GUARDAN TODAS LAS TAREAS EN UN HASHMAP
        for (Tarea t : tareasPorId.getAllTasks()) {
            mapaTareas.put(t.getId(), t);
        }

        //SE GUARDAN TODAS LAS TAREAS CRÍTICAS/NO CRÍTICAS EN SUS RESPECTIVAS LISTAS
        for (Tarea tarea : tareasPorId.getAllTasks()) {
            if (tarea.isCritica()) tareasCriticas.add(tarea);
            else tareasNoCriticas.add(tarea);
        }
    }

    public Tarea servicio1 (String ID) {
        return this.tareasPorId.get(ID);
        //Complejidad temporal: O(log2 n)
    }

    public List<Tarea> servicio2 (boolean esCritica) {
        if (esCritica) return this.tareasCriticas;
        else return this.tareasNoCriticas;
        //Complejidad temporal: O(x), donde x es la cantidad de tareas críticas/no críticas
    }

    public List<Tarea> servicio3 (int prioridadInferior, int prioridadSuperior) {
        System.out.print("Tareas con prioridad entre " + prioridadInferior + " y " + prioridadSuperior + ": ");
        return tareasPorPrioridad.getTareasRango(prioridadInferior, prioridadSuperior);
    }

    public void printTrees() {
        System.out.print("Árbol por IDs ");
        tareasPorId.printPreOrder();
        System.out.print("Árbol por prioridad ");
        tareasPorPrioridad.printPreOrder();
    }

    public Backtracking ejecutarBacktracking(int tiempoMaximo, int tiempoMaximoNoRefrigerado) {
        Backtracking backtracking = new Backtracking(procesadores);
        ArrayList<Tarea> listaTareas = new ArrayList<>(mapaTareas.values());
        return backtracking.ejecutar(tiempoMaximoNoRefrigerado, listaTareas);
    }

    /*public void ejecutarBack(int tiempoMaximo){
        Backtracking backtrack = new Backtracking();
        ArrayList<Procesador> listaProcesadores = new ArrayList<>(procesadores.values());
        ArrayList<Tarea> listaTareas = new ArrayList<>(mapaTareas.values());
        backtrack.ejecutar(tiempoMaximo, listaProcesadores, listaTareas);
    } */

}