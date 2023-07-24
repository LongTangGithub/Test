package org.example;


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
 */
public class Driver {
    public static void main(String[] args){
        Jackets myJacket = new Jackets("North Face", "Black" , 299);
        String filename = "jackets.csv";

        Jackets.serializeJacketCSV(myJacket, filename);
        Jackets deserializedJacket = Jackets.deserialize(filename);

        deserializedJacket.checkEquality(myJacket);

    }
}

