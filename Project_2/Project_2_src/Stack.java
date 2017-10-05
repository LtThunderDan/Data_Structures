/**
 * Interface for SimpleStack.java
 *
 * Build a Generic Stack ADT
 *
 * @author Jean-Paul Labadie
 * @since November 6, 2015
 */
public interface Stack<T> {

    /**
     * Pushes an object onto the "top" of the stack
     * @param T a Generic object
     */
    public void push(T new_item);


    /**
     * Removes and returns the object on the "top" of the stack
     * @return the Generic object from the top of the stack; null if empty
     */
    public T pop();


    /**
     * Returns without removing the object on the "top" of the stack, allowing it to be accessed without being removed.
     * @return the Generic object from the top of the stack; null if empty
     */
    public T peek();

    /**
     *
     * @return boolean true if the stack is empty, or false otherwise
     */
    public boolean isEmpty();

    /**
     * @return integer representing the number of objects in the stack, only zero when empty
     */
    public int size();

    /**
     * Return a string representation of the stack. I recommend [Bob, Mark, Toothless,...] as a format,
     * where 'Bob' at the top of the stack.
     *
     * @return returns a string which represents the stack
     */
    public String toString();
}
