import java.util.NoSuchElementException;

/**
 * Created by Daniel on 11/29/2016.
 */
public class HashMap<K, E> implements IMap<K, E> {
    private E[] hashArray;
    private int size;

    public HashMap(int i) {
        hashArray = (E[]) new Object[i];
    }

    @Override
    public void put(K key, E element) {
        //increment size.
        size++;
        Node<K, E> newNode = new Node(key, element);
        int code = key.hashCode();
        int hashedIndex = Math.floorMod(code, hashArray.length);

        //If there is nothing at that index, set that value of the index.
        if (hashArray[hashedIndex] == null) {
            hashArray[hashedIndex] = (E) newNode;
        }
        //If there is a value at the index. Make it a node and connect it to the value in the index.
        else{
            Node current = (Node) hashArray[hashedIndex];
            Node beforeCurrent = null;

            while(current != null){
                beforeCurrent = current;
                current = (Node) current.next();
            }
            beforeCurrent.setNextNode(newNode);
        }
    }

    @Override
    public E get(K key) {
        int code = key.hashCode();
        int hashedIndex = Math.floorMod(code, hashArray.length);
        //If there is a value at the index...
        if(hashArray[hashedIndex] != null){
            Node<K, E> node = new Node(key);
            Node<K, E> compNode = (Node) hashArray[hashedIndex];
            //If nodes key, is equal to the node you are looking for, return it.
            if(node.getKey().equals(compNode.getKey())){
                return compNode.getElement();
            }
            //Otherwise, lets go through all the nodes in that index...
            while(compNode != null){
                //If you hit a node that is equal to the one you are looking for, return.
                if(node.getKey().equals(compNode.getKey())){
                    return compNode.getElement();
                }
                compNode = (Node) compNode.next();
            }
        }
        //If nothing is found...
        throw new NoSuchElementException();
    }

    //Increment size everytime you put something. So return the size.
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Object[] getInternalArray() {
        return hashArray;
    }

    //toString, just printing out what is inside the array, all nice like.
    public String toString(){
        int size = size();
        String s = "[";
        for(int i = 0; i < hashArray.length; i++){
            if(hashArray[i] == null){
                continue;
            }
            else if(hashArray[i] != null){
                Node curr = (Node) hashArray[i];
                while(curr != null){
                    size--;
                    if(size == 0){
                        s += curr.toString();
                        break;
                    }
                    s+= (curr.toString() + ", ");
                    curr = (Node) curr.next();
                    }
                }
            }
            s += "]";
            return s;
        }


//Basically what we are doing in this class, is setting pointers to nodes added into the array if the index is already being used.
    //for separate chaining.
    private class Node<K, E> implements IMap.IMapPair {
        private K key;
        private E element;
        private Node<K, E> nextNode;

        public Node(K key, E element) {
            this.key = key;
            this.element = element;
            this.nextNode = null;
        }

        public Node(K key) {
            this.key = key;
            this.nextNode = null;
        }

        public void setNextNode(Node<K, E> node) {
            this.nextNode = node;
        }

        public K getKey() {
            return this.key;
        }

        public E getElement() {
            return this.element;
        }

        public IMapPair next() {
            return this.nextNode;
        }

        public String toString() {
            return (key + ": " + element);
        }
    }

    public static void main(String[] args){
        //For the first map with 100000 values.
        int amount1 = 100000;
        HashMap map1 = new HashMap(200000);

        //Filling the map with values.
        for(int i = 0; i < amount1; i++){
            map1.put(i%amount1, i);
        }

        //start the clock.
        long start = System.nanoTime();

        //Finding the amounts.
        for(int j = 0; j < amount1*10; j++){
            map1.get(j%amount1);
        }

        //end the clock.
        long end = System.nanoTime();

        //print statements telling what is going on.
        System.out.println("Map size: " + map1.size());
        System.out.println("Time it took: " + (end - start) + " in nanoseconds.\n");
//******************************************************************//
        //For the second map with 200000 values.
        int amount2 = 200000;
        HashMap map2 = new HashMap(400000);

        for(int i = 0; i < amount2; i++){
            map2.put(i%amount2, i);
        }

        start = System.nanoTime();
        for(int j = 0; j < amount2*10; j++){
            map2.get(j%amount2);
        }
        end = System.nanoTime();
        System.out.println("Map size: " + map2.size());
        System.out.println("Time it took: " + (end - start) + " in nanoseconds.\n");
//****************************************************************//
        //For the third map with 300000 values.
        int amount3 = 300000;
        HashMap map3 = new HashMap(600000);

        for(int i = 0; i < amount3; i++){
            map3.put(i%amount3, i);
        }

        start = System.nanoTime();
        for(int j = 0; j < amount3*10; j++){
            map3.get(j%amount3);
        }
        end = System.nanoTime();
        System.out.println("Map size: " + map3.size());
        System.out.println("Time it took: " + (end - start) + " in nanoseconds.\n");
    }
}
