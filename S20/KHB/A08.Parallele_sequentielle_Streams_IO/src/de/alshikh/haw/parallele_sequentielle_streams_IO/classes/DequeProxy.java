package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**********************************************************************
 *
 * basic LinkedList implementation
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class DequeProxy<E> extends Deque<E> {
    public DequeProxy() {
        super();
    }

    private Object writeReplace() throws ObjectStreamException {
        return new DequeProxy.DequeInnerProxy<>(this);
    }

    // Nested static class - Proxy
    private static class DequeInnerProxy<E> implements Serializable {

        private static final long serialVersionUID = -5592750437218135456L;
        private Node<E> head;
        private Node<E> tail;

        DequeInnerProxy(DequeProxy<E> o) {
            this.head = o.head;
            this.tail = o.tail;
        }

        // readResolve method for Person.PersonProxy
        private Object readResolve() throws ObjectStreamException {
            DequeProxy<E> Deque = new DequeProxy<>();
            Deque.head = head;
            Deque.tail = tail;
            return Deque; // Uses public constructor
        }
    }

}

