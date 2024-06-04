package main;
import java.util.ArrayList;
import java.util.HashMap;

public class Greedy extends Algoritmo {
    private int cantidadCandidatosConsiderados;

    public Greedy(int tiempoMaximo, HashMap<String, Procesador> procesadores) {
        super(procesadores);
        cantidadCandidatosConsiderados = 0;
    }

    @Override
    public Greedy ejecutar(int tiempoMaximoNoRefrigerado, HashMap<String, Procesador> procesadores,
                              ArrayList<Tarea> tareas) {

        return this;
    }

    @Override
    public String toString() {
        return "Greedy{" + super.toString() +
                "Costo de la solución (cantidad de candidatos considerados): " + cantidadCandidatosConsiderados +
                '}';
    }
}