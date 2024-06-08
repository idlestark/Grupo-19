package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtracking extends Algoritmo {

    private int estados;

    public Backtracking(HashMap<String, Procesador> procesadores) {
        super(procesadores);
        estados = 0;
    }

    public int getEstados() {
        return estados;
    }

    public ArrayList<Procesador> getProcesadores () {
        return this.listaProcesadores;
    }

    @Override
    public Backtracking ejecutar(int tiempoLimiteNoRefrigerado, ArrayList<Tarea> tareas) {
        //MALCOLM KEEN EYED NAVIGATOR, KEDISS EMBERCLAW FAMILIAR
        //BASALT MONOLITH
        backtrack(0, tareas, tiempoLimiteNoRefrigerado);
        //METODO TOSTRING
        return this;
    }



    private void backtrack(int tareaActual, ArrayList<Tarea> tareas, int tiempoLimiteNoRefrigerado) {
        this.estados++;
        //FRENA CUANDO SE LLEGA LA ÚLTIMA TAREA
        //System.out.println(tareaActual);
        if (tareaActual == tareas.size()) {
            //CONTADOR DE TIEMPO TOTAL QUE LLEVA REALIZAR TODAS LAS TAREAS
            int mayorTiempo = 0;
            //POR CADA PROCESADOR...
            for (Procesador procesador : this.listaProcesadores) {
                //BUSCA EL TIEMPO MÁXIMO ENTRE TODOS LOS PROCESADORES
                mayorTiempo = Math.max(mayorTiempo, getTiempoProcesador(procesador));
            }
            //SI EL MAYOR TIEMPO ES MENOR AL TIEMPO MÁXIMO DEL ALGORITMO...
            if (this.tiempoMaximo == 0 || mayorTiempo < this.tiempoMaximo) {
                //SE SETEA EL NUEVO TIEMPO "RÉCORD"
                this.setTiempoMaximo(mayorTiempo);
                //Y SE ACTUALIZA LA LISTA DE ASIGNACIONES POR UNA MÁS EFICIENTE
                nuevaAsignacion();
            }
        }
        else {
            //ASIGNA LA TAREA ACTUAL AL PRIMER PROCESADOR POSIBLE
            for (Procesador procesador : this.listaProcesadores) {
                if (puedeAsignar(tareas.get(tareaActual), procesador, tiempoLimiteNoRefrigerado)) {
                    //CUANDO ENCUENTRA UN PROCESADOR QUE PUEDA REALIZAR LA TAREA, SE LA ASIGNA, SE TERMINA EL WHILE Y SE PASA A LA SIGUIENTE TAREA
                    asignarTarea(tareas.get(tareaActual), procesador);
                    //System.out.println("id tarea insertada: " + tareas.get(tareaActual).getId());
                    backtrack(tareaActual + 1, tareas, tiempoLimiteNoRefrigerado);
                    desasignarTarea(tareas.get(tareaActual), procesador);
                }
            }
        }
    }

    //DESASIGNA UNA TAREA A UN PROCESADOR
    private void desasignarTarea(Tarea tarea, Procesador procesador) {
        //QUITA LA TAREA DADA DE LA LISTA DE TAREAS ASIGNADAS DEL PROCESADOR DADO
        //System.out.println("estados: " + this.estados);
        //System.out.println("parado en: " + procesador.getId());
        //System.out.println("tarea a borrar: " + tarea.getId());
        //System.out.println("antes de borrar: " + asignacionComparable.get(procesador));
        this.asignacionComparable.get(procesador).remove(tarea);
        //System.out.println("después de borrar: " + asignacionComparable.get(procesador));
    }

    //ACTUALIZA EL MAPA DE ASIGNACIONES CON UNO MÁS EFICIENTE QUE VIENE POR PARÁMETRO
    private void nuevaAsignacion() {
        //EL MAPA DE ASIGNACIONES ORIGINAL TOMA LOS VALORES DEL TEMPORAL
        //System.out.println("asignación nueva: " + this.asignacionComparable);
        //asignacionTemporal = asignacionComparable;
        asignacionTemporal.putAll(asignacionComparable);
        resetMap(this.asignacionComparable);
//        for (Procesador procesador : listaProcesadores) {
//            procesador.vaciarTareas();
//            for (Tarea tarea : this.asignacionTemporal.get(procesador)) {
//                procesador.asignarTarea(tarea);
//            }
//            for (Procesador p : listaProcesadores) {
//                procesador.getTareas();
//            }
//        }
    }

    @Override
    public String toString() {
        return "Backtracking{" + super.toString() +
                "Costo de la solución (cantidad de estados generados): " + estados +
                '}';
    }
}

