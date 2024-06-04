package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//CAMBIAR NOMBRE A "Backtracking"
public class BacktrackingRespaldo extends Algoritmo {

    private int estados;
    //CAMBIAR NOMBRE A "Backtracking"
    public BacktrackingRespaldo(int tiempoLimite, HashMap<String, Procesador> procesadores) {
        super(procesadores);
        estados = 0;
    }

    public int getEstados() {
        return estados;
    }

    @Override
    public Backtracking ejecutar(int tiempoLimiteNoRefrigerado, HashMap<String, Procesador> procesadores, ArrayList<Tarea> tareas) {
        ArrayList<Procesador> listaProcesadores = new ArrayList<>(procesadores.values());
        backtrack(0, listaProcesadores, tareas, tiempoLimiteNoRefrigerado);

        return null; //reurn this;
    }

    private void backtrack(int tareaActual, ArrayList<Procesador> listaProcesadores, ArrayList<Tarea> tareas, int tiempoLimiteNoRefrigerado) {

        estados++;

        //CONDICIÓN DE CORTE: CUANDO SE LLEGUE A LA ÚLTIMA TAREA DE LA LISTA
        if (tareaActual == tareas.size()) {
            //CONTADOR DE TIEMPO TOTAL QUE LLEVA REALIZAR TODAS LAS TAREAS
            int mayorTiempo = 0;
            //POR CADA PROCESADOR...
            for (Procesador procesador : listaProcesadores) {
                //BUSCA EL TIEMPO MÁXIMO ENTRE TODOS LOS PROCESADORES
                mayorTiempo = Math.max(mayorTiempo, getTiempoProcesador(procesador));
            }
            //SI EL MAYOR TIEMPO ES MENOR AL TIEMPO MÁXIMO DEL ALGORITMO...
            if (mayorTiempo < this.getTiempoMaximo()) {
                //SE SETEA EL NUEVO TIEMPO "RÉCORD"
                this.setTiempoMaximo(mayorTiempo);
                //Y SE ACTUALIZA LA LISTA DE ASIGNACIONES POR UNA MÁS EFICIENTE
                nuevaAsignacion(listaProcesadores);
            }

            return;
        }

        //TOMA LA TAREA CORRESPONDIENTE EN LA LISTA DE TAREAS
        Tarea tarea = tareas.get(tareaActual);
        //POR CADA PROCESADOR DE LA LISTA DE PROCESADORES...
        for (Procesador procesador : listaProcesadores) {
            //PRIMERO COMPRUEBA SI LA TAREA PUEDE SER ASIGNADA AL PROCESADOR ITERADO (LOS PROCESADORES NO REFRIGERADOS NO PUEDEN DEDICAR MÁS DE X TIEMPO DADO A UNA TAREA)
            if (puedeAsignar(tarea, procesador, tiempoLimiteNoRefrigerado)) {
                //SI PUEDE, SE LE ASIGNA LA TAREA
                asignarTarea(tarea, procesador);
                //HACE UNA LLAMADA RECURSIVA CON LA SIGUIENTE TAREA
                backtrack(tareaActual + 1, listaProcesadores, tareas, tiempoLimiteNoRefrigerado);
                //desasignarTarea(tarea, procesador);
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
        if (!tareasAsignadas.isEmpty()) return !tareasAsignadas.get(tareasAsignadas.size() - 1).isCritica();
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
        List<Tarea> tareasAsignadas = this.asignacion.get(procesador);
        tareasAsignadas.add(tarea);
    }

    //DESASIGNA UNA TAREA A UN PROCESADOR
    private void desasignarTarea(Tarea tarea, Procesador procesador) {
        //QUITA LA TAREAD DADAD DE LA LISTA DE TAREAS ASIGNADAS DEL PROCESADOR DADO
        List<Tarea> tareasAsignadas = this.asignacion.get(procesador);
        tareasAsignadas.remove(tarea);
    }
    //ACTUALIZA EL MAPA DE ASIGNACIONES CON UNO MÁS EFICIENTE QUE VIENE POR PARÁMETRO
    private void nuevaAsignacion(ArrayList<Procesador> listaProcesadores) {
        //SE LIMPIA EL MAPA DE ASIGNACIONES
        this.asignacion.clear();
        System.out.println("penes negros: " + this.asignacion);
        //Y POR CADA PROCESADOR DE LA LISTA...
        for (Procesador procesador : listaProcesadores) {
            //SE LE AGREGA EL PROCESADOR ITERADO AL MAPA JUNTO CON UNA LISTA DE
            this.asignacion.put(procesador, new ArrayList<>(this.asignacion.get(procesador)));
        }
    }

    @Override
    public String toString() {
        return "Backtracking{" + super.toString() +
                "Costo de la solución (cantidad de estados generados): " + estados +
                '}';
    }
}