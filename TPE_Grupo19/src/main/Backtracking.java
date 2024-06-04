package main;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Backtracking extends Algoritmo {

    private int estados;
    protected HashMap<Procesador, List<Tarea>> asignacionTemporal;

    public Backtracking(HashMap<String, Procesador> procesadores) {
        super(procesadores);
        estados = 0;
        asignacionTemporal = new HashMap<>();
    }

    public int getEstados() {
        return estados;
    }

    @Override
    public Backtracking ejecutar(int tiempoLimiteNoRefrigerado, HashMap<String, Procesador> procesadores, ArrayList<Tarea> tareas) {
        ArrayList<Procesador> listaProcesadores = new ArrayList<>(procesadores.values());
        resetMap(this.asignacionTemporal, listaProcesadores);
        backtrack(0, listaProcesadores, tareas, tiempoLimiteNoRefrigerado);

        return this;
    }

    private void backtrack(int tareaActual, ArrayList<Procesador> listaProcesadores, ArrayList<Tarea> tareas, int tiempoLimiteNoRefrigerado) {

        estados++;
        System.out.println("Tarea: " + tareaActual);
        //FRENA CUANDO SE LLEGA LA ÚLTIMA TAREA


        if (tareaActual == tareas.size()) {
            //CUANDO ENCUENTRA UN PROCESADOR QUE PUEDA REALIZAR LA TAREA, SE LA ASIGNA, SE TERMINA EL WHILE Y SE PASA A LA SIGUIENTE TAREA
            asignarTarea(tareas.getLast(), listaProcesadores.getLast());

            System.out.println(asignacionTemporal);

            //CONTADOR DE TIEMPO TOTAL QUE LLEVA REALIZAR TODAS LAS TAREAS
            int mayorTiempo = 0;
            //POR CADA PROCESADOR...
            for (Procesador procesador : listaProcesadores) {
                //BUSCA EL TIEMPO MÁXIMO ENTRE TODOS LOS PROCESADORES
                mayorTiempo = Math.max(mayorTiempo, getTiempoProcesador(procesador));
            }
            System.out.println("mayor tiempo: " + mayorTiempo);
            //SI EL MAYOR TIEMPO ES MENOR AL TIEMPO MÁXIMO DEL ALGORITMO...
            if (mayorTiempo < this.tiempoMaximo) {
                //SE SETEA EL NUEVO TIEMPO "RÉCORD"
                this.setTiempoMaximo(mayorTiempo);
                //Y SE ACTUALIZA LA LISTA DE ASIGNACIONES POR UNA MÁS EFICIENTE
                nuevaAsignacion(listaProcesadores);
            }
        }
        else {
            //ASIGNA LA TAREA ACTUAL AL PRIMER PROCESADOR POSIBLE
            for (Procesador procesador : listaProcesadores) {
                if (puedeAsignar(tareas.get(tareaActual), procesador, tiempoLimiteNoRefrigerado)) {
                    //CUANDO ENCUENTRA UN PROCESADOR QUE PUEDA REALIZAR LA TAREA, SE LA ASIGNA, SE TERMINA EL WHILE Y SE PASA A LA SIGUIENTE TAREA
                    asignarTarea(tareas.get(tareaActual), procesador);
                    backtrack(tareaActual + 1, listaProcesadores, tareas, tiempoLimiteNoRefrigerado);
                    desasignarTarea(tareas.get(tareaActual), procesador);
                }
            }
        }
    }



    //RETORNA SI SI UN PROCESADOR DADO PUEDE REALIZAR UNA TAREA DADA EN UN TIEMPO DADO
    private boolean puedeAsignar(Tarea tarea, Procesador procesador, int tiempoLimiteNoRefrigerado) {
        //SI EL PROCESADOR ES REFRIGERADO Y SU TIEMPO TOTAL (CON LA NUEVA TAREA INCLUIDA) SUPERA EL TIEMPO LÍMITE, RETORNA FALSE (NO SE PUEDE ASIGNAR LA TAREA A ESTE PROCESADOR)
        if (!procesador.isRefrigerado() && getTiempoProcesador(procesador) + tarea.getTiempo() > tiempoLimiteNoRefrigerado)
            return false;
        //VERIFICA QUE NO SE LE ESTÉ ASIGNANDO UNA SEGUNDA TAREA CRÍTICA AL MISMO PROCESADOR (NO SE PUEDEN ASIGNAR DOS TAREAS CRÍTICAS):
        //TOMA LA LISTA DE TAREAS DEL PROCESADOR DADO
        List<Tarea> tareasAsignadas = this.getAsignacion().get(procesador);
        //SI LA LISTA DE TAREAS ASIGNADAS NO ESTÁ VACÍA, TOMA LA ÚLTIMA Y RETORNA SU ESTADO (SI ES CRÍTICA O NO)
        if (!tareasAsignadas.isEmpty())
            return !tareasAsignadas.get(tareasAsignadas.size() - 1).isCritica();
        //SI NO CUMPLE LA CONDICIÓN ANTERIOR (LA LISTA ESTÁ VACÍA), LA TAREA PUEDE SER ASIGNADA SIN PROBLEMAS
        return true;
    }

    //RETORNA EL TIEMPO TOTAL QUE NECESITA PARA COMPLETAR TODAS LAS TAREAS QUE YA TIENE ASGINADAS
    private int getTiempoProcesador(Procesador procesador) {
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

    //ASIGNA UNA TAREA A UN PROCESADOR
    private void asignarTarea(Tarea tarea, Procesador procesador) {
        //AGREGA LA TAREA DADA A LA LISTA DE TAREAS ASIGNADAS DEL PROCESADOR DADO
        List<Tarea> tareasAsignadas = this.asignacionTemporal.get(procesador);
        tareasAsignadas.add(tarea);
        this.asignacionTemporal.put(procesador, tareasAsignadas);
    }

    //DESASIGNA UNA TAREA A UN PROCESADOR
    private void desasignarTarea(Tarea tarea, Procesador procesador) {
        //QUITA LA TAREA DADA DE LA LISTA DE TAREAS ASIGNADAS DEL PROCESADOR DADO
        List<Tarea> tareasAsignadas = this.asignacion.get(procesador);
        tareasAsignadas.remove(tarea);
    }

    //ACTUALIZA EL MAPA DE ASIGNACIONES CON UNO MÁS EFICIENTE QUE VIENE POR PARÁMETRO
    private void nuevaAsignacion(ArrayList<Procesador> listaProcesadores) {
        //EL MAPA DE ASIGNACIONES ORIGINAL TOMA LOS VALORES DEL TEMPORAL
        this.asignacion = this.asignacionTemporal;
        //Y SE LIMPIA EL TEMPORAL
        resetMap(this.asignacionTemporal, listaProcesadores);
    }

    //VACÍA LAS LISTAS DE TAREAS DE CADA PROCESADOR
    private void resetMap (HashMap<Procesador, List<Tarea>> mapa, ArrayList<Procesador> listaProcesadores) {
        //POR CADA PROCESADOR...
        for (Procesador procesador : listaProcesadores) {
            //SE LE ASIGNA UNA LISTA VACÍA EN EL MAPA
            mapa.put(procesador, new ArrayList<>());
        }
    }

    @Override
    public String toString() {
        return "Backtracking{" + super.toString() +
                "Costo de la solución (cantidad de estados generados): " + estados +
                '}';
    }
}

