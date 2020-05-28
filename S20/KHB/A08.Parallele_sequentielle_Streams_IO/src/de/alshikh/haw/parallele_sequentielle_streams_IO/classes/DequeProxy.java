package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import de.alshikh.haw.parallele_sequentielle_streams_IO.interfaces.*;
import java.util.NoSuchElementException;
import java.util.Objects;

/**********************************************************************
 *
 * basic LinkedList implementation
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class DequeProxy<E> implements Deque<E> {

    private Node<E> head = null;
    private Node<E> tail = null;

    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirst(E e) {
        if (head == null) throw new IllegalArgumentException("Deque is empty");
        unshift(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addLast(E e) {
        if (tail == null) throw new IllegalArgumentException("Deque is empty");
        push(e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(E e) {
        Node<E> node = new Node<>(e);
        if(head == null) head = node; else tail.next = node;
        tail = node;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerFirst(E e) {
        if (head == null) return false;
        unshift(e);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean offerLast(E e) {
        if (head == null) return false;
        push(e);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeFirst() {
        if (head == null) throw new NoSuchElementException("Deque is empty");
        Node<E> first = head;
        head = head.next;
        if (head == null) tail = null;
        return first.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeLast() {
        if (head == null) throw new NoSuchElementException("Deque is empty");
        Node<E> last = tail;
        tail = prev(tail);
        tail.next = null;
        if (last == tail) {tail = null;head = null;}
        return last.value;
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
        if (head == null) throw new NoSuchElementException("Deque is empty");
        return head.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E getLast() {
        if (head == null) throw new NoSuchElementException("Deque is empty");
        return tail.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peekFirst() {
        if (head == null) return null;
        return head.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E peekLast() {
        if (head == null) return null;
        return tail.value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    // Iterator would be better but not part of the assigment
    public int size() {
        int size = 0;
        for (Node<E> n = head; n != null; n = n.next) size++;
        return size;
    }

    /**
     * add an element to the beginning of the Deque
     *
     * @param e the element to be added
     */
    private void unshift(E e) {
        Node<E> node = new Node<>(e);
        if(head == null) tail = node; else node.next = head;
        head = node;
    }

    /**
     * @param node from which the previous node is returned
     * @return the previous node
     */
    // we would link the previous node to the node itself
    // but to stick with the assigment we went with this solution
    private Node<E> prev(Node<E> node) {
        Node<E> prev = head;
        while (prev.next != node && prev.next != null)
            prev = prev.next;
        return prev;
    }

    /**
     * empty Deque are considered equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DequeProxy<?> other = (DequeProxy<?>) o;
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
            hash += n.value.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Deque{" + head + '}';
    }

    private static class Node<N> {
        N value;
        Node<N> next;
        Node(N value) {
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

