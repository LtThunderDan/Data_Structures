import java.util.NoSuchElementException;

/**
 * Created by Daniel on 11/15/2016.
 */
public class RedBlackTree<K extends Comparable<K>,E> implements IBinarySearchTree<K , E> {
    private RedBlackNode root;
    private int size;

    //essentially same a BST, from the book.
    @Override
    public void put(K key, E element) {
        RedBlackNode newNode = new RedBlackNode(key, element);
        //If there are no nodes, insert, and make black.
        if (root == null){
            root = newNode;
            root.setBlack();
            size = 1;
        }
        else{
            RedBlackNode current = root;
            RedBlackNode parent;
            while(true){
                parent = current;
                if(newNode.getKey().compareTo(current.getKey())<= -1){
                    current = current.getLeftChild();
                    if(current == null){
                        parent.setLeftChild(newNode);
                        newNode.setParent(parent);
                        size += 1;
                        break;
                    }
                }
                else {
                    current = current.getRightChild();
                    if(current == null){
                        parent.setRightChild(newNode);
                        newNode.setParent(parent);
                        size += 1;
                        break;
                    }
                }
            }
            balanceTree(newNode);
            root.setBlack();
        }
    }

    //balanceTree will re-balance the red/black tree.
    private void balanceTree(RedBlackNode currentNode){
        //If parent node is root, return.
        if(currentNode.getParent() == root){
            return;
        }
        //If node is black, we don't need to re-balance.
        else if(currentNode.getParent().isBlack()){
            return;
        }
        //If node is black, we need to check parent nodes to see if we need to balance.
        else if(currentNode.isBlack()) {
            balanceTree(currentNode.getParent());
        }
        //If parent of the parent node(Grandparent), is the root.
        else if(currentNode.getParent().getParent().getParent() == null){
            //If parent is a left child...
            if(currentNode.getParent().getParent().getLeftChild() == currentNode.getParent()){
                //If parent's sibling is null or black...
                if(currentNode.getParent().getParent().getRightChild() == null ||
                        currentNode.getParent().getParent().getRightChild().isBlack()){
                    //If node is a left child...
                    if(currentNode.getParent().getLeftChild() == currentNode){
                        //we rotate right.
                        rootRotateRight(currentNode);
                    }
                    //If node is a right child...
                    else if (currentNode.getParent().getRightChild() == currentNode){
                        //we reconstruct left.
                        rootLeftRestructure(currentNode);
                    }

                }
                //If parent is not null or black, meaning it is red... we recolor
                else{
                    rootLeftRecolor(currentNode);
                }
            }
            //If parent is right child...
            else if (currentNode.getParent().getParent().getRightChild() == currentNode.getParent()){
                //If parent's sibling is null or black...
                if(currentNode.getParent().getParent().getLeftChild() == null ||
                        currentNode.getParent().getParent().getLeftChild().isBlack()){
                    //If node is a left child...
                    if(currentNode.getParent().getLeftChild() == currentNode){
                        //we reconstruct right.
                        rootRightRestructure(currentNode);
                    }
                    //If node is a right child...
                    else if (currentNode.getParent().getRightChild() == currentNode){
                        //we rotate left.
                        rootRotateLeft(currentNode);
                    }
                }
                //If parent is not null or black, meaning it is red... we recolor.
                else{
                    rootRightRecolor(currentNode);
                }
            }
        }
        //If parent of the parent node (Grandparent), is NOT the root.
        else if (currentNode.getParent().getParent() != root){
            //If parent is a left child...
            if(currentNode.getParent().getParent().getLeftChild() == currentNode.getParent()){
                //If parent's sibling is null or black...
                if(currentNode.getParent().getParent().getRightChild() == null ||
                        currentNode.getParent().getParent().getRightChild().isBlack()){
                    //If node is a left child...
                    if(currentNode.getParent().getLeftChild() == currentNode){
                        //we rotate right.
                        rotateRight(currentNode);
                    }
                    //If node is a right child...
                    else if (currentNode.getParent().getRightChild() == currentNode){
                        //we reconstruct left.
                        leftRestructure(currentNode);
                    }
                }
                //If parent is not null or black, meaning it is red... we recolor.
                else{
                    leftRecolor(currentNode);
                }
            }
            //Else if parent is a right child...
            else if (currentNode.getParent().getParent().getRightChild() == currentNode.getParent()){
                //If parent's sibling is null or black...
                if(currentNode.getParent().getParent().getLeftChild() == null ||
                        currentNode.getParent().getParent().getLeftChild().isBlack()){
                    //If node is a left child...
                    if(currentNode.getParent().getLeftChild() == currentNode){
                        //we reconstruct right.
                        rightRestructure(currentNode);
                    }
                    //If node is a right child...
                    else if (currentNode.getParent().getRightChild() == currentNode){
                        //we rotate left.
                        rotateLeft(currentNode);
                    }
                }
                //If parent is not null or black, meaning it is red... we recolor.
                else{
                    rightRecolor(currentNode);
                }
            }
        }
        //If nodes parent AND grandparent are not null, balance the tree.
        if (currentNode.getParent() != null && currentNode.getParent().getParent() != null) {
            balanceTree(currentNode.getParent());
        }
    }

