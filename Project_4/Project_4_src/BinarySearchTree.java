import java.util.NoSuchElementException;

/**
 * Created by Daniel on 11/15/2016.
 */
public class BinarySearchTree<K extends Comparable<K>,E> implements IBinarySearchTree<K ,E > {
    int sizeOfTree = 0;
    private Node root;

    //GET and PUT were all used from the book.
    @Override
    public void put(K key, E element) {
        Node newNode = new Node(key , element); // make new node
        newNode.key = key;                      //insert date
        newNode.element = element;
        if(root == null){                       //if no node in root
            root = newNode;
            sizeOfTree++;
        }
        else{                                   //there is a root.
            Node current = root;
            Node parent;
            while(true){                        //(exits internally)
                parent = current;
                if(newNode.key.compareTo(current.key) <= -1){ //go left?
                    current = current.leftChild;
                    if(current == null){                      //if end of the line
                        parent.leftChild = newNode;           //insert on left
                        sizeOfTree++;                         //increase size of tree
                        return;
                    }
                }   //end if go left
                else{                                         //go right?
                    current = current.rightChild;
                    if(current == null){                      //if end of the line
                        parent.rightChild = newNode;          //insert on right
                        sizeOfTree++;                         //increase size of tree
                        return;
                    }
                }   //end else go right
            }   //end while
        }   //end else not root
    }   //end put()

    @Override
    public E get(K key) {   //find node with given key
        Node current = root;    //start at root
        while(current.key != key) {      //while no match,
            if (key.compareTo((K) current.key) <= -1) {
                current = current.leftChild;
            }
            else if (key.compareTo((K) current.key) >= 1) {
                current = current.rightChild;
            }
            else if (key.compareTo((K) current.key) == 0) {
                return (E) current.element;
            }

            if(current == null) {      //if no child,
                throw (new NoSuchElementException());   //throw no such element exception
            }
        }
        return (E) current.element;
    }

    @Override
    public int size() {
        return sizeOfTree;
    }


    //http://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree
    @Override
    public int getHeight() {
        //If tree has a root, it is still 0.
        if(sizeOfTree == 0 || sizeOfTree == 1){
            return 0;
        }
        //We account for the fact that having a root is equal to 0 by subtracting 1.
        else{
            return heightRecursive(root) - 1;
        }

    }

    public int heightRecursive(Node n){
        //tracks how tall the left and right side of the tree is.
        int righth = 0;
        int lefth = 0;

        //As long as there is a left child, recurse.
        if(n.leftChild != null){
            lefth = heightRecursive(n.leftChild);
        }

        //As long as there is a right child, recurse.
        if(n.rightChild != null){
            righth = heightRecursive(n.rightChild);
        }

        //compare left and right paths to find which is greater, and your height.
        if (lefth > righth) {
            return lefth + 1;
        } else {
            return righth + 1;
        }
    }

    @Override
    public String getTreeString() {
        //If there is no root, return an empty brackets.
        if(root == null){
            return "[]";
        }
        //Otherwise, make a recursive call inside brackets.
        return "[" + getTreeStringRecursive(root) + "]";
    }


    public String getTreeStringRecursive(Node n) {
        //make an empty String variable to add to.
        String nodStr = "";
        //As long as node is not null...
        if (n != null) {
            //Add the nodes key to string var., separated by a comma.
            nodStr += n.key + ", ";
            //If left child is NOT null, do recursion.
            if(n.leftChild != null) {
                nodStr += "[" + getTreeStringRecursive(n.leftChild) + "], ";
            }
            //Else if left child is null, add to string var with null separated by a comma.
            else {
                nodStr += "null, ";
            }

            //If right child is NOT null, do recursion.
            if(n.rightChild != null){
                nodStr += "[" + getTreeStringRecursive(n.rightChild) + "]";
            }
            //Else if right child is null, add to string var with null.
            else{
                nodStr += "null";
            }
            return nodStr;
        }
        return "";
    }

    public String toString(){
        return "[" + toStringRecursive(root) + "]";
    }

    public String toStringRecursive(Node n){
        //create an empty string variable to add too.
        String nodStr = "";
        //If the node is NOT null...
        if(n != null){
            //If the nodes left child is NOT null...
            if(n.leftChild != null){
                //add to string var and do recursion with a comma.
                nodStr += toStringRecursive(n.leftChild) + ", ";
            }
            //separate the key and element with a colon.
            nodStr += n.key + ": " + n.element;
            //If the nodes right child is NOT null...
            if(n.rightChild != null){
                //Add a comma first, then do recursion.
                nodStr += ", ";
                nodStr += toStringRecursive(n.rightChild);
            }
            return nodStr;
        }
        return "";
    }
}
