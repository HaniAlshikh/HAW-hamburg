package de.alshikh.haw.parallele_sequentielle_streams_IO.classes;


import java.io.IOException;

/**********************************************************************
 *
 * basic LinkedList implementation
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public class DequeCustom<E> extends Deque<E> {

    public DequeCustom() {
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
        // Write out elements in order.
        //for (Node<E> n = head; n != null; n = n.next) {
        //    if (n.value.equals(otherNode.value)) otherNode = otherNode.next; else return false;
        //}
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
        //int size = s.readInt();
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
        //es = new Object[size];
        //this.tail = size;

        // Read in all elements in the proper order.
        //for (int i = 0; i < size; i++)
        //    es[i] = s.readObject();
    }
}

