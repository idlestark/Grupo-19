package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//        Backtracking solucionBacktracking = servicios.ejecutarBacktracking(0, 50);
//        for (Procesador p : solucionBacktracking.getProcesadores()) {
//            System.out.print("Procesador " + p.getId() + ": ");
//            if (p.getTareas().isEmpty()) System.out.print("no tiene tareas");
//            for (Tarea t : p.getTareas()) {
//                System.out.print(t.getId() + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("Costo: " + solucionBacktracking.getEstados());
//        System.out.println("Mejor tiempo: " + solucionBacktracking.getTiempoMaximo());

        Backtracking solucionBacktracking = servicios.ejecutarBacktracking(0, 50);
        for (Procesador p : solucionBacktracking.getAsignacion().keySet()) {
            System.out.print("Procesador " + p.getId() + ": ");
            for (Tarea t : solucionBacktracking.getAsignacion().get(p)) {
                System.out.print(t.getId() + " ");
            }
            System.out.println();
        }
        System.out.println("Costo: " + solucionBacktracking.getEstados());
        System.out.println("Mejor tiempo: " + solucionBacktracking.getTiempoMaximo());

    }
}