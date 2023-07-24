package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JacketsTest {

    private final String testFileName = "test_jackets.csv";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    // Delete the test csv file after each test to clean up
    void tearDown() {
        try{
            Files.deleteIfExists(Path.of(testFileName));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    void serializeJacketCSV() {
        Jackets testJacket = new Jackets("Patagonia", "Blue", 100);
        Jackets.serializeJacketCSV(testJacket, testFileName);

        // Read the CSV file and compare its content with the expected data
        try{
            String fileContent = Files.readString(Path.of(testFileName), StandardCharsets.UTF_8);
            String expectedContent = "Brand, Color, Price\nPatagonia, Blue, 100\n";
            assertEquals(expectedContent, fileContent, "Serialized content doesn't match the expected data");
        }catch (IOException e) {
            fail("Failed to read test CSV file ");
        }
    }

    @Test
    void deserialize() {
        // Creating a test CSV file with multiple lines of jacket data
        String testCSVContent = "Brand, Color, Price\nChrome Hearts, Black, 300\nColumbia, Grey, 75, Arcteryx, red, 400";
        try{
            Files.writeString(Path.of(testFileName), testCSVContent, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        }catch(IOException e){
            fail("Failed to create the test CSV file.");
        }

        Jackets deserializedJacket = Jackets.deserialize(testFileName);

        Jackets expectedJacket = new Jackets("Chrome Hearts", "Black", 300);

        // Comparing the attributes of the deserialized Jacket with the expected jacket object
        assertEquals(expectedJacket.getBrand(), deserializedJacket.getBrand(), "Brand attribute doesn't match");
        assertEquals(expectedJacket.getColor(), deserializedJacket.getColor(), "Brand attribute doesn't match");
        assertEquals(expectedJacket.getPrice(), deserializedJacket.getPrice(), "Brand attribute doesn't match");
    }

    /**
     * Creating two sets of 10 Jackets ob ojects and comparing them
     */
    @Test
    void testEqualityOfSets() {
        Set<Jackets> firstJacketSet = new HashSet<>();
        for(int i = 1; i <= 10; i++){
            firstJacketSet.add(new Jackets("Brand" + i, "Color" + i,  100 * i));
        }

        Set<Jackets> secondJacketSet = new HashSet<>();
        for(int i = 1; i <= 10; i++){
            secondJacketSet.add(new Jackets("Brand" + i, "Color" + i,  100 * i));
        }
        assertEquals(firstJacketSet, secondJacketSet, "Both sets of Jackets are equal");
    }
}
