package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Algoritmo {
    protected HashMap<Procesador, List<Tarea>> asignacionTemporal;
    protected HashMap<Procesador, List<Tarea>> asignacionComparable;
    protected ArrayList<Procesador> listaProcesadores;
    protected int tiempoMaximo;

    public Algoritmo(HashMap<String, Procesador> procesadores) {
        this.asignacionTemporal = new HashMap<>();
        this.asignacionComparable = new HashMap<>();
        listaProcesadores = new ArrayList<>();
        listaProcesadores.addAll(procesadores.values());
        this.tiempoMaximo = 0;


        resetMap(this.asignacionTemporal);
        resetMap(this.asignacionComparable);

        // Inicializar asignación para cada procesador
        for (Procesador procesador : procesadores.values()) {
            this.getAsignacion().put(procesador, new ArrayList<>());
        }
    }

    public HashMap<Procesador, List<Tarea>> getAsignacion() {
        HashMap<Procesador, List<Tarea>> copia = new HashMap<>();
        copia = this.asignacionTemporal;
        return copia;
    }

    //RETORNA SI SI UN PROCESADOR DADO PUEDE REALIZAR UNA TAREA DADA EN UN TIEMPO DADO
    protected boolean puedeAsignar(Tarea tarea, Procesador procesador, int tiempoLimiteNoRefrigerado) {
        //SI EL PROCESADOR ES REFRIGERADO Y SU TIEMPO TOTAL (CON LA NUEVA TAREA INCLUIDA) SUPERA EL TIEMPO LÍMITE, RETORNA FALSE (NO SE PUEDE ASIGNAR LA TAREA A ESTE PROCESADOR)
        if (!procesador.isRefrigerado() && getTiempoProcesador(procesador) + tarea.getTiempo() > tiempoLimiteNoRefrigerado)
            return false;
        //VERIFICA QUE NO SE LE ESTÉ ASIGNANDO UNA SEGUNDA TAREA CRÍTICA AL MISMO PROCESADOR (NO SE PUEDEN ASIGNAR DOS TAREAS CRÍTICAS):
        //TOMA LA LISTA DE TAREAS DEL PROCESADOR DADO
        List<Tarea> tareasAsignadas = this.getAsignacion().get(procesador);
        //SI LA LISTA DE TAREAS ASIGNADAS NO ESTÁ VACÍA, TOMA LA ÚLTIMA Y RETORNA SU ESTADO (SI ES CRÍTICA O NO)
        if (!tareasAsignadas.isEmpty()) {
            int cantidadCriticas = 0;
            for (Tarea t : procesador.getTareas()) {
                if (t.isCritica()) cantidadCriticas++;
            }
            return cantidadCriticas < 2;
        }
        //SI NO CUMPLE LA CONDICIÓN ANTERIOR (LA LISTA ESTÁ VACÍA), LA TAREA PUEDE SER ASIGNADA SIN PROBLEMAS
        return true;
    }

    //RETORNA EL TIEMPO TOTAL QUE NECESITA PARA COMPLETAR TODAS LAS TAREAS QUE YA TIENE ASGINADAS
    protected int getTiempoProcesador(Procesador procesador) {
        //CONTADOR
        int total = 0;

        //POR CADA TAREA QUE YA TIENE ASIGNADA
        for (Tarea tarea : this.getAsignacion().get(procesador)) {
            //LE AGREGA EL TIEMPO DE ESA TAREA AL CONTADOR
            total += tarea.getTiempo();
        }
        //RETORNA EL TIEMPO QUE LE LLEVA COMPLETAR TODAS LAS TAREAS
        return total;
    }

    protected void asignarTarea(Tarea tarea, Procesador procesador) {
        //AGREGA LA TAREA DADA A LA LISTA DE TAREAS ASIGNADAS DEL PROCESADOR DADO
        List<Tarea> temp = new ArrayList<>();
        temp.addAll(asignacionComparable.get(procesador));
        temp.add(tarea);
        //System.out.println("procesador sape : " + procesador.getId() + " Tarea " + tarea.getId());
        this.asignacionComparable.put(procesador, temp);
        //System.out.println("Asignacion chitiada: "+asignacionComparable);
//        List<Tarea> tareasAsignadas = this.asignacionTemporal.get(procesador);
//        tareasAsignadas.add(tarea);
//        this.asignacionTemporal.put(procesador, tareasAsignadas);
    }

    //VACÍA LAS LISTAS DE TAREAS DE CADA PROCESADOR
    protected void resetMap (HashMap<Procesador, List<Tarea>> mapa) {
        //POR CADA PROCESADOR...
        for (Procesador procesador : this.listaProcesadores) {
            //SE LE ASIGNA UNA LISTA VACÍA EN EL MAPA
            mapa.put(procesador, new ArrayList<>());
        }
    }

    public int getTiempoMaximo() {
        return tiempoMaximo;
    }

    public void setTiempoMaximo(int tiempoMaximo) {
        this.tiempoMaximo = tiempoMaximo;
    }

    public abstract Algoritmo ejecutar(int tiempoMaximoNoRefrigerado, ArrayList<Tarea> tareas);

    @Override
    public String toString() {
        return "Cada procesador con las tareas asignadas: " + asignacionTemporal +
                ", Tiempo máximo de ejecución: " + tiempoMaximo;
    }
}