    //method to handle a case in balanceTree
    private void setRoot(RedBlackNode n){
        //turn node to black.
        n.setBlack();
        //remove its parent.
        n.removeParent();
        //set node to the new root.
        root = n;
    }

    //method used to rotate a root node, left.
    private void rootRotateRight(RedBlackNode n){
        RedBlackNode tempNode = root;
        tempNode.setRed();
        //If left sibling is NOT null
        if (n.getParent().getRightChild() != null){
            //set left sibling to right child.
            tempNode.setLeftChild(n.getParent().getRightChild());
        }
        //else sibling is null.
        else {
            //remove the left child.
            tempNode.removeLeftChild();
        }
        //set parent's right child.
        n.getParent().setRightChild(tempNode);
        //set new root.
        setRoot(n.getParent());
    }

    //method used to rotate a root node, right.
    private void rootRotateLeft(RedBlackNode n){
        RedBlackNode tempNode = root;
        tempNode.setRed();
        //If right sibling is NOT null...
        if (n.getParent().getLeftChild() != null){
            //set right sibling to left child.
            tempNode.setRightChild(n.getParent().getLeftChild());
        }
        //else sibling is null.
        else {
            tempNode.removeRightChild();
        }
        tempNode.setParent(n.getParent());
        //set left sibling to temp node.
        n.getParent().setLeftChild(tempNode);
        //set new root.
        setRoot(n.getParent());

    }

    //method used to re-construct tree if parent is a left child.
    private void rootLeftRestructure(RedBlackNode n){
        //create a temp node.
        RedBlackNode tempRoot = root;
        //set temp node to red.
        tempRoot.setRed();
        //remove left child.
        tempRoot.removeLeftChild();
        //remove right child of parent.
        n.getParent().removeRightChild();
        //set parent node to roots left child.
        n.setLeftChild(n.getParent());
        //replace node as right child.
        n.setRightChild(tempRoot);
        //set new root.
        setRoot(n);
    }

    //method used to re-construct tree if parent is a right child.
    private void rootRightRestructure(RedBlackNode n){
        //create a temp node.
        RedBlackNode tempRoot = root;
        //set temp node to red.
        tempRoot.setRed();
        //remove right child.
        tempRoot.removeRightChild();
        //remove left child of parent.
        n.getParent().removeLeftChild();
        //set parent node to roots right child.
        n.setRightChild(n.getParent());
        //replace node as left child
        n.setLeftChild(tempRoot);
        //set new root.
        setRoot(n);
    }

    //recolor node if grandparent is root.
    private void rootLeftRecolor(RedBlackNode n){
        //set parent black.
        n.getParent().setBlack();
        //set grandparents right sibling to black.
        n.getParent().getParent().getRightChild().setBlack();
    }

    //recolor node if grandparent is root.
    private void rootRightRecolor(RedBlackNode n){
        //set parent black.
        n.getParent().setBlack();
        //set grandparents right sibling to black.
        n.getParent().getParent().getLeftChild().setBlack();
    }

    //recolor node if grandparent is NOT a root.
    private void leftRecolor(RedBlackNode n){
        //set parent to black.
        n.getParent().setBlack();
        //set grandparents right sibling to black.
        n.getParent().getParent().getRightChild().setBlack();
        //set grandparent to red.
        n.getParent().getParent().setRed();
    }

    //recolor node if grandparent is NOT root.
    private void rightRecolor(RedBlackNode n){
        //set parent black.
        n.getParent().setBlack();
        //set grandparents left sibling to black.
        n.getParent().getParent().getLeftChild().setBlack();
        //set grandparent to red.
        n.getParent().getParent().setRed();
    }

    //method used to rotate tree to the right if grandparent is NOT root.
    private void rotateRight(RedBlackNode n){
        //set grandparent as temp root.
        RedBlackNode tempRoot = n.getParent().getParent();
        //set temp to red.
        tempRoot.setRed();
        //set parent to black.
        n.getParent().setBlack();
        //IF grandparent is right child...
        if(tempRoot.getParent().getRightChild() == tempRoot) {
            //set parent as right child of great grandparent.
            tempRoot.getParent().setRightChild(n.getParent());
        }
        else{
            //set parent as left child of great grandparent.
            tempRoot.getParent().setLeftChild(n.getParent());
        }
        //If parents right child is NOT null.
        if(n.getParent().getRightChild() != null) {
            //set it to temps left child.
            tempRoot.setLeftChild(n.getParent().getRightChild());
        }
        else{
            //remove temps left child.
            tempRoot.removeLeftChild();
        }
        //set temp as right child of parent.
        n.getParent().setRightChild(tempRoot);
    }

