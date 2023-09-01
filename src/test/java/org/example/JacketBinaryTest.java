package org.example;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JacketBinaryTest {
    private final String binaryTestFileName = "test_jackets.ser";

    @Test
    void serializeJacketBinaryAndDeserialize() {
        TreeSet<Jackets> originalJacketsSet = new TreeSet<>();
        originalJacketsSet.add(new Jackets("Brand1", "Black", 100));
        originalJacketsSet.add(new Jackets("Brand2", "Red", 200));

        JacketBinarySerializer.serializeJacketBinary(originalJacketsSet, binaryTestFileName);
        Set<Jackets> deserializedJacketsSet = JacketBinarySerializer.deserializeJacketBinary(binaryTestFileName);

        assertEquals(originalJacketsSet, deserializedJacketsSet, "Sets should be equal after binary serialization and deserialization");
    }
}
