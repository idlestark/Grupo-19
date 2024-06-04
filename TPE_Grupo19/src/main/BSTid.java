package main;

public class BSTid extends BinarySearchTree {

    public BSTid () {
        super();
    }

    @Override
    public Tarea get (String taskId) {
        //SI EL ÁRBOL NO TIENE RAÍZ (ESTÁ VACÍO), RETORNA FALSE
        if (this.root == null) return null;
            //SI TIENE RAÍZ, LLAMA AL MÉTODO PRIVADO CON EL NODO RAÍZ Y EL VALOR A BUSCAR. LUEGO RETORNA LO QUE RETORNE ESE MÉTODO
        else return get(this.root, taskId);
    }

    //MÉTODO RECURSIVO PRIVADO DE HAS ELEM
    private Tarea get (TreeNode currentNode, String taskId) {
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
    public void insert (Tarea task) {
        //SI EL ÁRBOL NO TIENE RAÍZ (ESTÁ VACÍO), EL NODO SE AGREGA COMO RAÍZ
        if (this.root == null) this.root = new TreeNode(task);
            //SI YA TIENE RAÍZ, SE LLAMA AL MÉTODO DE AGREGAR CON LA RAÍZ Y EL NUEVO VALOR
        else insert(this.root, task);
    }

    //MÉTODO RECRUSIVO PRIVADO DE INSERT
    private void insert (TreeNode currentNode, Tarea task) {
        //SI EL VALOR DEL NODO ACTUAL (EL QUE VIENE POR PARÁMETRO) ES MAYOR AL VALOR QUE SE QUIERE INSERTAR AL ÁRBOL...
        if (currentNode.getIdValue().compareTo(task.getId()) > 0) {
            //Y SI EL NODO ACTUAL NO TIENE HIJO IZQUIERDO...
            if (currentNode.getLeft() == null) {
                //SE INSERTA UN NUEVO NODO CON (EL VALOR INGRESADO) COMO HIJO IZQUIERDO DEL NODO ACTUAL
                TreeNode temp = new TreeNode(task);
                currentNode.setLeft(temp);
                //SI EL NODO ACTUAL TIENE UN HIJO IZQUIERDO, HACE UNA LLAMADA RECURSIVA CON ESE NODO HIJO COMO NUEVO EL NODO ACTUAL
            } else insert(currentNode.getLeft(), task);
            //SI EL VALOR DEL NODO ACTUAL ES MENOR AL VALOR QUE SE QUIERE INSERTAR AL ÁRBOL...
        } else if (currentNode.getIdValue().compareTo(task.getId()) < 0) {
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

}
