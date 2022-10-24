package hr.fer.oprpp1.custom.collections;

/**
 * Simple list collection.
 * @param <T> determines the type of objects in the List.
 */

public interface List<T> extends Collection<T>{

    /**
     * Gets the element at the specified index of the list.
     * @param index indicates the index of the desired element.
     * @return Object
     */

    T get(int index);

    /**
     * Inserts the {@code Object} in the list.
     * @param value value of the object that is to be inserted.
     * @param position position on which the element will be inserted.
     */

    void insert(T value, int position);

    /**
     * Returns the index of the first appearance of the given value in the list.
     * @param value Value of the Object whose index is being requested.
     * @return int
     */
    int indexOf(Object value);

    /**
     * Removes the object on the given index of the list.
     * @param index Index of the object that will be removed.
     */
    void remove(int index);

}
