/**
 * Created by Daniel on 11/19/2016.
 */
public class RedBlackNode<K extends Comparable<K>, E> {

    private RedBlackNode leftChild;
    private RedBlackNode rightChild;
    private RedBlackNode parent;
    String whatColor;
    boolean color; // true = red/ false = black

    K key;
    E element;

    public RedBlackNode(K key, E element){
        this.key = key;
        this.element = element;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    public void setRed(){
        this.color = true;
        this.whatColor = "Red";
    }

    public void setBlack(){
        this.color = false;
        this.whatColor = "Black";
    }

    public boolean isRed(){
        return this.color == true;
    }

    public boolean isBlack(){
        return this.color == false;
    }

    //Left Child Nodes
    public void setLeftChild(RedBlackNode bastard){
        this.leftChild = bastard;
    }

    public RedBlackNode getLeftChild(){
        return this.leftChild;
    }

    public void removeLeftChild(){
        this.leftChild = null;
    }

    //Right Child Nodes
    public void setRightChild(RedBlackNode favChild){
        this.rightChild = favChild;
    }

    public RedBlackNode getRightChild(){
        return this.rightChild;
    }

    public void removeRightChild(){
        this.rightChild = null;
    }

    //Parent Nodes
    public void setParent(RedBlackNode coolDad){
        this.parent = coolDad;
    }

    public RedBlackNode getParent(){ return this.parent; }

    public void removeParent(){
        this.parent = null;
    }

    public String getWhatColor(){
        return this.whatColor;
    }

    public K getKey(){
        return this.key;
    }

    public E getElement(){
        return this.element;
    }

}
