package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a simple HashMap.
 * @param <K> Type of key.
 * @param <V> Type of value.
 *
 * @author MatijaPav
 */

public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K, V>>{

    /**
     * Implementation of SimpleHashtables entry.
     * @param <K> Type of key.
     * @param <V> Type of value.
     */
    public static class TableEntry<K, V>{
        private K key;
        private V value;
        private TableEntry<K, V> next;

        /**
         * Instantiates a new TableEntry
         * @param key key of the entry.
         * @param value value of the entry.
         * @throws NullPointerException if null is passed as a key.
         *
         */
        private TableEntry(K key, V value){
            if(key == null) throw new NullPointerException("Key can't be null!");
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Getter for the key of the entry.
         * @return {@code K} key
         */

        public K getKey() {
            return key;
        }

        /**
         * Getter for the value uf the entry.
         * @return {@code V} value.
         */

        public V getValue() {
            return value;
        }

        /**
         * Setter for the value of entry.
         * @param value New value of the entry.
         */

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * Data structure that contains all entries of the Hashtable.
     * Implemented as an array of {@code TableEntry<K, V>}
     */
    private TableEntry<K, V>[] table;

    /**
     * Number of stored pairs;
     */
    private int size;

    /**
     * Counts the number of times the SimpleHashtable was modified.
     */
    private int modificationCount;

    /**
     * Creates an instance of a {@code SimpleHashtable}
     * @param capacity initial capacity of SimpleHashTable.
     */
    @SuppressWarnings("unchecked")
    public SimpleHashtable(int capacity){
        if(capacity < 1) throw new IllegalArgumentException("Initial capacity can't be less than 1");
        this.table = (TableEntry<K, V>[]) new TableEntry[getFirstPowerOf2(capacity)];
        this.size = 0;
        this.modificationCount = 0;
    }

    /**
     * Creates an instance of {@code SimpleHashtable} with initial capacity set to default value of 16.
     */

    public SimpleHashtable(){
        this(16);
    }

    /**
     * Auxiliary method that is used to determine the first equal, or greater than the capacity, power of 2.
     * @param capacity desired capacity of the table.
     * @return first power of 2 that is greater, or equal to the desired capacity.
     */
    private int getFirstPowerOf2(int capacity){
        int i = 2;
        while(i < capacity)
            i *= 2;

        return i;
    }

    /**
     * Returns the number of pairs saved to the Simple hashtable.
     * @return {@code int} number of saved pairs.
     */

    public int size(){
        return this.size;
    }

    /**
     * Checks if the hashtable is empty.
     * @return {@code true} if hashtable is empty, {@code false} otherwise.
     */

    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * Gets the entry of the table with the given key.
     * @param key key of the desired entry.
     * @return {@code TableEntry<K, V>} if hashtable contains the key, {@code null} otherwise.
     */

    private TableEntry<K, V> getEntry(Object key){
        int slot = key.hashCode() % this.table.length;
        TableEntry<K, V> head = this.table[slot];
        while(head != null){
            if(head.getKey().equals(key))
                return head;
            head = head.next;
        }
        return null;
    }

    /**
     * Returns the value mapped to the given key.
     * @param key key whose value is requested.
     * @return value of the entry if the map contains the given key, null otherwise.
     */
    public V get(Object key){
        return this.getEntry(key).getValue();
    }

    /**
     * Puts the key-value pair in to the table. If key is already contained within the table it overwrites and returns the previous value.
     * @param key Key of the entry
     * @param value Value of the entry
     * @return previous {@code value} if table already contains key, {@code null} otherwise.
     */

    public V put(K key, V value){
        if(checkLoadFactor())
            rehash();

        int slot = key.hashCode() % this.table.length;
        TableEntry<K, V> head = this.table[slot];

        if(head == null){
            this.table[slot] = new TableEntry<>(key, value);
            this.size++;
            this.modificationCount++;
            return null;
        }

        while(head.next != null && head.getKey() != key)
            head = head.next;

        V old = null;
        if(head.getKey().equals(key)){
            old = head.getValue();
            head.setValue(value);
            return old;
        }
        head.next = new TableEntry<>(key, value);
        this.size++;
        this.modificationCount++;
        return null;
    }

    /**
     * Checks if SimpleHashtable contains an entry with the given key.
     * @param key key that is being searched.
     * @return {@code true} if SimpleHashtable contains.
     */

    public boolean containsKey(Object key){
        return this.getEntry(key) != null;
    }

    /**
     * Removes the entry from the table mapped to the given key.
     * @param key key of the entry that is to be deleted.
     * @return value of the removed entry, or null if there is no such entry.
     */

    public V remove(Object key){
        if(this.getEntry(key) == null) return null;

        this.modificationCount++;

        int slot = key.hashCode() % this.table.length;
        TableEntry<K, V> curr = table[slot];

        if(curr.getKey().equals(key)){
            table[slot] = curr.next;
            this.size--;
            V old = curr.value;
            curr = null;
            return old;
        }
        TableEntry<K, V> previous = curr;
        while(!curr.getKey().equals(key)){
            previous = curr;
            curr = curr.next;
        }

        V old = curr.getValue();
        previous.next = curr.next;

        curr = null;
        this.size--;
        return old;
    }

    /**
     * Checks if given value is present in the SimpleHashtable.
     * @return {@code true} if value is present, {@code false} otherwise.
     */
    public boolean containsValue(V value){
        for(TableEntry<K, V> entry : table){
            while(entry != null){
                if(entry.getValue().equals(value))
                    return true;
                entry = entry.next;
            }
        }
        return false;
    }

    /**
     * Creates an array from the SimpleHashtable. Elements are added in the order in which they are stored in the SimpleHashtable.
     * @return TableEntry<K, V> array
     */

    @SuppressWarnings("unchecked")
    public TableEntry<K, V>[] toArray(){
        TableEntry<K, V>[] arr = (TableEntry<K, V>[]) new TableEntry[this.size];
        int index = 0;
        for(TableEntry<K, V> entry : table){
            while(entry != null){
                arr[index++] = entry;
                entry = entry.next;
            }
        }
        return arr;
    }

    /**
     * Returns the SimpleHashtable in string format.
     * @return String
     */

    public String toString(){
        if(isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        int slot = 0;
        for(TableEntry<K, V> entry : table){
            if(entry != null) {
                sb.append("[");
                while (entry != null) {
                    if (entry.next == null)
                        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("]").append('\n');
                    else
                        sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");

                    entry = entry.next;
                }
            }

        }
        String s = sb.toString();
        return s.substring(0, s.length() - 1);
    }

    /**
     * Checks if the hashtable is overfilled.
     * @return {@code true} if the ratio between the number of elements and length of the internal
     * data structure is 0.75 or greater.
     */
    private boolean checkLoadFactor(){
        return ((double)this.size / this.table.length) >= 0.75;
    }

    /**
     * Clears the entire hashtable. Doesn't affect capacity.
     */
    public void clear(){
        this.modificationCount++;
        for(TableEntry<K, V> head : table){
            head = null;
        }
        this.size = 0;
    }

    /**
     * Method doubles the length of the internal data structure and rehashes the table.
     */
    @SuppressWarnings("unchecked")
    private void rehash(){
        TableEntry<K, V>[] old = this.toArray();
        int oldLen = this.table.length;

        this.table = (TableEntry<K, V>[]) new TableEntry[oldLen * 2];
        this.size = 0;
        for(TableEntry<K, V> entry : old){
            this.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Creates a new iterator.
     * @return SimpleHashtable iterator.
     */
    @Override
    public Iterator<SimpleHashtable.TableEntry<K, V>> iterator(){
        return new IteratorImpl();
    }

    /**
     * Nested class IteratorImpl implements an Iterator for the SimpleHashtable.
     *
     * @author MatijaPav
     */
    private class IteratorImpl implements Iterator<SimpleHashtable.TableEntry<K, V>>{

        private TableEntry<K, V> currentEntry;

        private TableEntry<K, V> nextEntry;

        private int slot;

        private boolean isRemovable;

        private int returnedElements;

        private int modificationCount;
        private int originalSize;


        private IteratorImpl(){
            this.currentEntry = null;
            this.slot = 0;
            this.isRemovable = false;
            this.returnedElements = 0;
            this.modificationCount = SimpleHashtable.this.modificationCount;
            this.originalSize = SimpleHashtable.this.size;
        }

        /**
         * Used to determine if Iterator has a next value to return.
         * Compares the number of returned elements to the size of the SimpleHashtable.
         * @return {@code true} if there is a next value to be returned, {@code false} otherwise.
         * @throws ConcurrentModificationException if the SimpleHashtables internal data structure was modified
         * after the iterator was instantiated.
         */
        public boolean hasNext(){
            if(modificationCount != SimpleHashtable.this.modificationCount)
                throw new ConcurrentModificationException("SimpleHashtable was modified after the instantiation of the iterator!");
            return returnedElements < originalSize;
        }

        /**
         * Gets the next element of the iteration.
         * @return next {@code SimpleHashtable.TableEntry<K, V>}
         */
        public SimpleHashtable.TableEntry<K, V> next(){
            if(!hasNext()) throw new NoSuchElementException("No next element!");
            isRemovable = true;
            returnedElements++;

            if(currentEntry == null){
                while(currentEntry == null)
                    currentEntry = SimpleHashtable.this.table[slot++];
                return currentEntry;
            }

            if(currentEntry.next != null) {
                currentEntry = currentEntry.next;
                return currentEntry;
            }

            currentEntry = SimpleHashtable.this.table[slot++];
            while(currentEntry == null)
                currentEntry = SimpleHashtable.this.table[slot++];

            return currentEntry;

        }

        /**
         * Iterator removes the current element.
         * Method increases modification count to nullify the diffrence created by delegating the removal
         * of current entry to the {@code SimpleHashtable.remove(Object key)} method which also increases the modification count.
         */
        public void remove(){
            if(!isRemovable) throw new IllegalStateException("Can't remove this entry!");
            this.modificationCount++;
            SimpleHashtable.this.remove(currentEntry.getKey());
            isRemovable = false;
        }

    }

}
