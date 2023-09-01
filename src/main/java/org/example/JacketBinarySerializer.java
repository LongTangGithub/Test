package org.example;

import java.io.*;
import java.util.TreeSet;
public class JacketBinarySerializer {
    /**
     * Serialize a set of Jacket objects into a binary file.
     *
     * @param jacketsSet The set of Jacket objects to be serialized.
     * @param filename   The name of the binary file.
     */
    public static void serializeJacketBinary(TreeSet<Jackets> jacketsSet, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            outputStream.writeObject(jacketsSet);
            System.out.println("Jacket Info serialized to " + filename + " (Binary)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize a binary file, extract the values, and create a new TreeSet of Jacket objects.
     *
     * @param filename The name of the binary file.
     * @return The deserialized TreeSet of Jacket objects, or an empty set if deserialization is not successful.
     */
    public static TreeSet<Jackets> deserializeJacketBinary(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filename))) {
            TreeSet<Jackets> jacketsSet = (TreeSet<Jackets>) inputStream.readObject();
            System.out.println("Jacket is deserialized from " + filename + " (Binary)");
            return jacketsSet;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new TreeSet<>();
    }

}
