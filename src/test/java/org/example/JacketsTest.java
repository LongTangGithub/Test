package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.Path;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Jackets class serialization and deserialization
 */
class JacketsTest {

    private final String testFileName = "test_jackets.csv";
    private final String xmlTestFileName = "test_jackets.xml";


    /**
     * Setting up the test environment by serializing a set of Jackets objects to a test CSV file
     */
    @BeforeEach
    void setUp() {
        TreeSet<Jackets> jacketsSet = new TreeSet<>();
        jacketsSet.add(new Jackets("Brand2", "Red", 200));
        jacketsSet.add(new Jackets("Brand5", "White", 500));
        jacketsSet.add(new Jackets("Brand1", "Black", 100));
        jacketsSet.add(new Jackets("Brand4", "Orange", 400));
        jacketsSet.add(new Jackets("Brand3", "Yellow", 300));

        Jackets.serializeJacketCSV(jacketsSet, testFileName);
    }

    /**
     * Deleting the test csv file after each test to clean up
     */
    @AfterEach
    void tearDown() {
        try{
            Files.deleteIfExists(Path.of(testFileName));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Testing the deserialization and compare the process by checking if the deserialized set of Jackets match the
     * original set
     *
     * <p>
     *     Personal Note:
     *     The assertEquals() method used the equals() method in Jackets.java that was overridden to determine if the two
     *     jackets objects are equal
     * </p>
     */
    @Test
    void deserializedAndCompareSets() {
        Set<Jackets> originalJacketsSet = new TreeSet<>();
        originalJacketsSet.add(new Jackets("Brand1", "Black", 100));
        originalJacketsSet.add(new Jackets("Brand2", "Red", 200));
        originalJacketsSet.add(new Jackets("Brand3", "Yellow", 300));
        originalJacketsSet.add(new Jackets("Brand4", "Orange", 400));
        originalJacketsSet.add(new Jackets("Brand5", "White", 500));

        Set<Jackets> deserializedJacketsSet = Jackets.deserialize(testFileName);

        assertEquals(originalJacketsSet, deserializedJacketsSet, "Prices of both sets of Jackets should be equal");

    }

    @Test
    void serializeJacketXMLAndDeserialize() {
        TreeSet<Jackets> originalJacketsSet = new TreeSet<>();
        originalJacketsSet.add(new Jackets("Brand1", "Black", 100));
        originalJacketsSet.add(new Jackets("Brand2", "Red", 200));

        Jackets.serializeJacketXML(originalJacketsSet, xmlTestFileName);
        TreeSet<Jackets> deserializedJacketsSet = Jackets.deserializedJacketXML(xmlTestFileName);

        assertEquals(originalJacketsSet, deserializedJacketsSet, "Sets should be equal after XML serialization and deserialization");

        // Clean up: Delete the test XML file
        File xmlFile = new File(xmlTestFileName);
        assertTrue(xmlFile.delete(), "Test XML file should be deleted");
    }

}
