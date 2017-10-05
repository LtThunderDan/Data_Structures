/**
 * Created by Daniel on 11/29/2016.
 */
public class HashSet<E> implements ISet<E> {
    private E[] hashArray;

    public HashSet(int i){
        hashArray = (E[]) new Object[i];
    }

    @Override
    public void add(E element) {
        int code = element.hashCode();
        int hashedIndex = Math.floorMod(code, hashArray.length);
        //If when adding into set and the element already exists.
        //We need to hash double hash the element and convert it into a string. To store somewhere else.
        while(hashArray[hashedIndex] != null){
            //convert element into string...
            String stringCode = String.valueOf(code);
            //code now equals the string code after being hashed.
            code = stringCode.hashCode();
            //the hashedIndex will now be used as the new index.
            hashedIndex = Math.floorMod(code, hashArray.length);
        }
        hashArray[hashedIndex] = element;
    }


    @Override
    public boolean has(E element) {
        //hash the element.
        int hashEle = element.hashCode();
        //Setting the index for where the element should go.
        int index = Math.floorMod(hashEle, hashArray.length);
        //counter for comparing to the length of the array
        int count = 0;

        //if there is nothing at the index, it doesn't have it.
        while(hashArray[index] != null){
            //iterate counter.
            count++;
            //If counter exceeds the length of the array, then we don't have the element.
            if(count >= hashArray.length){
                break;
            }
            //If we find the element and they are equal, we have it!
            if(hashArray[index].equals(element)){
                return true;
            }
            //double hashing.
            String hashEleString = "" + hashEle;
            hashEle = hashEleString.hashCode();
            index = Math.floorMod(hashEle, hashArray.length);
        }
        return false;

        //Inefficient way, by looking through all the values.
//        //runs through all index's of the array...
//        for (int j = 0; j < hashArray.length; j++) {
//            //If index is equal to null, continue, which means it does not have it.
//            if (hashArray[j] == null) {
//                continue;
//            //Else If at index j the elements are equal, we do have it.
//            } else if (hashArray[j].equals(element)) {
//                return true;
//            }
//        }
//        return false;
    }

    @Override
    public int size() {
        int someSize = 0;
        //lets run through the array and increment the size each time we find a position that is not null.
        for(int i = 0; i < hashArray.length; i++)
            if(hashArray[i] != null){
                someSize++;
            }
        return someSize;
    }

    @Override
    public Object[] getInternalArray() {
        return hashArray;
    }

    public String toString() {
        //lets get the size of the array.
        int size = size();
        //make a string variable starting with a bracket.
        String s = "[";
        //For all index's of the array...
        for(int i = 0; i < hashArray.length; i++){
            //we check if a spot equals null...
            if(hashArray[i] == null){
                //if null, continue.
                continue;
            }
            //decrement size.
            size--;
            //Once size hits zero, don't add a comma and break out of for loop.
            if(size == 0){
                s += hashArray[i].toString();
                break;
            }
            //If size is not equal to zero, add a comma.
            else {
                s += (hashArray[i] + ", ");
            }
        }
        s += "]";
        return s;
    }

    public static void main(String[] args){
        int amount1 = 100000;
        HashSet set1 = new HashSet(150000);

        //Fill set with values.
        for (int i = 0; i < amount1; i++) {
            set1.add(i);
        }

        //Start the clock.
        long start = System.nanoTime();

        //Looking to see if we have it.
        for(int j = 0; j < amount1*10;j++){
            set1.has(j%amount1);
        }
        //End the clock.
        long end = System.nanoTime();

        System.out.println("Size of set: " + set1.size());
        System.out.println("Time it took: " +(end - start) + " in nanoseconds\n");

        //*******************************************************8//

        int amount2 = 200000;
        HashSet set2 = new HashSet(300000);

        //Fill set with values.
        for (int i = 0; i < amount2; i++) {
            set2.add(i);
        }

        //Start the clock.
        start = System.nanoTime();

        //Looking to see if we have it.
        for(int j = 0; j < amount2*10;j++){
            set2.has(j%amount2);
        }
        //End the clock.
        end = System.nanoTime();

        System.out.println("Size of set: " + set2.size());
        System.out.println("Time it took: " +(end - start) + " in nanoseconds\n");

        //********************************************************//

        int amount3 = 300000;
        HashSet set3 = new HashSet(450000);

        //Fill set with values.
        for (int i = 0; i < amount3; i++) {
            set3.add(i);
        }

        //Start the clock.
        start = System.nanoTime();

        //Looking to see if we have it.
        for(int j = 0; j < amount3*10;j++){
            set3.has(j%amount3);
        }
        //End the clock.
        end = System.nanoTime();

        System.out.println("Size of set: " + set3.size());
        System.out.println("Time it took: " +(end - start) + " in nanoseconds\n");
    }
}
