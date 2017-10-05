/**
 * Created by Daniel on 10/25/2016.
 */
//Use this for elimination.
public class Node <T> {
    public T element;
    public Node<T> nextNode;
    public Node(T object){ element = object;}
    public String toString() {return (element.toString());}
}
