package de.alshikh.haw.klausur.SS2018.classes;

import de.alshikh.haw.klausur.SS2018.interfaces.IStack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Optional;


public class Stack<E> implements IStack<E>, Serializable {

    transient Object[] es;
    transient int head;
    transient int tail;
    transient int size;
    transient int capacity;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final long serialVersionUID = 259224252849136887L;

    public Stack(int capacity) {
        if (capacity < 0) throw new NegativeArraySizeException();
        es = new Object[this.capacity = capacity];
    }

    public Stack(){
        this(11);
    }

    @Override
    public void push(E e) {
        if (e == null) throw new NullPointerException();
        if (head == tail && size != 0) grow(1);
        es[tail] = e;
        tail = inc(tail, es.length);
        size++;
    }

    @Override
    public E pop() {
        if (size == 0) throw new EmptyStackException();
        tail = dec(tail, es.length);
        E e = elementAt(tail);
        if (e != null) es[tail] = null;
        size--;
        return e;
    }

    @Override
    public Optional<E> peek() {
        if (size == 0) return Optional.empty();
        return Optional.of(elementAt(dec(tail, es.length)));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }


    private void writeObject(ObjectOutputStream out)
            throws IOException {

        out.defaultWriteObject();
        out.writeInt(size);
        out.writeInt(capacity);
        for (int i = head, t = dec(tail, es.length); t >= i; i += inc(head, es.length)) {
            out.writeObject(elementAt(i));
        }
    }
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        in.defaultReadObject();

        size = in.readInt();
        capacity = in.readInt();
        es = new Object[capacity];
        for (int i = 0; i < size ; i++) {
            es[i] = in.readObject();
        }
        head = 0; tail = size;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stack<?> s = (Stack<?>) o;
        if (size != s.size) return false;
        for (int i = head, t = dec(tail, es.length); t >= i; i += inc(head, es.length)) {
            if (!elementAt(i).equals(s.elementAt(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size, capacity);
        for (int i = head, t = dec(tail, es.length); t >= i; i += inc(head, es.length)) {
            hash += elementAt(i).hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Stack{ ");
        for (int i = head, t = dec(tail, es.length); t >= i; i += inc(head, es.length)) {
            str.append(elementAt(i) + " ");
        }
        str.append("}");
        return str.toString();
    }


    private void grow(int needed) {
        // overflow-conscious code
        final int oldCapacity = capacity;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed
                || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        Object[] bk = es;
        es = new Object[capacity = newCapacity];
        for (int i = head, j = 0; i != tail; i = inc(head, bk.length), j++) {
            es[j] = elementAt(i);
        }
        head = 0; tail = size;
    }

    private int newCapacity(int needed, int jump) {
        final int oldCapacity = capacity, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, Stack too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
                ? oldCapacity + jump
                : MAX_ARRAY_SIZE;
    }

    @SuppressWarnings("unchecked")
    private E elementAt(int i) {
        return (E) es[i];
    }

    private int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    private int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }
}
