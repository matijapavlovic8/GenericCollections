package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

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

    @Test
    public void testIteratorRemove(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iter = table.iterator();
        assertThrows(IllegalStateException.class, iter::remove);
    }

    @Test
    public void testIteratorRemove2(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iter = table.iterator();
        iter.next();
        iter.remove();
        assertTrue(table.isEmpty());
    }

    @Test
    public void testIteratorConcurrent(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iter = table.iterator();
        table.put(1, 2);
        assertThrows(ConcurrentModificationException.class, iter::next);
    }

    @Test
    public void testHasNext(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iter = table.iterator();
        assertTrue(iter.hasNext());
        iter.next();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testConcurrentRemove(){
        SimpleHashtable<Integer, Integer> table = new SimpleHashtable<>();
        table.put(0, 1);
        table.put(1, 2);
        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iter = table.iterator();
        iter.next();
        iter.remove();
        iter.next();
        iter.remove();
        assertTrue(table.isEmpty());
    }

}
