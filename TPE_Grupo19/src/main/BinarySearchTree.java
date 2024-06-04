package main;
import java.util.ArrayList;
import java.util.List;

public abstract class BinarySearchTree {

    protected TreeNode root;

    public BinarySearchTree () {
        this.root = null;
    }

    public void setRoot (Tarea task) {
        if (this.root == null) this.root = new TreeNode(task);
    }

    public Tarea getRoot () {
        //SI HAY RAÍZ EN EL ÁRBOL, RETURNA SU VALOR
        if (this.root != null) return this.root.getValue();
            //SI NO HAY, RETORNA NULO
        else return null;
    }

    public boolean isEmpty () {
        //SI EL ÁRBOL NO TIENE RAÍZ, RETORNA TRUE (ESTÁ VACÍO)
        return (this.root == null);
    }

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
        }
    }

    //MÉTODO PRIVADO PARA IMPRIMIR LOS NODOS EN CUALQUIER ORDEN
    private void printNodes(TreeNode currentNode, String type) {
        //SI EL NODO ACTUAL NO ES NULO...
        if (currentNode != null) {
            //SI EL TIPO ES "PRE" (PRE-ORDER), IMPRIME EL VALOR AL INICIO
            if (type == "PRE") System.out.print(currentNode.getIdValue() + " ");
            //HACE LLAMADA RECURSIVA CON EL HIJO IZQUIERDO DEL NODO ACTUAL
            printNodes(currentNode.getLeft(), type);
            //SI EL TIPO ES "IN" (IN-ORDER), IMPRIME EL VALOR ENTRE EL HIJO IZQUIERDO Y EL DERECHO
            if (type == "IN") System.out.print(currentNode.getIdValue() + " ");
            //HACE LLAMADA RECURSIVA CON EL HIJO DERECHO DEL NODO ACTUAL
            printNodes(currentNode.getRight(), type);
            //SI EL TIPO ES "POS" (POS-ORDER), IMPRIME EL VALOR AL FINAL
            if (type == "POS") System.out.print(currentNode.getIdValue() + " ");
        }
    }

    public List<Tarea> getAllTasks () {
        //CREA UN ARRAYLIST VACÍO
        List<Tarea> tasks = new ArrayList<Tarea>();
        //Y SE RELLENA CON EL MÉTODO PRIVADO
        getAllTasks(this.root, tasks);
        return tasks;
    }

    //MÉTODO PRIVADO RECURSIVO
    private void getAllTasks (TreeNode currentNode, List<Tarea> tasks) {
        //SE AGREGA EL VALOR DEL NODO ACTUAL (SU TAREA)
        tasks.add(currentNode.getValue());
        //SI TIENE HIJO IZQUIERDO, HACE UNA LLAMADA RECURSIVA CON ESE HIJO
        if (currentNode.getLeft() != null) getAllTasks(currentNode.getLeft(), tasks);
        //SI TIENE HIJO DERECHO, HACE UNA LLAMADA RECURSIVA CON ESE HIJO
        if (currentNode.getRight() != null) getAllTasks(currentNode.getRight(), tasks);
    }


    public abstract Tarea get(String id);

    public abstract void insert(Tarea tarea);


}













