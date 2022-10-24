package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;
import hr.fer.oprpp1.custom.collections.Collection;
import hr.fer.oprpp1.custom.collections.LinkedListIndexedCollection;
import hr.fer.oprpp1.custom.collections.List;

public class ListDemo {

    public static void main(String[] args) {
        List col1 = new ArrayIndexedCollection();
        List col2 = new LinkedListIndexedCollection();
        col1.add("Ivana");
        col2.add("Jasna");
        Collection col3 = col1;
        Collection col4 = col2;
        col1.get(0);
        col2.get(0);
        col1.forEach(x -> System.out.println(x)); // Ivana
        col2.forEach(x -> System.out.println(x)); // Jasna
        col3.forEach(x -> System.out.println(x)); // Ivana
        col4.forEach(x -> System.out.println(x)); // Jasna
    }
}
