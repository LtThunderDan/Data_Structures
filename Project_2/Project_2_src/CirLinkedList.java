import static java.lang.System.out;

/**
 * Created by Daniel on 10/26/2016.
 */
public class CirLinkedList<T> {
    public Node<T> firstNode;
    private Node<T> lastNode;
    private Node<T> currentNode;
    private Node<T> previousNode;
    public int maxNumNodes;
    private int numNodes;
    private Node<T> testNode;


    public String toString(){
        String s = " ";
        testNode = firstNode;
        for(int i = 0; i < numNodes; i++){
            s+= testNode;
            s+=" ";
            testNode = testNode.nextNode;
        }
        return s;
    }

    public void setMax(int setMaxNumNodes){
        this.maxNumNodes = setMaxNumNodes;
    }
    public int getMaxNumNodes(){return this.maxNumNodes;}

    public boolean isEmpty(){
        return numNodes == 0;
    }

    //get first node.
    public Node<T> getFirstNode(){
        return this.firstNode;
    }

    //get last node.
    public Node<T> getLastNode(){
        return this.lastNode;
    }

    //gets number of nodes.
    public int getNumNodes(){
        return numNodes;
    }

    //if one node, they are the winner.
    public boolean isWinner(){
        if(numNodes == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void addNode(Node<T> newNode){
        //if there are no nodes, make one.
        if(isEmpty()){
            firstNode = newNode;
            lastNode = newNode;
            firstNode.nextNode = lastNode;
            numNodes++;
        }
        //else lets add nodes to the link.
        else{
            Node<T> temp = lastNode;
            lastNode = newNode;
            temp.nextNode = lastNode;
            lastNode.nextNode = firstNode.nextNode;
            numNodes++;
        }
    }

    public Node<T> getNode(int i){
        int count = 1;
        Node<T> helperNode = firstNode;
        while(count != i){
            helperNode = helperNode.nextNode;
        }
        return helperNode;
    }

    public Node<T> removeNode(int i){
        //just in case, current references null.
        if(currentNode == null){
            currentNode = firstNode;
        }
        //
        if(numNodes == 1){
            return firstNode;
        }
        //
        if(numNodes == maxNumNodes){
            currentNode = firstNode;
            previousNode = firstNode;
        }
        previousNode = firstNode;
        int count = 1;
        //remove last node.
        if(i == getNumNodes()){
            currentNode = lastNode.nextNode;
            previousNode = currentNode;
            count = 1;
            while(count != getNumNodes()-1){
                previousNode = previousNode.nextNode;
                count++;
            }
            Node<T> returnNode = lastNode;
            previousNode.nextNode = firstNode;
            lastNode = previousNode;
            numNodes--;
            return returnNode;
        }
        //remove first node.
        else if(i == 1){
            Node<T> returnNode = firstNode;
            firstNode = firstNode.nextNode;
            lastNode.nextNode = firstNode;
            numNodes--;
            return returnNode;
        }
        //remove the stuff in between first/last
        else{
            count = 1;
            while(count != i){
                out.println("Current node: " + currentNode);
                //out.println("Previous Node: " + previousNode);
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
                count++;
            }
            out.println("COUNT: " + count);
        }
        //in between re-wiring.
        Node<T> returnNode = currentNode;
        if(currentNode == firstNode){
            firstNode = firstNode.nextNode;
        }
        out.println("CURRENT BEFORE RETURN: " + currentNode);
        out.println("PREVIOUS BEFORE RETURN: " + previousNode);
        previousNode.nextNode = currentNode.nextNode;
        out.println("PREVIOUS.NEXT: " + previousNode.nextNode);
        currentNode = currentNode.nextNode;
        out.println("CURRENT Re-Written: " + currentNode);
        numNodes--;
        return returnNode;
    }
}
