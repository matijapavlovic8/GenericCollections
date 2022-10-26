package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SimpleHashtableTest {

    @Test
    public void testPut(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        assertEquals(1, table.size());
    }

    @Test
    public void testGet(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        assertEquals(1, table.get(0));
    }

    @Test
    public void testContains(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        assertTrue(table.containsKey(0));
        assertTrue(table.containsValue(1));
        assertFalse(table.containsKey(1));
        assertFalse(table.containsValue(5));
    }

    @Test
    public void testRemove(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);

        assertEquals(1, table.remove(0));
        assertTrue(table.isEmpty());
    }

}
