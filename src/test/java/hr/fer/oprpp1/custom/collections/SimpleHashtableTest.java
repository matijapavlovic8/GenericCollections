package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SimpleHashtableTest {

    @Test

    public void testPut(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        assertTrue(table.containsKey(0));
    }
}
