package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import de.alshikh.haw.parallele_sequentielle_streams_IO.interfaces.Deque;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**********************************************************************
 *
 * basic Array Deque implementation using circular array
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class ArrDequeProxy<E> extends ArrDeque<E>
        implements Deque<E>, Serializable {

    private static final long serialVersionUID = -7071966788253823740L;

    public ArrDequeProxy(int size) {
        super(size);
    }

    public ArrDequeProxy() {
        super();
    }

    /**
     * writeReplace method for the proxy pattern
     * @return
     */
    private Object writeReplace() throws ObjectStreamException {
        return new ArrDequeInnerProxy<>(this);
    }

    // Nested static class - Proxy
    private static class ArrDequeInnerProxy<E> implements Serializable {

        private static final long serialVersionUID = 623286517279433635L;
        private int head;
        //private int tail; // tail points to last + 1 (tail is always null, otherwise deque is full)
        private int size;
        private Object[] es;

        ArrDequeInnerProxy(ArrDequeProxy<E> o) {
            this.head = o.head;
            //this.tail = o.tail;
            this.size = o.size();
            this.es = o.es;
        }

        // readResolve method for Person.PersonProxy
        private Object readResolve() throws ObjectStreamException {
            ArrDequeProxy<E> arrDeque = new ArrDequeProxy<>(size);
            for (int i = 0, j = head; i < size; i++, j = inc(j, es.length)) {
                arrDeque.push((E) es[i]);
            }
            return arrDeque; // Uses public constructor
        }

        private int inc(int i, int modulus) {
            if (++i >= modulus) i = 0;
            return i;
        }
    }
}

