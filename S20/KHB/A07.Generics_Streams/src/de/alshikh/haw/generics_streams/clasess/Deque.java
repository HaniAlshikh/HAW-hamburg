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

    private int head = -1;
    private int tail = head;
    private Object[] es;
    // if two deque have the same elements but different capacity they are still equal?
    private int capacity = 16;

    public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public Deque(int size) {
        // or stetment should be gone if head and tail = 0
        if (size == 0) size = capacity;
        es = new Object[size];
    }

    public Deque() {
        es = new Object[capacity];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(E e) {
        if (head == -1) throw new IllegalArgumentException("Deque is empty");
        enqueue(e);
    }

    /**
     * {@inheritDoc}
     */
    public void enqueue(E e) {
        if (isFull()) grow(1);
        if (head == -1) head = tail = 0;
        else if (head == 0) head = es.length - 1;
        else head--;
        es[head] = e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLast(E e) {
        if (tail == -1) throw new IllegalArgumentException("Deque is empty");
        push(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(E e) {
        if (isFull()) grow(1);
        if (tail == -1) tail = head = 0;
        else if (tail == es.length - 1) tail = 0;
        else tail++;
        es[tail] = e;
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
        if (head == tail) head = tail = -1;
        else if (head == es.length - 1) head = 0;
        else head++;
        return first;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        E last = (E) es[tail];
        if (head == tail) head = tail = -1;
        else if (tail == 0) tail = es.length - 1;
        else tail--;
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
        return elementAt(tail);

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
        // if moved to 0 default ist null just return es[tail]
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
        return isEmpty() ? 0 : (es.length - head + tail) % es.length + 1;
    }

    private boolean isFull() {
        return head == (tail + 1) % es.length;
    }

    private boolean isEmpty() {
        return head == -1;
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

        for (int i = head, y = 0; i != tail; i = (i + 1) % oldCapacity, y++) {
            es[y] = temp[i];
        }
        es[temp.length - 1] = temp[tail];

        head = 0; tail = temp.length - 1;
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
     * empty Deque are considered equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deque<?> other = (Deque<?>) o;
        if (size() != other.size()) return false;
        if (isEmpty() && other.isEmpty()) return true;
        //for (int i = head; i != tail; i = (i + 1) % es.length) {
        //    if (!es[i].equals(other.es[i])) return false;
        //}
        //return es[tail] == other.es[tail];
        for (int i = 0; i < size(); i++) {
            if (!es[(head + i) % capacity].equals(other.es[(other.head + i) % other.capacity])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size());
        if (!isEmpty()) {
            for (int i = head; i != tail; i = (i + 1) % es.length) {
                hash += es[i].hashCode();
            }
            hash += es[tail].hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = head; i != tail; i = (i + 1) % es.length) output.append(es[i]).append(", ");
        return output.append(es[tail]).insert(0,"[").append("]").toString();
    }
}
