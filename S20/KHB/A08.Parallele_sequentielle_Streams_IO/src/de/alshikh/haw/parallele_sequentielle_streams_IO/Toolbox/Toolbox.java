package de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
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
     * reads words from a file, remove punctuation
     * and return an array of the words
     *
     * @param file the file from which the words are read
     * @return string array of words
     */
    public static String[] getWordsArr(String file) {
        Function<String, Stream<String>> toWordsNoPunct =
                line -> Stream.of(line.replaceAll("\\p{Punct}", "").split("\\s+"));

        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            return lines
                    .filter(line -> !line.equals(""))
                    .flatMap(toWordsNoPunct)
                    .map(String::toLowerCase)
                    .toArray(String[]::new);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }


    /**
     * serialize an object to a file
     *
     * @param obj the object to be serialized
     * @param fileName the file in which the object is serialized
     * @throws IOException if an I/O error occurs
     */
    public static void serializeObject(Object obj, String fileName)
            throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File(fileName)))){
            oos.writeObject(obj);
        }
    }

    /**
     * deserialize an object from a file
     *
     * @param fileName the file from which the object is deserialized
     * @return the deserialized object
     * @throws ClassNotFoundException if the class of the serialized object
     *         could not be found
     * @throws IOException if an I/O error occurs
     */
    public static Object deSerializeObject(String fileName)
            throws ClassNotFoundException, IOException{
        Object obj = null;
        try(ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File(fileName)))){
            obj = ois.readObject();
        }
        return obj;
    }
}
