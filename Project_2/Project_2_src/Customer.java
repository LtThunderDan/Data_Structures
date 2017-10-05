/**
 * Created by Daniel on 10/25/2016.
 */
public class Customer {
    int numItems;

    public Customer(int numItems){
        this.numItems = numItems;
    }

    //get number of items a customer has.
    public int getNumItems(){return numItems;}

    //when an item gets removed, decrement the number of items.
    public void takeItem(){numItems--;}

    //a toString helps out with the App.
    public String toString(){
        return("A customer with "+numItems+" items. ");
    }
}
