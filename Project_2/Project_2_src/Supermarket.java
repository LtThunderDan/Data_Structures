/**
 * Interface for SupermarketCheckouts.java
 *
 * Build a model that represents supermar/ket checkout lines using the correct ADTs.
 *
 * @author Jean-Paul Labadie
 * @since November 6, 2015
 */
public interface Supermarket {

    /**
     * This method should define the number of checkout lines as specified by numClerks, with each line
     * having a maximum length specified by lineCapacity. Remember to validate your inputs. Do nothing
     ; if either input input (or both) has a value less than or equal to zero.
     */
    public void buildMarket(int numClerks, int lineCapacity);

    /**
     * A new "customer" with 'numItems' items in their "cart" should be created and added to the best line
     * for that customer. The 'best line' is whichever line is shortest.
     *
     * @param numItems the number of items in the customer's cart, must be greater than zero
     * @return false if all of the lines were at capacity, or if numItems is <= 0.
     */
    public boolean addCustomer(int numItems);

    /**
     * Represents one 'tick' of time passing in the supermarket simulation:
     *   - Each 'clerk' should clear one item from their current customer when tick() is called
     *   - When a customer is left with zero items after a tick, they will be removed on the next tick
     *   - tick should return the total number of customers removed from checkout lines this tick
     *   - If customers are present but none are cleared this tick, the tick returns 0
     *   - If no customers are present, tick should return -1
     *
     *   @return the number of customers removed this tick, or 0 if none removed, or -1 if no customers
     */
    public int tick();
}
