package de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**********************************************************************
 *
 * Utility class containing useful methods to the current project
 *
 * @author Hani Alshikh
 *
 ************************************************************************/
public final class Toolbox {

    private Toolbox() {}

    /**
     * time the elapsed time of an operation
     *
     * @param op the operation to be run and timed
     *           the return value of the operation is ignored
     * @return the elapsed time in ms
     */
    public static long timeOp(Runnable op) {
        long startTime = System.currentTimeMillis();
        op.run();
        return System.currentTimeMillis() - startTime;
    }

    /**
     * serialize an object to a file
     *
     * @param obj the object to be serialized
     * @param fileName the file in which the object is serialized
     */
    public static void serializeObject(Object obj, String fileName) {
        // FileOutputStream is meant for writing streams of raw bytes
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))){
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * deserialize an object from a file
     *
     * @param fileName the file from which the object is deserialized
     * @return the deserialized object
     * @throws ClassNotFoundException if the class of the serialized object
     *         could not be found
     */
    public static Object deSerializeObject(String fileName) throws ClassNotFoundException{
        Object obj = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))){
            obj = ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
