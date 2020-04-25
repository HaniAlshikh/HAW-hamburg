package de.alshikh.haw.generische.clasess;

import java.util.NoSuchElementException;
import java.util.Objects;

public class Deque<E> implements de.alshikh.haw.generische.interfaces.Deque<E> {

    private Node<E> head = null;
    private Node<E> tail = null;

    @Override
    public void addFirst(E e) {
        if (head == null) throw new IllegalArgumentException("Deque is empty");
        shift(e);
    }

    @Override
    public void addLast(E e) {
        if (tail == null) throw new IllegalArgumentException("Deque is empty");
        push(e);
    }

    @Override
    public void push(E e) {
        Node<E> node = new Node<>(e);
        if(head == null) {
            // first node
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;

    }

    private void shift(E e) {
        Node<E> node = new Node<>(e);
        if(head == null) {
            // first node
            tail = node;
        } else {
            node.next = head;
        }
        head = node;
    }

    @Override
    public boolean offerFirst(E e) {
        if (head == null) return false;
        shift(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        if (tail == null) return false;
        push(e);
        return false;
    }

    @Override
    public E removeFirst() {
        Node<E> first = head;
        head = head.next;
        return first.value;
    }

    @Override
    public E removeLast() {
        // TODO
        if (tail == null) throw new IllegalArgumentException("Deque is empty");
        return pop();
    }

    @Override
    public E pop() {
        Node<E> last = tail;
        tail = prev(tail);
        tail.next = null;
        return last.value;
    }

    @Override
    public E pollFirst() {
        if (head     == null) throw new NoSuchElementException("Deque is empty");
        return removeFirst();
    }

    @Override
    public E pollLast() {
        if (tail == null) throw new NoSuchElementException("Deque is empty");
        return removeLast();
    }

    @Override
    public E getFirst() {
        return head.value;
    }

    @Override
    public E getLast() {
        return tail.value;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    // Iterator would be better but not part of the assigment
    public int size() {
        int size = 0;
        for (Node<E> n = head; n != null; n = n.next) size++;
        return size;
    }

    public void dump() {
        for (Node<E> n = head; n != null; n = n.next)
            System.out.println(n.value + " ");
    }

    // we would link the previous node to each node
    // but to stick with assigment we went with this
    // solution
    private Node<E> prev(Node<E> node) {
        Node<E> prev = head;
        while (prev.next != node)
            prev = prev.next;
        return prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deque<?> other = (Deque<?>) o;
        Node<?> otherNode = other.head;
        if (size() != other.size()) return false;
        if (head == null && otherNode == null) return true;
        for (Node<E> n = head; n != null; n = n.next) {
            if (n.value.equals(otherNode.value)) otherNode = otherNode.next; else return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size());
        for (Node<E> n = head; n != null; n = n.next) {
            hash += Objects.hash(n);
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Deque{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        // Node constructor links the node as a new head
        Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }

    }
}
