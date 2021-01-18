package de.hawh.JKJSHS.huffman.assignment;

public class BinaryTree<D> {
    D data;
    BinaryTree<D> left;
    BinaryTree<D> right;

    public BinaryTree(D data) {
        this.data = data;
    }

    public BinaryTree(D data, BinaryTree<D> left, BinaryTree<D> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public D getData() {
        return data;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
