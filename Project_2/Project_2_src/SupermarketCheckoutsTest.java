import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * <H1>TestSupermarket</H1>
 * Unit testing for the Supermarket program
 *
 * @author      Jean-Paul Labadie jhl42@nau.edu
 * @version     1.0
 * @since       8-5-2015
 */
public class SupermarketCheckoutsTest {

    @Test
    public void testAddZeroCustomer(){
        SupermarketCheckouts test = new SupermarketCheckouts();
        test.buildMarket(2,2);
        assertEquals("Failed when testing addCustomer with zero items", false, test.addCustomer(0));
    }

    @Test
    public void testAddNegativeCustomer(){
        SupermarketCheckouts test = new SupermarketCheckouts();
        test.buildMarket(2,2);
        assertEquals("Failed when testing addCustomer with negative items", false, test.addCustomer(-5));
    }

    @Test
    public void testCustomerFullLines(){
        SupermarketCheckouts test = new SupermarketCheckouts();
        test.buildMarket(1,2);
        test.addCustomer(1);
        test.addCustomer(2);
        assertEquals("Failed when testing addCustomer when lines are full", false, test.addCustomer(10));
    }

    @Test
    public void testTickRemovesCustomersCorrectly1(){
        SupermarketCheckouts test = new SupermarketCheckouts();
        test.buildMarket(1,3);
        test.addCustomer(2);
        assertEquals("Failed when testing tick", 0, test.tick());
    }

    @Test
    public void testTickRemovesCustomersCorrectly2(){
        SupermarketCheckouts test = new SupermarketCheckouts();
        test.buildMarket(1,3);
        test.addCustomer(1);
        test.addCustomer(2);
        test.addCustomer(2);
        assertEquals("Failed when testing tick", 0, test.tick());
        assertEquals("Failed when testing tick",1, test.tick());
        assertEquals("Failed when testing tick", 0, test.tick());
        assertEquals("Failed when testing tick", 0, test.tick());
        assertEquals("Failed when testing tick", 1, test.tick());
        assertEquals("Failed when testing tick", 0, test.tick());
        assertEquals("Failed when testing tick", 0, test.tick());
        assertEquals("Failed when testing tick", 1, test.tick());
        assertEquals("Failed when testing tick", -1, test.tick());
    }
}
