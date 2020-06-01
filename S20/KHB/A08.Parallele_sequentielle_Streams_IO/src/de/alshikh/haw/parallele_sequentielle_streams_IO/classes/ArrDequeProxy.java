package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**********************************************************************
 *
 * showcasing the basic implementation of the ArrDeque serialization proxy
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class ArrDequeProxy<E> extends ArrDeque<E>
        implements Serializable {

    private static final long serialVersionUID = -7071966788253823740L;

    public ArrDequeProxy(int size) {
        super(size);
    }

    public ArrDequeProxy() {
        super();
    }

    /**
     * write a new proxy object to a stream of this ArrDeque
     *
     * @throws ObjectStreamException if an stream error occurs
     * @return ArrDequeInnerProxy copy of this ArrDeque
     * @serialData The current size ({@code int}) of the ArrDeque,
     * followed by all of its elements in first-to-last order.
     */
    private Object writeReplace() throws ObjectStreamException {
        return new ArrDequeInnerProxy<>(this);
    }

    private static class ArrDequeInnerProxy<E> implements Serializable {

        private static final long serialVersionUID = 623286517279433635L;
        private int size;
        private Object[] es;

        ArrDequeInnerProxy(ArrDequeProxy<E> o) {
            this.size = o.size();
            this.es = new Object[Math.max(size, 1)];
            // avoid writing the entire array and stick with the filled part
            for (int i = 0, j = o.head; i < size; i++, j = inc(j, o.es.length)) {
                this.es[i] = o.es[j];
            }
        }

        /**
         * read a stream and create a new ArrDeque object from the proxy object
         *
         * @throws ObjectStreamException if an stream error occurs
         * @return ArrDequeProxy copy of this ArrDeque
         */
        @SuppressWarnings("unchecked")
        private Object readResolve() throws ObjectStreamException {
            ArrDequeProxy<E> arrDeque = new ArrDequeProxy<>(size);
            for (int i = 0; i < size; i++) {
                arrDeque.push((E) es[i]);
            }
            return arrDeque;
        }

        private int inc(int i, int modulus) {
            if (++i >= modulus) i = 0;
            return i;
        }
    }
}

