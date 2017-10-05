/**
 * Created by Daniel on 10/25/2016.
 */
public class SimpleStack<T> implements Stack<T>{
    private int numItemsInStack;
    private StackOfNode<T> stack;

    public SimpleStack(){
        stack = new StackOfNode<T>();
        int numItemsInStack = 0;
    }

    //lets push a node onto the stack.
    public void push(T new_item){
        stack.addNode(new_item);
        numItemsInStack++;
    }

    //take the top node off
    public T pop(){
        if(stack.isEmpty()) {return null;}
        T item = stack.removeNode();
        numItemsInStack--;
        return item;
    }

    //lets see what nodes on top.
    public T peek(){
        return stack.retrieveTopNode();
    }

    //check to see if the stack is empty or not
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    //what size is the stack.
    public int size(){return numItemsInStack;}

    public String toString(){
        return " ";
    }

}
