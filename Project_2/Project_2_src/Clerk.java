/**
 * Created by Daniel on 10/25/2016.
 */
public class Clerk {
    private int size;
    private int numCustomers;
    private Customer[] queue;
    private int frontLine;
    private int backLine;

    public Clerk(int size){
        this.size = size;
        this.numCustomers = 0;
        this.frontLine = 0;
        this.backLine = 0;
        queue = new Customer[size];
    }

    public int removeItem(){
        int checkouts = 0;
        //safe guard, in case front of line equals null.
        if (queue[frontLine] == null) {return 0;}
        //set front of line to 0, if it goes out of bounds. Because its a queue.
        if(frontLine >= size){frontLine = 0;}
        //if customer is out of items, kick them out of market.
        else if(queue[frontLine].getNumItems() <= 0){
            removeCustomer();
            checkouts++;
            return checkouts;
        }
        //if customer has items, remove 1 item.
        else{
            queue[frontLine].takeItem();
            return 0;
        }
        return checkouts;
    }

    public void removeCustomer(){
        //if front of line is = null, just return.
        if(queue[frontLine] == null){return;}
        //if line is empty
        if(isLineEmpty()){
            System.out.println("Cannot remove customer, line is empty.");
            return;
        }
        //in case front of line surpasses the size of queue, set to 0.
        if(frontLine >= size){
            frontLine = 0;
            queue[frontLine++] = null;
            numCustomers--;}
        //if everything is good, do this.
        else{
            queue[frontLine++] = null;
            numCustomers--;
        }
    }

    public void addCustomer(Customer payday){
        //if line is full
        if (isLineFull()) {
            System.out.println("Cannot add customer, line is full.");
            return;
        }
        //in case back of line surpasses the size of queue, set to 0.
        if(backLine >= size){
            backLine = 0;
            queue[backLine++] = payday;
            numCustomers++;
        }
        //if everything is good, do this.
        else {
            queue[backLine++] = payday;
            numCustomers++;
        }
    }

    //if line is full, return true, else false.
    public boolean isLineFull(){
        return numCustomers == size;
    }

    //if line is empty, return true, else false
    public boolean isLineEmpty(){
        return numCustomers == 0;
    }

    //just return number of customers in line.
    public int getSizeOfCustomers(){
        return numCustomers;
    }

    //toString helps out with the App.
    public String toString(){
        String s = "Clerk line: ";
        for (int i=0; i < size; i++){
            if (queue[i] == null) {
                s+= "NULL ";
                continue;
            }
            s += queue[i];
        }
        return s;
    }
}
