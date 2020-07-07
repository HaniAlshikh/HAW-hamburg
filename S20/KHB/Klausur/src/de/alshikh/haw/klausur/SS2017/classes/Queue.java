package de.alshikh.haw.klausur.SS2017.classes;

import de.alshikh.haw.klausur.SS2017.exception.QueueEmptyException;
import de.alshikh.haw.klausur.SS2017.exception.QueueFullException;
import de.alshikh.haw.klausur.SS2017.Interfaces.IQueue;

import java.util.Objects;
import java.util.Optional;

public class Queue<E> implements IQueue<E> {

    private Object[] es;
    private int head;
    private int tail;
    private int size;

    public Queue(int capacity) {
        if (capacity <= 0) throw new NegativeArraySizeException();
        es = new Object[capacity];
    }

    public Queue() {
        this(IQueue.DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(E e) throws QueueFullException {
        if (e == null) throw new NullPointerException();
        if (isFull()) throw new QueueFullException("Queue is full");
        es[tail] = e;
        tail = inc(tail, es.length);
        size++;
    }

    @Override
    public void dequeue() {
        if (isEmpty()) throw new QueueEmptyException("Queue is Empty");
        es[head] = null;
        head = inc(head, es.length);
        size--;
    }

    @Override
    public Optional<E> peek() {
        if (isEmpty()) return Optional.empty();
        return Optional.of(elementAt(head));
    }

    @Override
    public boolean isFull() {
        return (head == tail && size != 0);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue<?> q = (Queue<?>) o;
        if (size != q.size) return false;
        if (!isEmpty()) {
            for (int i = head, t = dec(tail, es.length); i != t; i += inc(head, es.length)) {
                if (!elementAt(i).equals(q.elementAt(i))) return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size);
        if (!isEmpty()) {
            for (int i = head, t = dec(tail, es.length); i != t; i += inc(head, es.length)) {
                hash += Objects.hash(elementAt(i));
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Queue { ");
        if (!isEmpty()) {
            for (int i = head, t = dec(tail, es.length); i != t; i += inc(head, es.length)) {
                str.append(es[i]).append(" ");
            }
        }
        str.append("}");
        return str.toString();
    }

    @SuppressWarnings("unchecked")
    private E elementAt(int i) {
        return (E) es[i];
    }
    /**
     * Circularly increments i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    private int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    /**
     * Circularly decrements i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    private int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }
}
