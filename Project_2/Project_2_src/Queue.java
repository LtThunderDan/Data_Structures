import java.util.NoSuchElementException;

/**
 * @author      Jean-Paul Labadie jhl42@nau.edu
 * @version     2.5.0
 * @since       1.5.0
 *
 * A Queue is a collection designed for holding elements prior to processing.
 *
 * Queues provide insertion, extraction, and inspection operations.
 * Generally these methods throw an exception if the operation fails. In most implementations, insert operations
 * cannot fail.
 *
 * Queues typically, but do not necessarily, order elements in a FIFO (first-in-first-out) manner.
 * Think of this as a line in a store, where people add to the end of the line, but are subtracted from the front of the line.
 * Exceptions to this order include priority queues and stacks, but ignore those for now.
 *
 * Whatever the ordering used, the head of the queue is that element which would be removed
 * by a call to remove(). In this FIFO queue, all new elements are inserted at the tail of the queue with add(). Also,
 * note that in our implementation, you should never allow null objects to be inserted into the Queue. Likewise,
 * you should restrict the Queue, such that whatever object type is used to initialize the Queue, only that type of
 * object can be added to the Queue.
 *
 * See references:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
 * http://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html
 * http://docs.oracle.com/javase/tutorial/essential/exceptions/
 * http://docs.oracle.com/javase/tutorial/extra/generics/index.html
 *
 * Goals:
 * Implement an Interface
 * Implement Generics
 * Implement Exceptions
 * Implement a Queue Abstract Data-type
 *
 *
 * Requirements:
 * Create a class SimpleQueue that implements this Interface
 * Create SimpleQueue using an array as the underlying data structure
 * Create SimpleQueue such that it correctly supports wrap-around
 * Create SimpleQueue using Generics so that it can contain any Type of Object
 * Create SimpleQueue methods such that they throw the correct exception in the correct circumstance
 * Create SimpleQueue such that it has a fixed size determined by the constructor
 * Create SimpleQueue such that it supports wraparound as defined in class and in your text
 *
 * For more information, check in your book or look at the similar documentation for Java's Queue interface:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
 * http://stackoverflow.com/questions/529085/how-to-create-a-generic-array-in-java
 */
public interface Queue<E> {

    /**
     * Use this signature for the constructor.
     * Save the int size, and use it to determine the size of the underlying array.
     *
     * public SimpleQueue(Class<E> type, int size)
     *
     * Also, use something like "final E[] queue = (E[]) Array.newInstance(type,size);"
     * to create the underlying array. You'll have to import java.lang.reflect.Array
     * to do this. Also, since you need to access this array throughout the class,
     * you'll want to declare the array in the class scope, then initialize it in
     * the constructor.
     */

    /**
     * @param element the object we want to add to the last position (end) of the queue
     * @throws IllegalStateException the element cannot be added because the queue is in the 'full' state
     */
    void add(E element) throws IllegalStateException;

    /**
     * Removes and returns the first element (front) of the queue, or throws an exception.
     *
     * @return the element from the first position (front) of the queue
     * @throws NoSuchElementException the element cannot be returned because the Queue is empty
     */
    E remove() throws NoSuchElementException;

    /**
     * Returns the first element (front) of the Queue, without removing it from the data structure.
     *
     * @return a reference to the first element (front) of the Queue.
     */
    E peek();

    /**
     *
     * @return returns the total number of elements currently in this Queue.
     */
    int size();

    /**
     *
     * @return returns the maximum number of elements that this Queue could hold,
     * regardless of how 'full' the Queue is.
     */
    int getMaxCapacity();
}