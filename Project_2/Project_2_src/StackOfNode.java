/**
 * Created by Daniel on 10/25/2016.
 */
//used for elimination, for creating a stack of nodes. And SimpleStack.
public class StackOfNode<T> {
    private int numNodes;
    private Node<T> firstNode;

    public StackOfNode(){
        numNodes = 0;
        firstNode = null;
    }

    //getTopNode()
    public T retrieveTopNode(){
        return this.firstNode.element;
    }

    //addNode()
    public void addNode(T object){
        Node<T> newNode = new Node(object);
        newNode.nextNode = firstNode;
        firstNode = newNode;
        numNodes++;
    }

    //removeNode()
    public T removeNode(){
        if(numNodes == 0){
            return null;
        }
        Node<T> toBeReturned = firstNode;
        T eleToBeReturned = toBeReturned.element;
        firstNode = firstNode.nextNode;
        return eleToBeReturned;
    }

    public boolean isEmpty(){
        return (firstNode == null);
    }

    public static void main(String[] args){
        StackOfNode stack = new StackOfNode();
        stack.addNode(3);
        stack.addNode(2);
        stack.addNode(1);
        for (int i=0; i<3; i++){
            System.out.println(stack.removeNode());
        }
    }
}
