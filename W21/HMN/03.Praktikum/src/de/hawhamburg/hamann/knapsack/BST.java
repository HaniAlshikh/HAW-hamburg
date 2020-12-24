package de.hawhamburg.hamann.knapsack;

import de.hawhamburg.hamann.collections.BSTree;

import java.util.Comparator;

public class BST<K, E> implements BSTree<K, E> {

    private Node<K,E> root;
    private int size;
    private final Comparator<K> cmp;


    public BST(Comparator<K> cmp) {
        this.cmp = cmp;
    }


    @Override
    public void add(K key, E element) {
        if (key == null || element == null)
            throw new IllegalArgumentException("key and element cannot be null!");
        root = addR(root, key, element);
        size++;
    }

    @Override
    public void remove(K key) {
        if (key == null) throw new IllegalArgumentException("key cannot be null!");
        if (!contains(key)) return;
        root = removeR(root, key);
        size--;
    }

    @Override
    public E search(K key) {
        return searchR(root, key);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean contains(K key) {
        return containsR(root, key);
    }


    private Node<K,E> addR(Node<K,E> n, K key, E element) {
        if (n == null) return new Node<>(key, element);

        if (cmp.compare(n.key, key) > 0)
            n.left = addR(n.left, key, element);
        else
            n.right = addR(n.right, key, element);

        return n;
    }

    private Node<K, E> removeR(Node<K, E> n, K key) {
        // remove node by setting it to null
        if (key.equals(n.key)) return null;

        if (cmp.compare(n.key, key) > 0)
            n.left = removeR(n.left, key);
        else
            n.right = removeR(n.right, key);

        return n;
    }


    private E searchR(Node<K,E> n, K key) {
        if (n == null) return null;
        if (cmp.compare(n.key, key) == 0) return n.element;

        if (cmp.compare(n.key, key) > 0)
            return searchR(n.left, key);
        else
            return searchR(n.right, key);
    }

    private boolean containsR(Node<K,E> n, K key) {
        return searchR(n, key) != null;
    }

    public static class Node<K, E> {
        K key;
        E element;
        Node<K,E> left;
        Node<K,E> right;

        public Node(K key, E element) {
            this.key = key;
            this.element = element;
        }
    }
}
