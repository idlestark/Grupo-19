package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Greedy extends Algoritmo {
    private int cantidadCandidatosConsiderados;


    public Greedy(HashMap<String, Procesador> procesadores) {
        super(procesadores);
        cantidadCandidatosConsiderados = 0;
    }

    @Override
    public Algoritmo ejecutar(int tiempoMaximoNoRefrigerado, ArrayList<Tarea> tareas) {
        return null;
    }

    public Algoritmo ejecutar (int tiempoMaximoNoRefrigerado, HashMap<String, Procesador> procesadores, ArrayList<Tarea> tareas) {
        ArrayList<Procesador> listaProcesadores = new ArrayList<>(procesadores.values());

        //POR CADATAREA...
        for (Tarea tarea : tareas) {
            //CREA UN PROCESADOR NULO
            Procesador mejorProcesador = null;
            int minTiempo = Integer.MAX_VALUE;

            //POR CADA PROCESADOR...
            for (Procesador procesador : listaProcesadores) {
                //SI SE LE PUEDE ASIGNAR LA TAREA...
                if (puedeAsignar(tarea, procesador, tiempoMaximoNoRefrigerado)) {
                    //SI EL TIEMPO DEL PROCEASDOR ES EL MENOR DE TODOS...
                    if (getTiempoProcesador(procesador) < minTiempo) {
                        //SUMA UNO AL CONTADOR DE CANDIDAD
                        cantidadCandidatosConsiderados++;
                        //LA VARIABLE MINTIEMPO TOMA EL VALOR DEL TIEMPO DEL PROCESADOR
                        minTiempo = getTiempoProcesador(procesador);
                        //Y EL PROCESADOR SE VUELVE EL "MEJOR PROCESADOR"
                        mejorProcesador = procesador;
                    }
                }
            }
            //SI EXISTE UN MEJOR PROCESADOR
            if (mejorProcesador != null) asignarTarea(tarea, mejorProcesador);
            else System.out.println("No existen procesador para asignar la tarea " + tarea.getId());
        }

        int maxTiempoFinal = 0;

        //UNA VEZ ASIGNADAS LAS
        for (Procesador procesador : listaProcesadores) {
            maxTiempoFinal = Math.max(maxTiempoFinal, getTiempoProcesador(procesador));
        }

        this.setTiempoMaximo(maxTiempoFinal);

        return this;
    }


    private boolean ultimaTareaAsignadaEsCritica(Procesador procesador) {
        List<Tarea> tareasAsignadas = this.getAsignacion().get(procesador);

        if (!tareasAsignadas.isEmpty()) {
            Tarea ultimaTareaAsignada = tareasAsignadas.get(tareasAsignadas.size() - 1);
            return ultimaTareaAsignada.isCritica();
        }

        return false;
    }

    @Override
    public String toString() {
        return "Greedy{" + super.toString() +
                "Costo de la solución (cantidad de candidatos considerados): " + cantidadCandidatosConsiderados +
                '}';
    }
}