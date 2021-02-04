package de.hawh.jkjs.huffman;

/**
 * This class represents a simple binary tree.
 *
 * @author Johan Kemper
 * @author Jannik Stuckstätte
 *
 * @param <E> Type parameter of the type of data stored.
 */
public class BinaryTree<E extends Comparable<E>> {

    private E data;
    private BinaryTree<E> left;
    private BinaryTree<E> right;

    public BinaryTree(E data, BinaryTree<E> left, BinaryTree<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryTree(E data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public BinaryTree<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTree<E> left) {
        this.left = left;
    }

    public BinaryTree<E> getRight() {
        return right;
    }

    public void setRight(BinaryTree<E> right) {
        this.right = right;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data.toString());
        sb.append(" - ");
        if (left != null) sb.append("left: ").append(left.toString());
        if (right != null) sb.append("right: ").append(right.toString());
        return sb.toString();
    }
}
