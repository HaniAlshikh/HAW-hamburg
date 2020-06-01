package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**********************************************************************
 *
 * showcasing the basic implementation of the Deque serialization proxy
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class DequeProxy<E> extends Deque<E> {

    /**
     * write a new proxy object to a stream of this Deque
     *
     * @throws ObjectStreamException if an stream error occurs
     * @return ArrDequeInnerProxy copy of this ArrDeque
     * @serialData all of the Deque elements
     */
    private Object writeReplace() throws ObjectStreamException {
        return new DequeProxy.DequeInnerProxy<>(this);
    }

    private static class DequeInnerProxy<E> implements Serializable {

        private static final long serialVersionUID = -5592750437218135456L;
        private Node<E> head;
        private Node<E> tail;

        DequeInnerProxy(DequeProxy<E> o) {
            this.head = o.head;
            this.tail = o.tail;
        }

        /**
         * read a stream and create a new Deque object from the proxy object
         *
         * @throws ObjectStreamException if an stream error occurs
         * @return DequeProxy copy of this Deque
         */
        private Object readResolve() throws ObjectStreamException {
            DequeProxy<E> Deque = new DequeProxy<>();
            Deque.head = head;
            Deque.tail = tail;
            return Deque;
        }
    }
}