    //method used to rotate tree to the left if grandparent is NOT root.
    private void rotateLeft(RedBlackNode n){
        //set grandparent as temp root.
        RedBlackNode tempRoot = n.getParent().getParent();
        //set temp to red.
        tempRoot.setRed();
        //set parent to black.
        n.getParent().setBlack();
        //If Grandparent is left child.
        if(tempRoot.getParent().getLeftChild() == tempRoot) {
            //set parent as the left child of great grandparent.
            tempRoot.getParent().setLeftChild(n.getParent());
        }
        else{
            //set parent as the right child of great grandparent
            tempRoot.getParent().setRightChild(n.getParent());
        }
        //If parents left child is NOT null.
        if(n.getParent().getLeftChild() != null) {
            //set it to temps right child.
            tempRoot.setRightChild(n.getParent().getLeftChild());
        }
        else{
            //remove temps right child
            tempRoot.removeRightChild();
        }
        //set temp as left child of parent.
        n.getParent().setLeftChild(tempRoot);
    }

    //method to re-construct tree if grandparent is NOT root.
    private void leftRestructure(RedBlackNode n){
        //set grandparent as temp node.
        RedBlackNode tempRoot = n.getParent().getParent();
        //set temp to red.
        tempRoot.setRed();
        //set node to black.
        n.setBlack();
        //remove right child.
        n.getParent().removeRightChild();
        //remove temp left child.
        tempRoot.removeLeftChild();
        //set parent as nodes left child.
        n.setLeftChild(n.getParent());
        //If temp is right child...
        if(tempRoot.getParent().getRightChild() == tempRoot){
            //set node to temps parents right child.
            tempRoot.getParent().setRightChild(n);
        }
        else {
            //set node as temps parents left child.
            tempRoot.getParent().setLeftChild(n);
        }
        //set temp as nodes right child.
        n.setRightChild(tempRoot);
    }

    //method to re-construct tree if grandparent is NOT root.
    private void rightRestructure(RedBlackNode n){
        //set grandparent as temp node.
        RedBlackNode tempRoot = n.getParent().getParent();
        //set temp to red.
        tempRoot.setRed();
        //set node to black.
        n.setBlack();
        //remove parents left child.
        n.getParent().removeLeftChild();
        //remove temps right child.
        tempRoot.removeRightChild();
        //set parents nodes right child.
        n.setRightChild(n.getParent());
        //If temp is left child.
        if(tempRoot.getParent().getRightChild() == tempRoot){
            //set node as temps parents left child.
            tempRoot.getParent().setRightChild(n);
        }
        else {
            //set node as temps parents left child.
            tempRoot.getParent().setLeftChild(n);
        }
        //set temp as nodes left child.
        n.setLeftChild(tempRoot);
    }

    //essentially same a BST, from the book.
    @Override
    public E get(K key) {
        RedBlackNode current = root;
        while(current.getKey() != key){
            if(key.compareTo((K) current.getKey()) <= -1){
                current = current.getLeftChild();
            }
            else if (key.compareTo((K) current.getKey()) >= 1){
                current = current.getRightChild();
            }
            else{
                return (E) current.getElement();
            }
            if(current == null){
                throw(new NoSuchElementException());
            }
        }
        return  (E) current.getElement();
    }

    @Override
    public int size() { return size; }

    //Found online, http://stackoverflow.com/questions/2597637/finding-height-in-binary-search-tree
    @Override
    public int getHeight() {
        if(size == 0) return 0;
        return getHeightRecursive(root) - 1;
    }

    private int getHeightRecursive(RedBlackNode n) {
        if (n == null){
            return 0;
        }
        else{
            return 1 + Math.max(getHeightRecursive(n.getLeftChild()), getHeightRecursive(n.getRightChild()));
        }
    }

    //essentially same a BST, from the book.
    @Override
    public String getTreeString() {
        if (root == null) return "[]";
        return getTreeStringRecursive(root);
    }

    //essentially same a BST, from the book.
    public String getTreeStringRecursive(RedBlackNode n){
        String s = "[";
        if(n != null) {
            s += n.getWhatColor() + ", " + n.getKey() + ", ";
            if(n.getLeftChild() == null){
                s += "null, ";
            }
            else{
                s += getTreeStringRecursive(n.getLeftChild()) + ", ";
            }
            if(n.getRightChild() != null){
                s += getTreeStringRecursive(n.getRightChild());
            }
            else{
                s += "null";
            }
            s += "]";
            return s;
        }
        return s;
    }

    //essentially same a BST, from the book.
    public String toString(){ return "[" + toStringRecursive(root) + "]"; }

    //essentially same a BST, from the book.
    private String toStringRecursive(RedBlackNode n){
        String s = "";
        if(n != null){
            if(n.getLeftChild() != null) {
                s += toStringRecursive(n.getLeftChild()) + ", ";
            }
            s +=  n.getKey() + ": " + n.getElement();
            if(n.getRightChild() != null){
                s += ", ";
                s += toStringRecursive(n.getRightChild());
            }
            return s;
        }
        return "";
    }
}