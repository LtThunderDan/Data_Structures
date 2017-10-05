import java.util.ArrayList;
/**
 * Created by Daniel on 10/20/2016.
 */
public class SupermarketCheckouts implements Supermarket {
    ArrayList<Clerk> market = new ArrayList<>();

    public SupermarketCheckouts(){}

    //helps with App.
    public String toString(){
        String s = "Current market: \n";
        for (int i = 0; i < market.size() ;i++){
            s+= (market.get(i) + "\n");
        }
        return s;
    }

    public void buildMarket(int numClerks, int lineCapacity) {
        //if market has no clerks or lines, return.
        if(numClerks <= 0 || lineCapacity <=0){return;}
        //lets build a market, and fill it with i amounts of clerks.
        for(int i = 0; i < numClerks; i++){
            Clerk clerk = new Clerk(lineCapacity);
            market.add(clerk);
        }
    }

    public boolean addCustomer(int numItems){
        if(numItems <= 0){return false;}
        for(int i = 0; i < market.size() ; i++){
            //market is not full
            if(!market.get(i).isLineFull()){break;}
            //if a clerk is full, check others.
            else if(market.get(i).isLineFull() && i < market.size()-1){continue;}
            //market is full
            else if(market.get(i).isLineFull() && i == market.size()-1){return false;}
            //market has room
            else{break;}
        }
        Customer payday = new Customer(numItems);
        //lets find the smallest line
        int shortestLine = 0;
        for(int j = 0; j < market.size(); j++){
            if(market.get(j).getSizeOfCustomers() < market.get(shortestLine).getSizeOfCustomers()){
                shortestLine = j;
            }
        }
        Clerk smallestLine = market.get(shortestLine);
        smallestLine.addCustomer(payday);
        return true;
    }

    public int tick(){
        int customerCheckout = 0;
        for(int i = 0; i < market.size(); i++){
            //is line empty/full
            if(!market.get(i).isLineEmpty() && !market.get(i).isLineFull()){break;}
            //is line empty and not last clerk
            else if(market.get(i).isLineEmpty() && i < market.size()-1){continue;}
            //is line empty and last clerk
            else if(market.get(i).isLineEmpty() && i == market.size()-1){return -1;}
            else{break;}
        }
        for(int j = 0; j < market.size(); j++){
            customerCheckout += market.get(j).removeItem();
        }
        if(customerCheckout == 0){return 0;}
        else {return customerCheckout;}
    }
}
