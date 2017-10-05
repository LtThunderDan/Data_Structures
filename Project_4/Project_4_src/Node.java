import static java.lang.System.out;

/**
 * Created by Daniel on 11/15/2016.
 */
public class Node<K extends Comparable<K>,E> {
    //class used to simple create what it means to be a node. And used to pass tests.
    E element;
    K key;

    public Node leftChild;
    public Node rightChild;

    public Node(K key, E element){
        this.element = element;
        this.key = key;
        this.leftChild = null;
        this.rightChild = null;
    }

    public String toString(){
        String s = "{";
        s += key;
        s += ", ";
        s += element;
        s += "}";
        return s;
    }
}
