package main;

public class TreeNode {

    private Tarea value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Tarea value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Tarea getValue() {
        return value;
    }

    public void setValue(Tarea newValue) {
        this.value = newValue;
    }

    public String getIdValue () {
        return value.getId();
    }

}
