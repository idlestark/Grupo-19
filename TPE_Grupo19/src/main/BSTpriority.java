package main;

import java.util.ArrayList;

public class BSTpriority extends BinarySearchTree {

    public BSTpriority() {
        super();
    }

    @Override
    public Tarea get(String taskId) {
        //SI EL ÁRBOL NO TIENE RAÍZ (ESTÁ VACÍO), RETORNA FALSE
        if (this.root == null) return null;
            //SI TIENE RAÍZ, LLAMA AL MÉTODO PRIVADO CON EL NODO RAÍZ Y EL VALOR A BUSCAR. LUEGO RETORNA LO QUE RETORNE ESE MÉTODO
        else return get(this.root, taskId);
    }

    //MÉTODO RECURSIVO PRIVADO DE HAS ELEM
    private Tarea get(TreeNode currentNode, String taskId) {
        //SI EL VALOR DEL NODO ACTUAL (EL QUE VIENE POR PARÁMETRO) ES MAYOR AL VALOR BUSCADO...
        if (currentNode.getIdValue().compareTo(taskId) > 0) {
            //Y SI EL NODO ACTUAL NO TIENE HIJO IZQUIERDO, RETORNO FALSE (EL VALOR BUSCADO SOLO PUEDE ESTAR A LA IZQUIERDA DEL NODO ACTUAL)
            if (currentNode.getLeft() == null) return null;
                //SI EL NODO ACTUAL TIENE UN HIJO IZQUIERDO, HACE UNA LLAMADA RECURSIVA CON ESE NODO HIJO COMO NUEVO EL NODO ACTUAL
            else return get(currentNode.getLeft(), taskId);
            //SI EL VALOR DEL NODO ACTUAL ES MENOR AL VALOR BUSCADO...
        } else if (currentNode.getIdValue().compareTo(taskId) < 0) {
            //Y SI EL NODO ACTUAL NO TIENE UN HIJO DERECHO, RETORNO FALSE (EL VALOR BUSCADO SOLO PUEDE ESTAR A LA DERECHA DEL NODO ACTUAL)
            if (currentNode.getRight() == null) return null;
                //SI EL NODO ACTUAL TIENE UN HIJO DERECHO, HACE UNA LLAMADA RECURSIVA CON ESE NODO HIJO COMO EL NUEVO NODO ACTUAL
            else return get(currentNode.getRight(), taskId);
        }
        //SI EL VALOR DEL NODO ACTUAL NO ES NI MAYOR NI MENOR AL VALOR BUSCADO, SINGIFICA QUE ES IGUAL (SE ENCUENTRA EN EL ÁRBOL)
        return currentNode.getValue();
    }

    @Override
    public void insert(Tarea task) {
        //SI EL ÁRBOL NO TIENE RAÍZ (ESTÁ VACÍO), EL NODO SE AGREGA COMO RAÍZ
        if (this.root == null) this.root = new TreeNode(task);
            //SI YA TIENE RAÍZ, SE LLAMA AL MÉTODO DE AGREGAR CON LA RAÍZ Y EL NUEVO VALOR
        else insert(this.root, task);
    }

    //MÉTODO RECRUSIVO PRIVADO DE INSERT
    private void insert(TreeNode currentNode, Tarea task) {
        //SI EL VALOR DEL NODO ACTUAL (EL QUE VIENE POR PARÁMETRO) ES MAYOR AL VALOR QUE SE QUIERE INSERTAR AL ÁRBOL...
        if (currentNode.getValue().getPrioridad() > task.getPrioridad()) {
            //Y SI EL NODO ACTUAL NO TIENE HIJO IZQUIERDO...
            if (currentNode.getLeft() == null) {
                //SE INSERTA UN NUEVO NODO CON (EL VALOR INGRESADO) COMO HIJO IZQUIERDO DEL NODO ACTUAL
                TreeNode temp = new TreeNode(task);
                currentNode.setLeft(temp);
                //SI EL NODO ACTUAL TIENE UN HIJO IZQUIERDO, HACE UNA LLAMADA RECURSIVA CON ESE NODO HIJO COMO NUEVO EL NODO ACTUAL
            } else insert(currentNode.getLeft(), task);
            //SI EL VALOR DEL NODO ACTUAL ES MENOR AL VALOR QUE SE QUIERE INSERTAR AL ÁRBOL...
        } else if (currentNode.getValue().getPrioridad() < task.getPrioridad()) {
            //Y SI EL NODO ACTUAL NO TIENE UN HIJO DERECHO...
            if (currentNode.getRight() == null) {
                //SE INSERTA UN NUEVO NODO (CON EL VALOR INGRESADO) COMO HIJO DERECHO DEL NODO ACTUAL
                TreeNode temp = new TreeNode(task);
                currentNode.setRight(temp);
                //SI EL NODO ACTUAL TIENE UN HIJO DERECHO, HACE UNA LLAMADA RECURSIVA CON ESE NODO HIJO COMO EL NUEVO NODO ACTUAL
            } else insert(currentNode.getRight(), task);
        }
        //SI EL VALOR DEL NODO ACTUAL ES IGUAL AL VALOR QUE SE QUIERE INSERTAR AL ÁRBOL, NO SE INSERTA
    }

