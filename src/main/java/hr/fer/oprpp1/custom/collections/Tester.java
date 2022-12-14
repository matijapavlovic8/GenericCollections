package hr.fer.oprpp1.custom.collections;

/**
 *
 */

public interface Tester<T> {
    /**
     * Tests if the object is acceptable.
     * @param obj Object that is tested.
     * @return {@code true} if object is acceptable, {@code false} otherwise
     */
    boolean test(T obj);
}
