package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import de.alshikh.haw.parallele_sequentielle_streams_IO.interfaces.Deque;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**********************************************************************
 *
 * basic Array Deque implementation using circular array
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class ArrDeque<E> implements Deque<E> {

    // The default serialization mechanism for an object writes the class of the object,
    // the class signature, and the values of all non-transient and non-static fields.
    transient int head;
    transient int tail; // tail points to last + 1 (tail is always null, otherwise deque is full)
    transient Object[] es;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public ArrDeque(int size) {
        es = new Object[Math.max(size, 1)];
    }

    public ArrDeque() {
        this(16);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(E e) {
        if (isEmpty()) throw new IllegalArgumentException("Deque is empty");
        unshift(e);
    }

    /**
     * {@inheritDoc}
     */
    public void unshift(E e) {
        if (e == null) throw new NullPointerException();
        es[head = dec(head, es.length)] = e;
        if (isFull()) grow();
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
        if (head == (tail = inc(tail, es.length))) grow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerFirst(E e) {
        if (isEmpty()) return false;
        unshift(e);
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
    @Override
    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        E first = elementAt(head);
        head = inc(head, es.length);
        return first;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        E last = elementAt(tail = dec(tail, es.length));
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
        int size = tail;
        if ((size -= head) < 0) size += es.length;
        return size;
    }

    public Stream<E> stackStream() {
        Stream.Builder<E> stackStream = Stream.builder();
        for (int i = 0, j = tail - 1, size = size(); i < size; i++, j = dec(j, es.length)) {
            stackStream.add(elementAt(j));
        }
        return stackStream.build();
    }

    public Stream<E> queueStream() {
        Stream.Builder<E> queueStream = Stream.builder();
        for (int i = 0, j = head, size = size(); i < size; i++, j = inc(j, es.length)) {
            queueStream.add(elementAt(j));
        }
        return queueStream.build();
    }


    @SuppressWarnings("unchecked") // only E elements can be added
    private E elementAt(int i) {
        if (isEmpty()) throw new NoSuchElementException("Deque is empty");
        return (E) es[i];
    }


    private boolean isFull() {
        return head == tail;
    }

    private boolean isEmpty() {
        return es[head] == null;
    }

    private void grow() {
        final int oldCapacity = es.length;
        int newCapacity;
        // Double capacity if small; else grow by 50%
        int jump = (oldCapacity < 64) ? (oldCapacity + 2) : (oldCapacity >> 1);

        // unfortunately we don't have enough memory to test this
        if ((newCapacity = (oldCapacity + jump)) < 0)
            if (oldCapacity < MAX_ARRAY_SIZE)
                newCapacity = MAX_ARRAY_SIZE;
            else throw new IllegalStateException("Sorry, deque too big");

        Object[] temp = es;
        es = new Object[newCapacity];
        for (int i = 0, j = head; i < oldCapacity; i++, j = inc(j, oldCapacity)) {
            es[i] = temp[j];
        }
        head = 0; tail = temp.length;
    }

    /**
     * Circularly increments i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    protected int inc(int i, int modulus) {
        if (++i >= modulus) i = 0;
        return i;
    }

    /**
     * Circularly decrements i, mod modulus.
     * Precondition and postcondition: 0 <= i < modulus.
     */
    protected int dec(int i, int modulus) {
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
        ArrDeque<?> other = (ArrDeque<?>) o;
        return Arrays.equals(queueStream().toArray(), other.queueStream().toArray());
        //if (size() != other.size()) return false;
        //if (isEmpty() && other.isEmpty()) return true;
        //for (int i = 0, j = head, y = other.head, size = size(); i < size;
        //     i++, j = inc(j, es.length), y = inc(y, other.es.length))
        //    if (!es[j].equals(other.es[y])) return false;
        //return true;
    }

    @Override
    public int hashCode() {
        int hash = Objects.hash(size());
        return queueStream().map(Objects::hashCode).reduce(hash,Integer::sum);
    }

    @Override
    public String toString() {
        return Arrays.toString(queueStream().toArray());
        //StringBuilder output = new StringBuilder();
        //for (int i = 0, j = head, size = size(); i < size - 1; i++, j = inc(j, es.length))
        //    output.append(es[j]).append(", ");
        //return output.append(es[dec(tail, es.length)]).insert(0,"[").append("]").toString();
    }
}

