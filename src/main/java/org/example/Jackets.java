package org.example;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

/**
 * Represent a jacket object with brand, color, and price attributes
 */
public class Jackets implements Serializable, Comparable {
    private final String brand;
    private final String color;
    private final int price;

    /**
     * Jacket Constructor
     *
     * @param brand the brand of the jacket
     * @param color the color of the jacket
     * @param price the price of the jacket
     */
    public Jackets(String brand, String color, int price) {
        this.brand = brand;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor(){
        return color;
    }

    public int getPrice(){
        return price;
    }

    /**
     * This method serializes the myJacket objet into a CSV file by writing its attributes (brand,color, price) to the file
     * Note:
     * <p>
     *     The price(int) is converted into a string, .append() expect a string a parameter.
     *     Everytime methods are invoked, it runs on a stack, if there's an error, it will trace the where error originated.
     * </p>
     *
     * @param jacket The jacket object to be serialized
     * @param filename The name of the CSV file
     */
    public static void serializeJacketCSV(Jackets jacket, String filename){
        try{
            Path filePath = Path.of(filename);
            StringBuilder serialJacketInfo = new StringBuilder();
            serialJacketInfo.append("Brand, Color, Price\n");
            serialJacketInfo.append(jacket.getBrand() + ", " + jacket.getColor() + ", " + jacket.getPrice() + "\n");

            // The StandardOpenOption.CREATE and StandardOpenOption.WRITE options are used to create the file if it doesn't exist and overwrite its contents if it does.
            Files.writeString(filePath, serialJacketInfo.toString(), StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("Jacket Info serialized to " + filename);
        }catch (IOException e){
            e.printStackTrace();
        }
        jacket.prettyPrint("Serialized");
    }

    /**
     * This method the CSV file, extract the values and creates a new Jacket object with the extracted values
     *
     * Note:
     * <p>
     *     Indicate the deserialization is not successful if the extracted values are invalid, and it will return null.
     *     Skipping the header line --> not actual data (br.readLine)
     *     Extract values and parse
     *     Trim the values so the parseInt method can correctly parse the price value without throwing a NumberFormatException
     * </p>
     *
     * @param filename The name of the CSV file
     * @return The deserialized Jacket object, or null if deserialization is not successful
     */
    public static Jackets deserialize(String filename){

        Jackets jacket = null;
        try{
            Path filePath = Path.of(filename);

            String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);


            String[] lines = fileContent.split("\\r?\\n");     // Split the content into lines

            if(lines.length >  1){                                  // Check if one line exists
                String[] values = lines[1].split(",");        // Extract the values from the second line (skipping the header)
                if(values.length == 3){                             // if the array length is 3 [Brand, Color, Price]
                    String brand = values[0].trim();
                    String color = values[1].trim();
                    int price = Integer.parseInt(values[2].trim());  // the parseInt doesn't know how to parse a space


                    jacket = new Jackets(brand, color, price);      // creating a new jacket object with the extracted values and assign to it
                }
            }
        }catch(IOException e ){
            e.printStackTrace();
        }catch(NumberFormatException e){
            System.out.println("Invalid price value in the CSV file. Check the formatting.");
            e.printStackTrace();
        }
        System.out.println("Jacket is deserialized from " + filename);
        jacket.prettyPrint("Deserialized");
        return jacket;
    }

    /**
     * Prints the jacket with the specified Type (e.g., "Serialized" or "Deserialized")
     *
     * @param jacketType The type of Jacket Information
     */
    public void prettyPrint(String jacketType){
        System.out.println("Jacket Information: (" + jacketType + "): ");
        System.out.println("Brand: " + brand);
        System.out.println("Color: " + color);
        System.out.println("Price: " + price);
        System.out.println();
    }
    /**
     * Check if the current jacket object is equal to the specified jacket object
     *
     * @param deserializedJacket  The jacket object to compare with
     * @return true if the jackets are equal, false otherwise
     */
    public boolean checkEquality(Jackets deserializedJacket){
        boolean isEqual = this.equals(deserializedJacket);
        System.out.println("The original and deserialized jackets are " + (isEqual ? "equal." : "not equal."));
        return isEqual;
    }

    /**
     * By overriding the equals() method in the Jacket class, it allows the attributes of two Serialization.Jackets object to be compared
     * and determine if they're equal
     *
     * Note:
     * <p>
     *     Compare the attributes of the object not the object itself since that the two objects will not be equal due to
     *     different memory address
     * </p>
     *
     * @param o The object to compare with
     * @return true if the objects are equal, false otherwise
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jackets jackets = (Jackets) o;
        return price == jackets.price && Objects.equals(brand, jackets.brand) && Objects.equals(color, jackets.color);
    }

    /**
     * @return The hash code value for the jacket object
     */
    @Override
    public int hashCode() {
        return Objects.hash(brand, color, price);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
