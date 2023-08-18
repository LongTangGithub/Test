package org.example;
import java.util.TreeSet;


/**
 *
 * Jacket Object Serialization and Deserialization:
 * <p>
 *     Printing out the original and deserialized jacket information, and checks for equality between the
 *     original and deserialized jackets using the equals method
 * </p>
 *
 * Note:
 * <p>
 *     Serialization store the state of an object
 *     Imagine you create  a project and save the data to the file = savepoint
 *
 * </p>
 *
 * Created a TreeSet called jacketsSet to store the Jackets object "myJacket"
 *
 */
public class Driver {
    public static void main(String[] args){

        TreeSet<Jackets> jacketsSet = new TreeSet<>();
        Jackets myJacket = new Jackets("North Face", "Black" , 299);
        jacketsSet.add(myJacket);

        String filename = "jackets.csv";

        Jackets.serializeJacketCSV(jacketsSet, filename);
        TreeSet<Jackets> deserializedJacketsSet = Jackets.deserialize(filename);

        // Checking equality of myJacket with the deserializedJacket
        for (Jackets deserializedJacket : deserializedJacketsSet){
            deserializedJacket.checkEquality(myJacket);
        }
    }
}

