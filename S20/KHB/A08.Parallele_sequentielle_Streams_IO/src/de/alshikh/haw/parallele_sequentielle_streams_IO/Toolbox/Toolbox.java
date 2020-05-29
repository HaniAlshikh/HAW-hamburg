package de.alshikh.haw.parallele_sequentielle_streams_IO.Toolbox;

import java.io.*;

public class Toolbox {
    /**
     * Method used for serialization
     * @param obj
     * @param fileName
     */
    public static void serializeObject(Object obj, String fileName) {
        // FileOutputStream is meant for writing streams of raw bytes
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))){
            oos.writeObject(obj);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Method used for deserializing
     * @param fileName
     * @return
     * @throws ClassNotFoundException
     */
    public static Object deSerializeObject(String fileName) throws ClassNotFoundException{
        Object obj = null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))){
            obj = ois.readObject();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }
}