    @Override
    public void printPosOrder() {
        //SI EL ÁRBOL NO TIENE RAÍZ, IMPRIME QUE ESTÁ VACÍO
        if (this.getRoot() == null) System.out.println("This is an empty tree");
            //SI TIENE RAÍZ, LLAMA AL MÉTODO PRIVADO
        else {
            System.out.print("Recorrido post-orden: ");
            printNodes(this.root, "POS");
            System.out.println();
        }
    }

    public void printPreOrder() {
        //SI EL ÁRBOL NO TIENE RAÍZ, IMPRIME QUE ESTÁ VACÍO
        if (this.getRoot() == null) System.out.println("This is an empty tree");
            //SI TIENE RAÍZ, LLAMA AL MÉTODO PRIVADO
        else {
            System.out.print("Recorrido pre-orden: ");
            printNodes(this.root, "PRE");
            System.out.println();
        }

        //COMPLEJIDAD O(n)
    }

    public void printInOrder() {
        //SI EL ÁRBOL NO TIENE RAÍZ, IMPRIME QUE ESTÁ VACÍO
        if (this.getRoot() == null) System.out.println("El árbol está vacío");
            //SI TIENE RAÍZ, LLAMA AL MÉTODO PRIVADO
        else {
            System.out.print("Recorrido en-orden: ");
            printNodes(this.root, "IN");
            System.out.println();
        }
    }

    //MÉTODO PRIVADO PARA IMPRIMIR LOS NODOS EN CUALQUIER ORDEN
    private void printNodes(TreeNode currentNode, String type) {
        //SI EL NODO ACTUAL NO ES NULO...
        if (currentNode != null) {
            //SI EL TIPO ES "PRE" (PRE-ORDER), IMPRIME EL VALOR AL INICIO
            if (type == "PRE") System.out.print(currentNode.getValue().getPrioridad() + " ");
            //HACE LLAMADA RECURSIVA CON EL HIJO IZQUIERDO DEL NODO ACTUAL
            printNodes(currentNode.getLeft(), type);
            //SI EL TIPO ES "IN" (IN-ORDER), IMPRIME EL VALOR ENTRE EL HIJO IZQUIERDO Y EL DERECHO
            if (type == "IN") System.out.print(currentNode.getValue().getPrioridad() + " ");
            //HACE LLAMADA RECURSIVA CON EL HIJO DERECHO DEL NODO ACTUAL
            printNodes(currentNode.getRight(), type);
            //SI EL TIPO ES "POS" (POS-ORDER), IMPRIME EL VALOR AL FINAL
            if (type == "POS") System.out.print(currentNode.getValue().getPrioridad() + " ");
        }
    }

    public ArrayList<Tarea> getTareasRango(Integer range1, Integer range2) {
        ArrayList<Tarea> salida = new ArrayList<>();
        getTareasRango(this.root, range1, range2, salida);
        return salida;

    }

    private ArrayList<Tarea> getTareasRango(TreeNode root, Integer range1, Integer range2, ArrayList<Tarea> salida) {

        int prioridad = root.getValue().getPrioridad();

        if (prioridad >= range1 && prioridad <= range2) {
            salida.add(root.getValue());
            if (root.getLeft() != null) getTareasRango(root.getLeft(), range1, range2, salida);
            if (root.getRight() != null) getTareasRango(root.getRight(), range1, range2, salida);
        }
        else {
            if (prioridad < range1) {
                if (root.getRight() != null) getTareasRango(root.getRight(), range1, range2, salida);

            }

            if (prioridad > range2) {
                if (root.getLeft() != null) getTareasRango(root.getLeft(), range1, range2, salida);
            }
        }

        return salida;

    }

}