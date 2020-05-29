package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;

import de.alshikh.haw.parallele_sequentielle_streams_IO.interfaces.Deque;

import java.io.IOException;
import java.io.Serializable;

/**********************************************************************
 *
 * basic Array Deque implementation using circular array
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class ArrDequeCustom<E> extends ArrDeque<E>
        implements Deque<E>, Serializable {

    public ArrDequeCustom(int size) {
        super(size);
    }

    public ArrDequeCustom() {
        super();
    }

    private static final long serialVersionUID = 5888241769701361418L;

    /**
     * Saves this deque to a stream (that is, serializes it).
     *
     * @param s the stream
     * @throws java.io.IOException if an I/O error occurs
     * @serialData The current size ({@code int}) of the deque,
     * followed by all of its elements (each an object reference) in
     * first-to-last order.
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws IOException {
        // ensure that object is in desired state. Possibly run any business rules if applicable.
        // checkUserInfo();

        // use java default serialization mechanism
        // The class of each serializable object is encoded including the class
        // name and signature of the class, the values of the object's fields and arrays,
        // and the closure of any other objects referenced from the initial objects.
        s.defaultWriteObject();
        // Write out size
        s.writeInt(size());
        // Write out elements in order.
        for (int i = 0, j = head, size = size(); i < size; i++, j = inc(j, es.length)) {
            s.writeObject(es[i]);
        }
    }

    /**
     * Reconstitutes this deque from a stream (that is, deserializes it).
     * @param s the stream
     * @throws ClassNotFoundException if the class of a serialized object
     *         could not be found
     * @throws IOException if an I/O error occurs
     */
    private void readObject(java.io.ObjectInputStream s)
            throws IOException, ClassNotFoundException {

        // The objects must be read back from the corresponding ObjectInputstream
        // with the same types and in the same order as they were written

        // use java default deserialization mechanism
        s.defaultReadObject();
        // Read in size and allocate array
        int size = s.readInt();
        // ObjectInputValidation
        //@Override
        //public void validateObject() {
        //    System.out.println("Validating age.");
        //    if (age < 18 || age > 70)
        //    {
        //        throw new IllegalArgumentException("Not a valid age to create an employee");
        //    }
        //}
        // ensure that object state has not been corrupted or tampered with malicious code
        //validateUserInfo();
        //SharedSecrets.getJavaObjectInputStreamAccess().checkArray(s, Object[].class, size + 1);
        es = new Object[size];
        this.tail = size;

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
            es[i] = s.readObject();
    }

    // we don't support this case...

    // For serializable objects, the readObjectNoData method allows a class
    // to control the initialization of its own fields in the event that a subclass
    // instance is deserialized and the serialization stream does not list
    // the class in question as a superclass of the deserialized object.
    // This may occur in cases where the receiving party uses a different
    // version of the deserialized instance's class than the sending party,
    // and the receiver's version extends classes that are not extended by the sender's version.
    // This may also occur if the serialization stream has been tampered; hence,
    // readObjectNoData is useful for initializing deserialized
    // objects properly despite a "hostile" or incomplete source stream.

    //private void readObjectNoData() throws ObjectStreamException;

}

