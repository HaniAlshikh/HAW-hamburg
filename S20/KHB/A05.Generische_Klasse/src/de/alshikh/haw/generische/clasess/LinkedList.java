package de.alshikh.haw.generische.clasess;

/**********************************************************************
 *
 * Type Parameter Hiding & static nested classes
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class LinkedList<E> {

    // old solution
    //private Node head = null;
    //
    //// a type parameter is already defined and Node can use it.
    //// no need to define a new one, which will be hidden anyway.
    //private class Node {
    //    E value;
    //    Node next;
    //    // Node constructor links the node as a new head
    //    Node(E value) {
    //        this.value = value;
    //        this.next = head;
    //        head = this;
    //    }
    //}
    //
    //public void add(E e) {
    //    new Node(e);
    //    // Link node as new head
    //}
    //public void dump() {
    //    for (Node n = head; n != null; n = n.next)
    //        System.out.println(n.value + " ");
    //}

    private Node<E> head = null;

    private static class Node<N> {
        N value;
        Node<N> next;
        Node(N value, Node<N> next) {
            this.value = value;
            this.next = next;
        }
    }

    public void add(E e) {
        // Link node as new head
        head = new Node<>(e, head);
    }

    public void dump() {
        for (Node<E> n = head; n != null; n = n.next)
            System.out.println(n.value + " ");
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("world");
        list.add("Hello");
        list.dump();
    }
}
