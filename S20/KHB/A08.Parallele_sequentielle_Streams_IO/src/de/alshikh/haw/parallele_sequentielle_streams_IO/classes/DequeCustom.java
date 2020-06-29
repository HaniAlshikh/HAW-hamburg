package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**********************************************************************
 *
 * showcasing the basic implementation of custom Serializable on Deque
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class DequeCustom<E> extends Deque<E> {

    /**
     * save this Deque to a stream (serialize it)
     *
     * @param s the stream
     * @throws IOException if an I/O error occurs
     * @serialData all of the Deque elements
     */
    private void writeObject(ObjectOutputStream s)
            throws IOException {
        // java default serialization mechanism can already do the job
        s.defaultWriteObject();
    }

    /**
     * Reconstitutes this arrDeque from a stream (deserializes it).
     *
     * @param s the stream
     * @throws ClassNotFoundException if the class of a serialized object
     *         could not be found
     * @throws IOException if an I/O error occurs
     */
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
        // java default deserialization mechanism can already do the job
        s.defaultReadObject();
    }
}

