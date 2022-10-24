package hr.fer.oprpp1.custom.collections;

/**
 * Class Collection represents some general collection of objects.
 * @param <T> determines the type of objects in the Collection.
 *
 * @author MatijaPavlovic
 *
 */

public interface Collection<T> {
	
	/**
	 * 
	 * @return Returns {@code true} if collection contains no objects and {@code false} otherwise.
	 */
	
	default boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * 
	 * @return Returns the number of currently stored objects in this collection.
	 */
	
	int size();
	
	/**
	 * Adds the given object into this collection.
	 */
	
	void add(T value) ;
	
	/**
	 * @return Returns {@code true} only if the collection contains given value, otherwise returns {@code false}
	 */
	
	boolean contains(Object value);
	
	/**
	 * @return Returns {@code true} only if the collection contains given value as determined by equals method and removes
	 * one occurrence of it
	 */
	
    boolean remove(Object value);
	
	/**
	 * @return Allocates new array with size equals to the size of this collection, fills it with collection content and
	 * returns the array.
	 */
	
	public Object[] toArray();
	
	/**
	 * Method calls processor.process() for each element of this collection. The order in which elements
	 * will be sent is undefined in this class.
	 * @param processor instance of a class {@code Processor} that processes all elements of the collection.
	 */
	
	default void forEach(Processor<? super T> processor) {
        ElementsGetter<T> getter = this.createElementsGetter();
        while(getter.hasNextElement()){
            processor.process(getter.getNextElement());
        }
    }
	
	/**
	 * Adds all elements of given collection to the current collection.
	 * @param other is an instance of a {@code Collection} whose elements will be added to given collection.
	 */
	
	default void addAll(Collection<? extends T> other) {
		
		/*
		 * Local class that extends the Processor class. Overrides the method {@code process} and then uses it as an
		 * argument in the {@code forEach} method.
		 * @author MatijaPav
		 *
		 */
		
		class addEachItemProcessor implements Processor<T> {

			public void process(T value) {
				add(value);
			}
		}
		
		Processor<? super T> processor = new addEachItemProcessor();
		other.forEach(processor);
		
	}
	
	/**
	 * Removes all elements from this collection.
	 */
	
	void clear() ;

    /**
     * Creates an element getter.
     */

    ElementsGetter<T> createElementsGetter();

    /**
     * Adds all elements of the given {@code Collection} that satisfy the tester to the current collection.
     * @param col {@code Collection} whose elements are being added.
     * @param tester {@code Tester} instance that is used to check if the elements are satisfying.
     */
    default void addAllSatisfying(Collection<? extends T> col, Tester<? super T> tester){
        if(col == null || tester == null) throw new NullPointerException("Neither argument should be null!");

        ElementsGetter<? extends T> getter = col.createElementsGetter();
        getter.processRemaining(element ->{
            if(tester.test(element))
                this.add(element);
        });

    }
}
