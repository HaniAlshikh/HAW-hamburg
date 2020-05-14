package de.alshikh.haw.generics_streams.clasess;

import java.util.NoSuchElementException;
import java.util.Objects;

/**********************************************************************
 *
 * basic LinkedList implementation
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class Deque<E> implements de.alshikh.haw.generics_streams.interfaces.Deque<E> {

    private int head;
    private int tail; // tail points to last + 1 (tail is always null, otherwise deque is full)
    private Object[] es;

    public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public Deque(int size) {
        es = new Object[Math.max(size, 1)];
    }

    public Deque() {
        this(16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(E e) {
        if (isEmpty()) throw new IllegalArgumentException("Deque is empty");
        enqueue(e);
    }

    /**
     * {@inheritDoc}
     */
    public void enqueue(E e) {
        if (e == null) throw new NullPointerException();
        es[head = dec(head, es.length)] = e;
        if (isFull()) grow(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLast(E e) {
        if (isEmpty()) throw new IllegalArgumentException("Deque is empty");
        push(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(E e) {
        if (e == null) throw new NullPointerException();
        es[tail] = e;
        if (head == (tail = inc(tail, es.length))) grow(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerFirst(E e) {
        if (isEmpty()) return false;
        enqueue(e);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerLast(E e) {
        if (isEmpty()) return false;
        push(e);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        E first = (E) es[head];
        head = inc(head, es.length);
        return first;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        E last = (E) es[tail = dec(tail, es.length)];
        es[tail] = null;
        return last;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E pop() {
        return removeLast();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E pollFirst() {
        try {
            return removeFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E pollLast() {
        try {
            return removeLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked") // only E elements can be added
    private E elementAt(int i) {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        return (E) es[i];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getFirst() {
        return elementAt(head);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getLast() {
        return elementAt(dec(tail, es.length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peekFirst() {
        try {
            return getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peekLast() {
        try {
            return getLast();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return sub(tail, head, es.length);
    }

    private int sub(int i, int j, int modulus) {
        if ((i -= j) < 0) i += modulus;
        return i;
    }

    private boolean isFull() {
        return head == tail;
    }

    private boolean isEmpty() {
        return es[head] == null;
    }

    private void grow(int needed) {
        // overflow-conscious code
        final int oldCapacity = es.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);
        if (jump < needed
                || (newCapacity = (oldCapacity + jump)) - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);


        Object[] temp = es;
        es = new Object[newCapacity];

        // size is called i times
        for (int i = 0, j = head; i < oldCapacity; i++, j = inc(j, oldCapacity)) {
            es[i] = temp[j];
        }
        head = 0; tail = temp.length;
    }


    // stolen from java ArrayDeque
    private int newCapacity(int needed, int jump) {
        final int oldCapacity = es.length, minCapacity;
        if ((minCapacity = oldCapacity + needed) - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("Sorry, deque too big");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (oldCapacity + jump - MAX_ARRAY_SIZE < 0)
                ? oldCapacity + jump
                : MAX_ARRAY_SIZE;
    }

    /**
     * Circularly increments i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    /**
     * Circularly decrements i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    int dec(int i, int modulus) {
        if (--i < 0) i = modulus - 1;
        return i;
    }

    /**
     * empty Deque are considered equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deque<?> other = (Deque<?>) o;
        if (size() != other.size()) return false;
        if (isEmpty() && other.isEmpty()) return true;
        for (int i = 0, j = head, y = other.head; i < size(); i++, j = inc(j, es.length), y = inc(y, other.es.length)) {
            if (!es[j].equals(other.es[y])) return false;
        }


        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size());
        if (!isEmpty()) {
            for (int i = 0, j = head; i < size(); i++, j = inc(j, es.length)) {
                hash += es[j].hashCode();
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0, j = head; i < size() - 1; i++, j = inc(j, es.length))
            output.append(es[j]).append(", ");
        return output.append(es[dec(tail, es.length)]).insert(0,"[").append("]").toString();
    }
}
