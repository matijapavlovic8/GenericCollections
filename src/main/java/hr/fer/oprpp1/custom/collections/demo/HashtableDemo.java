package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.SimpleHashtable;

public class HashtableDemo {

    public static void main(String[] args) {
        SimpleHashtable<Integer, Integer> hashtable = new SimpleHashtable<>(2);
        hashtable.put(0, 1);
        hashtable.put(1, 2);
        hashtable.put(3, 2);
        hashtable.put(0, 2);
        hashtable.put(6, 7);
        hashtable.put(5, 4);
        hashtable.put(2, 4);
        hashtable.put(11, 15);
        hashtable.put(13, 14);
        hashtable.put(22, 20);

        System.out.println(hashtable.toString());

        System.out.println(hashtable.size());

        System.out.println(hashtable.remove(22));

        System.out.println(hashtable.size());

        System.out.println(hashtable.toString());


    }

}
