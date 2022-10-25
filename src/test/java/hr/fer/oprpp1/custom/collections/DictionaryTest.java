package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class DictionaryTest {

    @Test
    public void testEntryWithNullAsKey(){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        assertThrows(NullPointerException.class, () -> dict.put(null, 5));
    }

    @Test
    public void testGet(){
        Dictionary<Integer, String> dict = new Dictionary<>();
        dict.put(0, "value");
        assertEquals("value", dict.get(0));
    }

    @Test
    public void testPut(){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        dict.put(0, 1);
        assertEquals(1, dict.put(0, 2));
        assertEquals(2, dict.get(0));
    }
    @Test
    public void testIsEmpty(){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        assertTrue(dict.isEmpty());
        dict.put(7, 7);
        assertFalse(dict.isEmpty());
    }

    @Test
    public void testSize(){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        assertEquals(0, dict.size());
        dict.put(0, 1);
        assertEquals(1, dict.size());
    }

    @Test
    public void testRemove(){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        dict.put(0, 0);
        assertEquals(0, dict.remove(0));
        assertEquals(0, dict.size());
    }

    @Test
    public void clear(){
        Dictionary<Integer, Integer> dict = new Dictionary<>();
        dict.put(0, 1);
        dict.put(2, 1);
        dict.clear();
        assertTrue(dict.isEmpty());
    }

}
