package de.alshikh.haw.klausur.SS2019.Classes;

import de.alshikh.haw.klausur.SS2019.Exceptions.EmptyQueueException;
import de.alshikh.haw.klausur.SS2019.Interfaces.IQueue;

import java.util.Objects;

public class Queue<E> implements IQueue<E> {

    private Node<E> head = null;
    private Node<E> tail = new Node<>(null);
    private int size = 0;

    public Queue() {}

    @Override
    public Queue<E> enqueue(E e) {
        Node<E> node = new Node<>(e);
        if(head == null) head = node; else tail.next.next = node;
        tail.next = node;
        node.next = tail;
        size++;
        return this;
    }

    @Override
    public Queue<E> dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        head = head.next;
        if (head == tail) head = tail = null;
        size--;
        return this;
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        return head.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue<?> queue = (Queue<?>) o;
        if (size != queue.size) return false;
        Node<?> n = head, no = queue.head;
        for (int i = 0; i < size; i++) {
            if (!n.item.equals(no.item)) return false;
            n = n.next; no = no.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        Node<?> n = head;
        int hash = Objects.hash(size);
        for (int i = 0; i < size; i++) {
            hash += Objects.hash(n.item);
            n = n.next;
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Queue{" + head + '}';
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append(item);
            if (this != next.next) str.append(", ").append(next);
            return str.toString();
        }
    }

}
