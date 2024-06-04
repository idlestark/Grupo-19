package main;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Servicios servicios = new Servicios("D:\\Facultad\\Prog3\\TPE_Grupo19\\src\\datasets\\Procesadores.csv", "D:\\Facultad\\Prog3\\TPE_Grupo19\\src\\datasets\\Tareas.csv");

        System.out.println();

        //IMPRIMIR ÁRBOLES (ABB POR ID Y ABB POR PRIORIDAD)
        servicios.printTrees();

        System.out.println();

        //IMPRIMIR SERVICIO 1 (OBTENER TAREA POR ID)
        System.out.println("Tarea seleccionada: " + servicios.servicio1("T1"));

        System.out.println();

        //IMPRIMIR SERVICIO 2 (OBTENER TAREAS CRÍTICAS/NO CRÍTICAS)
        boolean crit = true; //CAMBIAR VALOR PARA OBTENER EL RESULTADO DESEADO
        ArrayList<Tarea> listaTareas = (ArrayList<Tarea>) servicios.servicio2(crit);
        if (crit) System.out.print("Tareas críticas: ");
        else System.out.print("Tareas no criticas: ");
        for (Tarea task : listaTareas) {
            System.out.print(task.getId() + " ");
        }

        System.out.println();
        System.out.println();

        //IMPRIMIR SERVICIO 3 (OBTENER TODAS LAS TAREAS ENTRE DOS NIVELES DE PRIORIDAD INDICADOS)
        for (Tarea task : servicios.servicio3(50, 100)) {
            System.out.print(task.getId() + " ");
        }

        System.out.println();
        System.out.println();

        //SOLUCIÓN CON BACKTRACKING (ASIGNAR TAREAS A CADA PROCESADOR)
        Backtracking solucionBacktracking = servicios.ejecutarBacktracking(0, 50);
        ArrayList<Procesador> listaAsignaciones = new ArrayList<>(solucionBacktracking.getAsignacion().keySet());
        for (Procesador p : listaAsignaciones) {
            System.out.print("Procesador " + p.getId() + ": ");
            for (Tarea t : solucionBacktracking.getAsignacion().get(p)) {
                System.out.print(t.getId() + " ");
            }
            System.out.println();
        }
        System.out.println("Tiempo máximo: " + solucionBacktracking.getTiempoMaximo());
        System.out.println("Costo: " + solucionBacktracking.getEstados());


        // Greedy
        Algoritmo solucionG = servicios.ejecutarGreedy(0, 50);

        // Imprimir la Solución Greedy
        //System.out.println(solucionG);
    }
}