package hr.fer.oprpp1.custom.collections;

/**
 * Implementation of a Map-like class.
 * @param <K> key of the {@code Dictionary} entry.
 * @param <V> value of the {@code Dictionary} entry.
 *
 * @author MatijaPav
 */

public class Dictionary<K, V>{

    /**
     * Nested class {@code Entry} represents an entry of a {@code Dictionary}.
     * @param <K> key
     * @param <V> value
     */
    private static class Entry<K, V>{
        private K key;
        private V value;

        public Entry(K key, V value){
            if(key == null) throw new NullPointerException("Key of an entry can't be null");
            this.key = key;
            this.value = value;
        }

        /**
         * Getter for the key of the {@code Entry}.
         * @return key
         */

        public K getKey() {
            return key;
        }

        /**
         * Getter for the value of the {@code Entry}.
         * @return value.
         */

        public V getValue() {
            return value;
        }

        /**
         * Setter for the value of the {@code Entry}.
         * @param value new value of the {@code Entry}.
         */

        public void setValue(V value) {
            this.value = value;
        }
    }

    /**
     * Adaptee collection used to implement the {@code Dictionary}
     */
    private ArrayIndexedCollection<Entry<K, V>> entries;

    /**
     * Creates an instance od {@code Dictionary} class.
     */
    public Dictionary(){
        this.entries = new ArrayIndexedCollection<>();
    }

    /**
     * Method used to determine if the instance of class {@code Dictionary} is empty.
     * @return {@code true} if Dictionary is empty and {@code false} otherwise.
     */

    public boolean isEmpty(){
        return this.entries.isEmpty();
    }

    /**
     * Clears the Dictionary.
     */
    public void clear(){
        this.entries.clear();
    }

    /**
     * Gets te value of the Entry with the given key.
     * @param key of the entry whose value is requested.
     * @return {@code value} if key is present in the dictionary, {@code null} otherwise.
     */

    public V get(Object key){
        return this.getEntry(key).getValue();
    }

    /**
     * Creates a Dictionary entry and puts it in to the dictionary. If dictionary already contains
     * an Entry with the given key, the value is overwritten.
     * @param key of the {@code Entry}
     * @param value of the {@code Entry}
     * @return previous {@code value} if the dictionary already contained the key, {@code null} otherwise.
     */

    public V put(K key, V value){
        if(value == null) throw new NullPointerException("Can't add null as a value.");
        if(this.getEntry(key) == null){
            this.entries.add(new Entry<>(key, value));
            return null;
        }
        V old = this.getEntry(key).getValue();
        this.getEntry(key).setValue(value);
        return old;
    }

    /**
     * Private method used to get the {@code Entry} of the dictionary with the given key.
     * @param key of the desired entry.
     * @return {@code Entry<K, V>} if key is present, {@code null} otherwise.
     */

    private Entry<K, V> getEntry(Object key){
        ElementsGetter<Entry<K, V>> getter = this.entries.createElementsGetter();
        Entry<K, V> entry;
        while(getter.hasNextElement()){
            entry = getter.getNextElement();
            if(entry.getKey().equals(key))
                return entry;
        }
        return null;
    }

    /**
     * Removes the dictionary entry with the given key.
     * @param key key of the entry that is to be removed.
     * @return value of the removed entry.
     */

    public V remove(K key){
        if(this.getEntry(key) == null){
            return null;
        }
        V old = this.get(key);
        this.entries.remove(this.getEntry(key));
        return old;
    }

    /**
     * Returns the size of the dictionary.
     * @return
     */
    public int size(){
        return entries.size();
    }


}
