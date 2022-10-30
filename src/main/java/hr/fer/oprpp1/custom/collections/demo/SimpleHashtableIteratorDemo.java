package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.SimpleHashtable;

import java.util.Iterator;

public class SimpleHashtableIteratorDemo {
    public static void main(String[] args) {
        SimpleHashtable<Integer, Integer> hashtable = new SimpleHashtable<>();
        hashtable.put(0, 1);
        hashtable.put(1, 2);
        hashtable.put(3, 2);
        hashtable.put(0, 2);
        hashtable.put(6, 7);
        hashtable.put(5, 4);
        hashtable.put(2, 4);

        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iterator = hashtable.iterator();

        for(SimpleHashtable.TableEntry<Integer, Integer> entry : hashtable){
            System.out.println(entry.getKey());
        }

        SimpleHashtable<Integer, Integer> hashtable2 = new SimpleHashtable<>();

        hashtable2.put(0, 0);
        hashtable2.put(1, 1);
        hashtable2.put(2, 2);

        Iterator<SimpleHashtable.TableEntry<Integer, Integer>> iterator2 = hashtable2.iterator();

        System.out.println(hashtable2.toString());
        System.out.println(hashtable2.size());
        int i = 0;
        while(iterator2.hasNext()){
            iterator2.next();
            iterator2.remove();
            i++;
        }
        System.out.println(i);
        System.out.println(hashtable2.toString());

    }
}
