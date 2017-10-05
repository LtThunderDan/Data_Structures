import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SimpleStackTest {

    private SimpleStack<Integer> teststack = new SimpleStack();
    private Stack<Integer> ctrlstack = new Stack();
    Random rando = new Random();

    @Before
    public void setUp() throws Exception {
        Integer i;
        if(ctrlstack.isEmpty() || teststack.isEmpty()){
            for(int x=0; x<10; x++) {
                i = rando.nextInt(9999);
                teststack.push(i);
                ctrlstack.push(i);
            }
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testPush() throws Exception {
        try {
            Integer i;
            for(int x=0; x<rando.nextInt(10); x++) {
                i = rando.nextInt(9999);
                teststack.push(i);
                ctrlstack.push(i);
            }
        }
        catch(Exception e){
            fail("Your stack failed when I tried to call push(<Object>)");
        }
    }

    @Test
    public void testPop() {
        try{
            for(int i=0; i<rando.nextInt(50); i++) {
                assertEquals("Your stack failed when I compared its pop() output against Java's Stack",
                        teststack.pop(), ctrlstack.pop());
            }
        }
        catch (EmptyStackException e)
        {
            //this can happen in normal use cases, and is not a bug
        }
    }

    @Test
    public void testPeek() {
        for(int i=0; i<rando.nextInt(50); i++) {
            assertEquals("Your stack failed when I compared its peek() output against Java's Stack",
                    teststack.peek(), ctrlstack.peek());
        }
    }

    @Test
    public void testIsEmpty() throws Exception {
        assertEquals("Your stack failed when I compared its isEmpty() output against Java's Stack",
                    teststack.isEmpty(), ctrlstack.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        assertEquals("Your stack failed when I compared its size() output against Java's Stack",
                teststack.size(), ctrlstack.size());
    }
}